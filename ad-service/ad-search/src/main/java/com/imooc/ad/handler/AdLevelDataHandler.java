package com.imooc.ad.handler;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.table.AdCreativeTable;
import com.imooc.ad.dump.table.AdCreativeUnitTable;
import com.imooc.ad.dump.table.AdPlanTable;
import com.imooc.ad.dump.table.AdUnitTable;
import com.imooc.ad.index.DataTable;
import com.imooc.ad.index.IndexAware;
import com.imooc.ad.index.adplan.AdPlanIndex;
import com.imooc.ad.index.adplan.AdPlanObject;
import com.imooc.ad.index.adunit.AdUnitIndex;
import com.imooc.ad.index.adunit.AdUnitObject;
import com.imooc.ad.index.creative.CreativeIndex;
import com.imooc.ad.index.creative.CreativeObject;
import com.imooc.ad.index.creativeunit.CreativeUnitIndex;
import com.imooc.ad.index.creativeunit.CreativeUnitObject;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 索引存在层级关系 依赖关系的划分 有了推广计划才有推广单元
 * 加载全量索引其实是增量索引“添加”的一种表现
 */
@Slf4j
public class AdLevelDataHandler {
    /**
     * 第二层级索引实现
     * @param planTable
     * @param type
     */
    public  static void handlerLeverlAdPlan(AdPlanTable planTable,OpType type){
            AdPlanObject planObject = new AdPlanObject(
                    planTable.getId(),
                    planTable.getUserId(),
                    planTable.getPlanStatus(),
                    planTable.getStartDate(),
                    planTable.getEndDate()
            );
                handleBinLogEvent(DataTable.of(AdPlanIndex.class),planObject.getPlanId(),planObject,type);
        }
            public  static void handlerLeverlAdCreative(AdCreativeTable creativeTable, OpType type){
                CreativeObject creativeObject = new CreativeObject(
                       creativeTable.getAdId(),
                        creativeTable.getName(),
                        creativeTable.getType(),
                        creativeTable.getMeterialType(),
                        creativeTable.getHeight(),
                        creativeTable.getWidth(),
                        creativeTable.getAuditStatus(),
                        creativeTable.getAdUrl()
                );
                handleBinLogEvent(DataTable.of(CreativeIndex.class),creativeObject.getAdId(),creativeObject,type);
            }


        public static void handleLevel3(AdUnitTable unitTable,OpType type){
            AdPlanObject planObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
            if(planObject == null){
                log.error("handleLevel3 found AdPlanObject error :{}",unitTable.getPlanId());
                return;
            }
            AdUnitObject unitObject = new AdUnitObject(
                    unitTable.getUnitId(),
                    unitTable.getUnitStatus(),
                    unitTable.getPositionType(),
                    unitTable.getPlanId(),

                    planObject
            );
            handleBinLogEvent(DataTable.of(AdUnitIndex.class),unitTable.getUnitId(),unitObject,type);
        }

    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpType type){
            if(type == OpType.UPDATE){
                log.error("CreativeUnitIndex not support update");
                return;
            }
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());

        if(unitObject == null || creativeObject == null){
            log.error("AdcreativeUnitTable index error :{}", JSON.toJSONString(creativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(creativeUnitTable.getAdId(), creativeUnitTable.getUnitId());
        handleBinLogEvent(DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(),creativeUnitObject.getUnitId().toString()),
                creativeUnitObject,
                type);


    }









            
    private static <K,V> void handleBinLogEvent(IndexAware<K,V> index, K key, V value, OpType type){

        switch (type){
            case ADD:
                index.add(key,value);
                break;
            case UPDATE:
                index.update(key,value);
                break;
            case DELETE:
                index.delete(key,value);
                break;
            default:
                break;
        }
    }
}

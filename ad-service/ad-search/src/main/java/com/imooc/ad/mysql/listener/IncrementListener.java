package com.imooc.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.imooc.ad.mysql.constant.Constant;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.mysql.dto.BinlogRowData;
import com.imooc.ad.mysql.dto.MySqlRowData;
import com.imooc.ad.mysql.dto.TableTemplate;
import com.imooc.ad.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class IncrementListener  implements IListener{

    @Resource(name="")
    private ISender sender;

    @Autowired
    private AggregationListener aggregationListener;

    @Override
    @PostConstruct
    public void register() {
        log.info(" IncrementListener register db and table info");
        Constant.table2Db.forEach((k,v) ->
                aggregationListener.register(v,k,this));

    }

    @Override
    public void onEvent(BinlogRowData eventData) {
        //BinlogRowData 转换 mysqlRowData
        TableTemplate tableTemplate = eventData.getTableTemplate();
        EventType eventType = eventData.getEventType();
        //包装成最后要投递的数据
        MySqlRowData rowData = new MySqlRowData();
        rowData.setTableName(tableTemplate.getTableName());
        rowData.setLevel(eventData.getTableTemplate().getLevel());
        OpType opType = OpType.to(eventType);
        List<String> fileldList = tableTemplate.getOpTypeFileldSetMap().get(opType);
        if(CollectionUtils.isEmpty(fileldList)){
            log.warn("{} no support for :{}",opType,tableTemplate.getTableName());
        }

        rowData.getFieldValueMap().addAll(eventData.getAfter());
        sender.sender(rowData);
    }
}

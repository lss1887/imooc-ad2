package com.imooc.ad.index.creativeunit;

import com.imooc.ad.index.IndexAware;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class CreativeUnitIndex implements IndexAware<String,CreativeUnitObject> {

    // <adId-unitId , CreaticeUnitObject>
    private static Map<String,CreativeUnitObject> objectMap;
    // adId  创意ID,unitId Set 推广单元ID
    private static Map<Long, Set<Long>> creativeUnitMap;
    //<unitId,adId set>
    private static Map<Long,Set<Long>> unitCrtiveMap;


    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        log.info("before add: {}", objectMap);
        objectMap.put(key,value);
        Set<Long> unitSet = CommonUtils.getOrCreate(value.getAdId(), creativeUnitMap, ConcurrentSkipListSet::new);

        log.info("after add: {}", objectMap);

    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("CreativeUnitIndex not support update");

    }

    @Override
    public void delete(String key, CreativeUnitObject value) {
        log.info("before add: {}", objectMap);
        objectMap.remove(key);

        Set<Long> unitSet = CommonUtils.getOrCreate(value.getAdId(), creativeUnitMap, ConcurrentSkipListSet::new);
        unitSet.remove(value.getUnitId());
        Set<Long> creativeSet = CommonUtils.getOrCreate(value.getUnitId(), unitCrtiveMap, ConcurrentSkipListSet::new);
        creativeSet.remove(value.getAdId());

        log.info("after add: {}", objectMap);
    }
}

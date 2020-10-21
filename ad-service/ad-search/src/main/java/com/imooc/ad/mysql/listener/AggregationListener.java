package com.imooc.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.imooc.ad.mysql.TemplateHolder;
import com.imooc.ad.mysql.dto.BinlogRowData;
import com.imooc.ad.mysql.dto.TableTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AggregationListener  implements BinaryLogClient.EventListener {
    private String dbName;
    private String tableName;
    private Map<String,IListener> listenerMap = new HashMap<>();

    @Autowired
    private  TemplateHolder templateHolder;

    private  String genkey(String dbName,String tableName){
        return dbName+":"+tableName;
    }

    public  void register(String dbName,String tableName,IListener iListener){
        log.info("register:{} -{}",dbName,tableName);
        this.listenerMap.put(genkey(dbName,tableName),iListener);
    }
    @Override
    public void onEvent(Event event) {
        EventType type = event.getHeader().getEventType();
        log.debug("event type : {}",type);
        if(type == EventType.TABLE_MAP){
            TableMapEventData data = event.getData();
            this.tableName = data.getTable();
            this.dbName = data.getDatabase();
            return;
        }
        if(type != EventType.EXT_UPDATE_ROWS && type != EventType.EXT_WRITE_ROWS && type != EventType.EXT_DELETE_ROWS ){
            return;
        }
        //表名 和库名 是否完成填充
        if(StringUtils.isEmpty(dbName) || StringUtils.isEmpty(tableName)){
            log.error("no meta data event");
            return;
        }
       //找出对应表有兴趣的监听器
        String key = genkey(this.dbName,this.tableName);
        IListener listener = this.listenerMap.get(key);
        if(listener == null){
            log.debug("skip :{}",key);
            return;
        }
        log.info("tigger event :{}",type.name());
        try {
            BinlogRowData rowData = buildRowData(event.getData());
            if(rowData == null){
                return;
            }
            rowData.setEventType(type);
            listener.onEvent(rowData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally {
            this.dbName = "";
            this.tableName = "";
        }

    }
    private List<Serializable[]> getAfterValues(EventData eventData){
        if(eventData instanceof WriteRowsEventData){
            return ((WriteRowsEventData) eventData).getRows();
        }
        if(eventData instanceof UpdateRowsEventData){
            return ((UpdateRowsEventData) eventData).getRows().stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }
        if(eventData instanceof DeleteRowsEventData){
            return ((DeleteRowsEventData) eventData).getRows();
        }

        return Collections.emptyList();
    }
    private BinlogRowData buildRowData(EventData eventData){
        TableTemplate table = templateHolder.getTable(tableName);
        if(table == null){
            log.warn("table {} not found",tableName);
        }
        List<Map<String, String>> afterMapList = new ArrayList<>();
        return  null;
    }
}

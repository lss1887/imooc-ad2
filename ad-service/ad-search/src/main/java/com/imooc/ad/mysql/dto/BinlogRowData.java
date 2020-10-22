package com.imooc.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * event
 */
@Data
public class BinlogRowData {
        // private EventHeader header;
        //    private EventData data;  EVENT
    private TableTemplate tableTemplate;
    //操作类型
    private EventType eventType;
    //map<列名 值>
    private List<Map<String,String>> after;
    private List<Map<String,String>> before;
}

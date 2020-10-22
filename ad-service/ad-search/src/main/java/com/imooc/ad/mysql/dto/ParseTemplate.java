package com.imooc.ad.mysql.dto;

import com.imooc.ad.mysql.constant.OpType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 对模板文件的解析
 */
@Data
public class ParseTemplate {

    private String database;
    //database  tableTempalte
    private Map<String,TableTemplate> tableTemplateMap = new HashMap<>();

    public  static ParseTemplate parse(Template template){
        ParseTemplate parseTemplate = new ParseTemplate();
        parseTemplate.setDatabase(template.getDatabases());

        List<JsonTable> tableList = template.getTableList();
        for (JsonTable table : tableList) {
            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(table.getTableName());
            tableTemplate.setLevel(table.getLevel().toString());
            parseTemplate.tableTemplateMap.put(table.getTableName(),tableTemplate);
            Map<OpType, List<String>> opTypeFileldSetMap = tableTemplate.getOpTypeFileldSetMap();

            //遍历操作类型对应的列
            for (JsonTable.Column column : table.getInsert()) {
                getAndCreateIfNeed(OpType.ADD,opTypeFileldSetMap, ArrayList::new).add(column.getColumn());
            }
            for (JsonTable.Column column : table.getUpdate()) {
                getAndCreateIfNeed(OpType.UPDATE,opTypeFileldSetMap, ArrayList::new).add(column.getColumn());
            }
            for (JsonTable.Column column : table.getDelete()) {
                getAndCreateIfNeed(OpType.DELETE,opTypeFileldSetMap, ArrayList::new).add(column.getColumn());
            }
        }
        return parseTemplate;
    }

    private static <T,R> R getAndCreateIfNeed(T key, Map<T,R> map, Supplier<R> factory){
        return map.computeIfAbsent(key,k-> factory.get());
    }
}

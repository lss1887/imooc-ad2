package com.imooc.ad.mysql;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.mysql.dto.ParseTemplate;
import com.imooc.ad.mysql.dto.TableTemplate;
import com.imooc.ad.mysql.dto.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TemplateHolder {

    private ParseTemplate template;
    @Autowired
    private  JdbcTemplate jdbcTemplate;
    private  static  final String SQL_SCHEMA = "select table_schema,table_name,column_name,ordinal_position from information_schema.COLUMNS WHERE table_schema = ? and table_name = ?";
    private  static  final  String  TEMPLATE_JSON= "template.json";

    public TableTemplate getTable(String tableName){
        return  template.getTableTemplateMap().get(tableName);
    }
    /**
     * TemplateHolder 加载时srping容器会自动执行
     */
    @PostConstruct
    private void init (){
        loadJson(TEMPLATE_JSON);
    }
    /**
     * 加载配置文件
     * @param path
     */
    private void loadJson(String path){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream(path);
            Template template = JSON.parseObject(is, Charset.defaultCharset(), Template.class);
            this.template = ParseTemplate.parse(template);
            loadMeta();
            is.close();
        } catch (IOException e) {
         log.error(e.getMessage());
         throw  new RuntimeException("fail to parse json file");
        }

    }

    /**
     *  索引到列名的映射
     */
    private  void loadMeta(){
        for (Map.Entry<String, TableTemplate> entry : template.getTableTemplateMap().entrySet()) {
            TableTemplate table = entry.getValue();
            List<String> insertFieIds = table.getOpTypeFileldSetMap().get(OpType.ADD);
            List<String> updateFieIds = table.getOpTypeFileldSetMap().get(OpType.UPDATE);
            List<String> deleteFieIds = table.getOpTypeFileldSetMap().get(OpType.DELETE);
            jdbcTemplate.query(SQL_SCHEMA,new Object[]{
                    template.getDatabase(),table.getTableName()
            },(rs,i) ->{
                int pos = rs.getInt("OPDINAL_POSIITION");
                String  colName = rs.getString("COLUMN_NAME");

                if((null != updateFieIds && updateFieIds.contains(colName))
                        || (null != insertFieIds && insertFieIds.contains(colName))
                        || (null != deleteFieIds && deleteFieIds.contains(colName)) ){
                    table.getPosMap().put(pos - 1,colName);
                }
                return null;
            });
        }
    }
}

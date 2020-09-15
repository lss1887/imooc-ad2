package com.imooc.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

public class BinlogServiceTest {

    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient(
        "127.0.0.1",
                3306,
                "root",
                "root"
        );
//        设定读取binlog 的文件 和位置 那么 client 将从这个位置开始监听 否则 client 会从 “头”开始读取 Binlog 文件 并监听
//        client.setBinlogFilename("binlog.000037");
////        client.setBinlogPosition();
//         includedColumns={0, 1, 2} 将列索引映射为 对应的字段
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if(data instanceof UpdateRowsEventData){
                System.out.println("Update-----------------");
                System.out.println(data.toString());
            }else if(data instanceof WriteRowsEventData){
                System.out.println("Write-----------------");
                System.out.println(data.toString());
            }else if(data instanceof DeleteRowsEventData){
                System.out.println("Delete-----------------");
                System.out.println(data.toString());
            }
        });
        client.connect();
    }
}

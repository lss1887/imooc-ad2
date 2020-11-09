package com.imooc.ad.mysql.runner;

import com.imooc.ad.mysql.BinlogClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 应用程序开启启动的程序
 */
@Slf4j
@Component
public class BinlogRunner implements CommandLineRunner {
    @Autowired
    private BinlogClient client;

    @Override
    public void run(String... args) throws Exception {
        log.info("coming in BinlogRunner");
        client.connect();
    }
}

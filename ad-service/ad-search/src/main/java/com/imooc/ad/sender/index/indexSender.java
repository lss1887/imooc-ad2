package com.imooc.ad.sender.index;

import com.imooc.ad.mysql.dto.MySqlRowData;
import com.imooc.ad.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("indexSender")
public class indexSender implements ISender {

    @Override
    public void sender(MySqlRowData rowData) {

    }
}

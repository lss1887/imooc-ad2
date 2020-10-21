package com.imooc.ad.mysql.listener;

import com.imooc.ad.mysql.dto.BinlogRowData;

/**
 * 监听与解析
 */
public interface IListener {
    void register();
    void onEvent(BinlogRowData eventData);

}

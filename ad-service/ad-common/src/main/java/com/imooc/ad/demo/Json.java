package com.imooc.ad.demo;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

public class Json {
    public static void main(String[] args) {
        String json = "[{\"date\":\"20200329\",\"cpm\":\"11\"},{\"date\":\"20200330\",\"cpm\":\"11\"},{\"date\":\"20200331\",\"cpm\":\"11\"},{\"date\":\"20200401\"},{\"date\":\"20200402\"},{\"date\":\"20200403\"},{\"date\":\"20200404\"},{\"date\":\"20200405\"},{\"date\":\"20200406\"},{\"date\":\"20200407\"},{\"date\":\"20200408\"},{\"date\":\"20200409\"},{\"date\":\"20200410\",\"cpm\":\"11\"},{\"date\":\"20200411\",\"cpm\":\"11\"},{\"date\":\"20200412\",\"cpm\":\"11\"},{\"date\":\"20200413\",\"cpm\":\"11\"},{\"date\":\"20200414\",\"cpm\":\"11\"},{\"date\":\"20200415\",\"cpm\":\"11\"},{\"date\":\"20200416\",\"cpm\":\"11\"},{\"date\":\"20200417\",\"cpm\":\"11\"},{\"date\":\"20200418\",\"cpm\":\"11\"},{\"date\":\"20200419\",\"cpm\":\"11\"},{\"date\":\"20200420\",\"cpm\":\"11\"},{\"date\":\"20200421\",\"cpm\":\"11\"},{\"date\":\"20200422\",\"cpm\":\"11\"}]";
        List<DateCpm> list = JSONArray.parseArray(json,DateCpm.class);

        for (DateCpm dateCpm : list) {
            System.out.println(dateCpm.getDate()+"___"+dateCpm.getCpm());

        }
    }
}

package com.imooc.ad.mysql.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        HashMap<String, List<String>> map = new HashMap<>();
        List<String> add = map.computeIfAbsent("add", k -> new ArrayList<>());
        System.out.println(add.add("1"));
        System.out.println(map.toString());

    }
}

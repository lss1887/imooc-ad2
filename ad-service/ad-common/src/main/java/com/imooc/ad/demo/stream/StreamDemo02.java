package com.imooc.ad.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        Stream<String> stream = list.stream().filter((e) -> {
            return e.startsWith("张");
        });
        System.out.println(stream.collect(Collectors.toList()));
    }
}

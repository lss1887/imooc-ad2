package com.imooc.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

public class CommonUtils {

    public static <K,V> V getOrCreate(K key, Map<K,V> map, Supplier<V> factory){
        return map.computeIfAbsent(key,k -> factory.get());
    }

    public static String stringConcat(String... args){
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
            builder.append("-");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}

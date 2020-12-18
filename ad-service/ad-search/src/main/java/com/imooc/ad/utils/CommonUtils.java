package com.imooc.ad.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
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
    //Tue Jan 01 08:00:00 CST 2019
    public static Date parseStringDate(String dateString){

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyy", Locale.US);
            return DateUtils.addHours(dateFormat.parse(dateString),-8);
        } catch (ParseException e) {
            log.error("parseStringDate error :{}",dateString);
        }
        return null;
    }
}

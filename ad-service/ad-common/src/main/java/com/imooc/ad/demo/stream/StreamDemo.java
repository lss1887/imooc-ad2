package com.imooc.ad.demo.stream;

import java.util.*;
import java.util.stream.Stream;

//steam流  过滤、映射、跳过、计数等多步操作 Stream流”其实是一个集合元素的函数模型
public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
    list.stream().filter((e) ->{return e.startsWith("张");}).filter((e) -> {return  e.length() == 3 ;}).forEach((e) ->{
    System.out.println(e); });
    list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(System.out :: println);
        LimitSteam();
    }

    public static void  createSteam(){
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Set<String> set = new HashSet<>();
        Stream<String> stream1 = set.stream();
    }
//    双列集合先得到单列集合
    public static void doubleCollection(){
        Map<String, String> map = new HashMap(5);

        Stream<String> stream = map.keySet().stream();
        Stream<String> stream1 = map.keySet().stream();
        Stream<Map.Entry<String, String>> stream2 = map.entrySet().stream(); //键值对像 集合
    }

//    流静态of方法,传入数组,获取流
    public static void getOfSteam(){
        String[] array = {"张无忌","张翠山","张三丰","张一山"};
        Stream<String> ss = Stream.of(array);

    }
    //Stream常用方法

//    fliter 过滤条件 产生新的流


//    cout 统计流中元素个数

    public static void getCount(){
        Stream<String> original = Stream.of("张1", "张2", "li3");
        Stream<String> result = original.filter(s -> s.startsWith("张"));
        System.out.println(result.count());
    }
//取用前几个：limit,限制截取,有限截取,产生新的流 取用前几个
//    Stream<T> limit(long maxSize);  参数是一个long型，如果集合当前长度大于参数则进行截取；否则不进行操作
//    跳过前几个：skip,跳过前几个,产生新的流
public static void LimitSteam(){
    Stream<String> original = Stream.of("张1", "张2", "li3");
    Stream<String> limit = original.limit(2);
    System.out.println(limit.count());
}


//映射：map,把调用方法的流变成另一个流,即把流的元素类型转换改变即可
//    该接口需要一个`Function`函数式接口参数，可以将当前流中的T类型数据转换为另一种R类型的流。
//R apply(T t);//通过T类型得到R类型东西,把一个东西变成转换成另一个东西,绑定死了,t绑定r,对应关系,映射
public static void getMapSteam(){
    Stream<String> original = Stream.of("10", "2", "3");
    Stream<Integer> limit = original.map(Integer::parseInt); //把元素变成整数 新的流

}

//    concat,静态方法,传入两个流,进行合并

//    Stream<String> streamA = Stream.of("张无忌");
//    Stream<String> streamB = Stream.of("张翠山");
//    Stream<String> result = Stream.concat(streamA, streamB);

//    forEach,遍历,消费每一个元素,一般用于打印元素
//Stream<String> stream = Stream.of("张无忌", "张三丰", "周芷若");
//        stream.forEach(System.out::println);
}


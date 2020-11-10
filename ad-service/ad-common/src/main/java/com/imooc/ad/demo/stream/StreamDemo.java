package com.imooc.ad.demo.stream;

import java.util.*;
import java.util.stream.Collectors;
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
        list.stream().filter((e) -> {
            return e.startsWith("张");
        }).filter((e) -> {
            return e.length() == 3;
        }).forEach((e) -> {
            System.out.println(e);
        });
        list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(System.out::println);
    }

    public static void createSteam() {
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Set<String> set = new HashSet<>();
        Stream<String> stream1 = set.stream();
    }

    //    双列集合先得到单列集合
    public static void doubleCollection() {
        Map<String, String> map = new HashMap(5);

        Stream<String> stream = map.keySet().stream();
        Stream<String> stream1 = map.keySet().stream();
        Stream<Map.Entry<String, String>> stream2 = map.entrySet().stream(); //键值对像 集合
    }

    //  流静态of方法,传入数组,获取流
    public static void getOfSteam() {
        String[] array = {"张无忌", "张翠山", "张三丰", "张一山"};
        Stream<String> ss = Stream.of(array);

    }
    //Stream常用方法

    /**
     * fliter 过滤条件 产生新的流
     *
     * @param args
     */
    public void byFilter(String[] args) {
        Stream<String> original = Stream.of("张无忌", "张三丰", "周芷若");
        // Stream<String> result = original.filter((s) -> {return s.startsWith("张");});//标准格式
        Stream<String> result = original.filter(s -> s.startsWith("张"));//省略格式
    }


    /**
     * 统计个数：count,统计流中元素个数
     */
    public static void getCount() {
        Stream<String> original = Stream.of("张1", "张2", "li3");
        Stream<String> result = original.filter(s -> s.startsWith("张"));
        System.out.println(result.count());
    }

    /**
     * 取用前几个：limit,限制截取,有限截取,产生新的流
     */
    public void limitSteam() {
        Stream<String> original = Stream.of("张1", "张2", "li3");
        Stream<String> limit = original.limit(2);
        System.out.println(limit.count());
    }


    /**
     * 跳过前几个：skip,跳过前几个,产生新的流
     */
    public void skipStream() {
        Stream<String> original = Stream.of("林1", "林2", "林3");
        Stream<String> result = original.skip(2);
        System.out.println(result.count()); //
    }


    /**
     * 映射：map,把调用方法的流变成另一个流,即把流的元素类型转换改变即可
     */
    public void getMapStream1() {
        Stream<String> original = Stream.of("10", "2", "3");
        Stream<Integer> limit = original.map(Integer::parseInt); //把元素变成整数 新的流

    }


    /**
     * 组合：concat,静态方法,传入两个流,进行合并
     */
    public void getConcatStream() {
        Stream<String> streamA = Stream.of("L1");
        Stream<String> streamB = Stream.of("L2");
        Stream<String> result = Stream.concat(streamA, streamB);
    }


    /**
     * forEach,遍历,消费每一个元素
     */
    public void getStreamForEach() {
        Stream<String> stream = Stream.of("张无忌", "张三丰", "周芷若");
        stream.forEach(System.out::println);
    }


    public void getMapStream() {
        Map<String, String> map = new HashMap<>();
        // ...
        Set<String> key = map.keySet();
        Set<String> value = (Set<String>) map.values();
        Set<Map.Entry<String, String>> entries = map.entrySet();

        Stream<String> keyStream = key.stream();
        Stream<String> valueStream = value.stream();
        Stream<Map.Entry<String, String>> entryStream = entries.stream();
    }


//    concat,静态方法,传入两个流,进行合并

//    Stream<String> streamA = Stream.of("张无忌");
//    Stream<String> streamB = Stream.of("张翠山");
//    Stream<String> result = Stream.concat(streamA, streamB);

//    forEach,遍历,消费每一个元素,一般用于打印元素
//Stream<String> stream = Stream.of("张无忌", "张三丰", "周芷若");
//        stream.forEach(System.out::println);


// 1. 第一个队伍只要名字为3个字的成员姓名；
// 2. 第一个队伍筛选之后只要前3个人；
// 3. 第二个队伍只要姓张的成员姓名；
// 4. 第二个队伍筛选之后不要前2个人；
// 5. 将两个队伍合并为一个队伍；
// 6. 根据姓名创建`Person`对象；
// 7. 打印整个队伍的Person对象信息。

    public void forExample() {

        List<String> one = new ArrayList<>();
        // ...添加元素

        List<String> two = new ArrayList<>();
        // ...添加元素

        // 第一个队伍只要名字为3个字的成员姓名；
        // 第一个队伍筛选之后只要前3个人；
        Stream<String> streamOne = one.stream().filter(s -> s.length() == 3).limit(3);//省略格式

        // 第二个队伍只要姓张的成员姓名；
        // 第二个队伍筛选之后不要前2个人；
        Stream<String> streamTwo = two.stream().filter(s -> s.startsWith("张")).skip(2);//省略..

        // 将两个队伍合并为一个队伍；
        // 根据姓名创建Person对象；
        // 打印整个队伍的Person对象信息。
        //Stream.concat(streamOne, streamTwo).map(Person::new).forEach(System.out::println);
        //上面的写法实际上是map(new Person(元素)),把元素变成Person类,新的流,不会,用下面写法也可以
        Stream.concat(streamOne, streamTwo).forEach((e) -> {
            // System.out.println(new Person(e));
        });
    }

    /**
     * 流的并发方法,跟下面的单列集合的并发流效果,
     */
    public void parallelStream() {
        Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();//流的并发方法
        //流的并发方法,跟下面的单列集合的并发流效果,本质上是一样的,都是并发
        stream.forEach((e) -> {
            System.out.println(e);//每次运行的结果有可能都不太一样,并发操作,一会执行这个,一个执行那个
        });
    }

    /**
     * 单列集合的并发流方法
     */
    public void parallelListStream() {
        ArrayList<String> al = new ArrayList<>();
        al.add("1");
        al.add("2");
        al.add("3");

        Stream<String> stream = al.parallelStream();//单列集合的并发流方法
        //每次运行的结果有可能都不太一样,并发操作,一会执行这个,一个执行那个
        stream.forEach(System.out::println);//方法引用,打印参数,这里是的参数是元素,即打印元素
    }

    //Stream流转换为集合或者数组


    /**
     * 流转为集合
     */
    public void streamToList() {
        Stream<String> stream = Stream.of("10", "20", "30", "40", "50");
        //流收集方法,传入收集工具类调用方法转换为集合 Collectors.toSet()  Collectors.toList()
        List<String> arr = stream.collect(Collectors.toList());//流已经变成一个集合,到达终点不能再变,流一去不复返!!!
        for (String s : arr) {
            System.out.println(s);
        }

    }

    //流转换数组    Object[] arr = stream.toArray();

    /**
     * 流调用方法转换为数组,并传入数组构造器引用,指定对应类型的数组
     */

    public void toArray() {
        Stream<String> stream = Stream.of("10", "20", "30", "40", "50");
        String[] arr = stream.toArray(String[]::new);//new String[];

        for (String s : arr) {
            System.out.println(s);
        }
    }

}


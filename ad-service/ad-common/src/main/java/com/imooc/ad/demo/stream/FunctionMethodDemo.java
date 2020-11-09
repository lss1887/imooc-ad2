package com.imooc.ad.demo.stream;

import java.util.function.Function;

//函数应用参数得到返回值,也有并且然后连接
//Function接口中最主要的抽象方法为：R apply(T t)，根据类型T的参数获取类型R的结果。
public class FunctionMethodDemo {

    //抽象方法：apply,应用,根据参数得到方法结果,即把什么东西变成什么东西
    public static void main(String[] args) {
        //使用的场景例如：将String类型转换为Integer类型。
        m((s)->{return Integer.parseInt(s);});//lambda表达式写法
        //通过方法引用的写法
        m(Integer::parseInt);
        method(Integer::parseInt, i -> i *= 10);
        String d= "ywj30";
        method2((s)->{return s.substring(s.length()-2);},(s)-> {return Integer.parseInt(s);},(s) ->{return s+100;},"ywj30");
    }

    private static void m(Function<String,Integer> f){
        Integer i = f.apply("13");
        System.out.println(i);//13
    }
    //    function andThen 是根据参数得到方法结果,即把什么东西变成什么东西
    private static void method(Function<String, Integer> one, Function<Integer, Integer> two) {
        int num = one.andThen(two).apply("10");
        System.out.println(num + 20);
    }

//            1. 将字符串截取数字年龄部分，得到字符串；
//            2. 将上一步的字符串转换成为int类型的数字；
//            3. 将上一步的int数字累加100，得到结果int数字。
    private static void method2(Function<String,String>f,Function<String,Integer> f2,Function<Integer,Integer> f3,String s ){
        int num = f.andThen(f2).andThen(f3).apply(s);
        System.out.println(num);
    }
}

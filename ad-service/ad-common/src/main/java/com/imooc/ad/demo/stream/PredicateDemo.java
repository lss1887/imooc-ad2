package com.imooc.ad.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate接口,测试参数真假,用来条件判断,还有与或非连接
 *
 * @author W9002575
 */
public class PredicateDemo {

    /**
     * //test,测试条件,返回真假
     *
     * @param predicate
     */
    private static void method(Predicate<String> predicate) {
        boolean veryLong = predicate.test("HelloWord");
        System.out.println(veryLong);
        System.out.println("字符串很长吗：" + veryLong);
    }


    /**
     * and,并且,判断两个条件是否同时成立
     *
     * @param one
     * @param two
     */
    private static void method2(Predicate<String> one, Predicate<String> two) {
        boolean isValid = one.and(two).test("HelloWord");
        System.out.println("字符串符合要求吗：" + isValid);
    }


    /**
     * or,或者,判断两个条件是否有一个或者两个成立
     *
     * @param one
     * @param two
     */
    private static void method(Predicate<String> one, Predicate<String> two) {
        boolean isValid = one.or(two).test("Helloworld");
        System.out.println("字符串符合要求吗：" + isValid);
    }

    /**
     * negate,取反,得到判断条件相反的结果
     *
     * @param predicate
     */
    private static void method3(Predicate<String> predicate) {
        boolean isValid = predicate.negate().test("HelloWord");
        System.out.println("字符串长吗：" + isValid);

    }


    /**
     * 姓名4个字的女生
     * @param predicate1
     * @param predicate2
     * @param arr
     */
    private static void method4(Predicate<String> predicate1, Predicate<String> predicate2, String[] arr) {
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (predicate1.and(predicate2).test(s)) {
                list.add(s);
            }
        }
        System.out.println(list);
    }


    //    predicate接口 判断条件  与或非链接
    public static void main(String[] args) {
        method(s -> s.length() > 5);
        method2(s -> s.contains("H"), s -> s.contains("W"));
        method3(s -> s.length() > 5);

        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};

        method4(s -> {
            return s.split(",")[1].contains("女");
        }, s -> {
            return s.split(",")[0].length() == 4;
        }, array);
    }

}

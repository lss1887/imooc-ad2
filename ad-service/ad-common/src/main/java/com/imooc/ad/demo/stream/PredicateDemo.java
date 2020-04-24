package com.imooc.ad.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

    private static void method(Predicate<String> predicate) {
        boolean veryLong = predicate.test("HelloWord");
        System.out.println(veryLong);
        System.out.println("字符串很长吗：" + veryLong);
    }



//    and 并且

    private  static void method2(Predicate<String> one,Predicate<String> two){
        boolean isValid = one.and(two).test("HelloWord");
        System.out.println("字符串符合要求吗：" + isValid);
    }


//negate,取反,得到判断条件相反的结果
private  static void method3(Predicate<String> predicate){
    boolean isValid = predicate.negate().test("HelloWord");
    System.out.println("字符串长吗：" + isValid);


}


//姓名4个字的女生

    private  static void method4(Predicate<String> predicate1,Predicate<String> predicate2,String[]arr){

        List<String> list = new ArrayList<>();

        for (String s : arr) {
            if(predicate1.and(predicate2).test(s)){
                list.add(s);
            }
        }
        System.out.println(list);


    }



    //    predicate接口 判断条件  与或非链接
    public static void main(String[] args) {
        method(s -> s.length() > 5);
        method2(s ->s.contains("H") ,s ->s.contains("W") );
        method3(s -> s.length() >5);

        String[] array = { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女" };

        method4(s ->{return s.split(",")[1].contains("女");},s -> {return  s.split(",")[0].length() ==4;},array);
    }
}

package com.imooc.ad.demo;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by linshisheng on 2020/03/31.
 * 迭代器
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Collection<String> coll = new LinkedList<String>();
        coll.add("打算");
        coll.add("飞翔");
        coll.add("吧");

         Iterator<String> it = coll.iterator();
         while (it.hasNext()){
             String s = it.next();
             System.out.println(s);
         }
    }
}

package com.imooc.ad.demo.file;

import java.io.FileInputStream;

public class FisRead {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("fos.txt");
      //定义变量 保存数据
        int b = 0;
        //读取数据到数组中
        byte[] bytes = new byte[2];
        while ((b = fis.read(bytes)) != -1){
            System.out.println(new String(bytes));
        }
        //关闭资源
        fis.close();
    }
}

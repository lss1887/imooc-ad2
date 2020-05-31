package com.imooc.ad.demo.file;

import java.io.File;
import java.io.IOException;

public class FileDemo {

//    Flie 的相对路径与绝对路径
public static void main(String[] args) throws IOException {
//    File file = new File("D:\\bbb.java");
//    System.out.println(file.getAbsoluteFile());
//
//    System.out.println(new File("bbb.java").getAbsolutePath());
//    System.out.println(file.exists());
    File txt = File.createTempFile("\\demo\\dd/tem2\\adx-java-demo", "txt");
    System.out.println(txt.getAbsolutePath());
}

}

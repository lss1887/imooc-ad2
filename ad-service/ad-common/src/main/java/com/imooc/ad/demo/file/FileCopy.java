package com.imooc.ad.demo.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("fos.txt");
        FileOutputStream fos = new  FileOutputStream("fos2.txt");
        int ch;
        byte[] bytes = new byte[2];
        while ((ch = fis.read(bytes)) != -1){
            fos.write(bytes,0,ch);
        }
        fis.close();
        fos.close();
    }
}

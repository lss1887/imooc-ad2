package com.imooc.ad.demo.file;

import java.io.FileOutputStream;

public class FosWrire {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("fos.txt");

        byte[] b ={97,98,99,100};
        for (int i = 0; i < b.length; i++) {
            fos.write(b[i]);
            fos.write("\r\n".getBytes());
        }
        fos.flush();
        fos.close();
    }
}

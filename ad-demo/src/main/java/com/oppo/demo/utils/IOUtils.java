package com.oppo.demo.utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IOUtils extends HttpServlet {

    public   void down(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //得到要下载的文件名
        String fileName ="autoMarkingTemplate.xlsx";
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = this.getServletContext().getRealPath("/download");
        //        处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path+File.separator+fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            return;
        }

        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream fis = new FileInputStream(path + File.separator + fileName);
        //创建输出流
        OutputStream fos = response.getOutputStream();
        //设置缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //输入通道
        FileChannel readChannel = fis.getChannel();
        //输出通道
        FileChannel writeChannel = ((FileOutputStream)fos).getChannel();
        while(true){
            buffer.clear();
            int len = readChannel.read(buffer);//读入数据
            if(len < 0){
                break;//传输结束
            }
            buffer.flip();
            writeChannel.write(buffer);//写入数据
        }
        //关闭输入流
        fis.close();
        //关闭输出流
        fos.close();
        System.out.println("============================");
    }

    //通过文件名和存储上传文件根目录找出要下载的文件的所在路径
    private  static  String findFileSavePathByFileName(String fileName,String fileSaveRootPath){
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        String dir = fileSaveRootPath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }

}

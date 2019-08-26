package com.learn.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadServlet",urlPatterns = "/file/UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ServletFileUpload fileUpload=new ServletFileUpload(new DiskFileItemFactory());//文件上传对象
        List<FileItem> parseRequest=null;
        String upload = request.getServletContext().getRealPath("upload");//当前项目下的upload 绝对路径
        List<String> newFileNames=new ArrayList<>();
        try {
            parseRequest = fileUpload.parseRequest(request);//解析请求
            for (FileItem fileItem:parseRequest){
                if (!fileItem.isFormField()){
                    String newFileName = getFileName(fileItem.getName());
                    BufferedInputStream bufferedInputStream=new BufferedInputStream(fileItem.getInputStream());
                    BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(upload+"/"+newFileName,true));
                    byte[] bytes=new byte[1024];
                    int len=0;
                    while((len=bufferedInputStream.read(bytes))>0){
                        bufferedOutputStream.write(bytes,0,len);
                    }
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    newFileNames.add(newFileName);
                }
            }
            request.setAttribute("newFileNames",newFileNames);
            request.getRequestDispatcher("/file/Success.jsp").forward(request,response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
    //构建新文件名
    public String getFileName(String fileName){
        String substring = fileName.substring(fileName.lastIndexOf("."));//得到扩展名
        String newFileName = UUID.randomUUID().toString() + substring;
        return newFileName;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

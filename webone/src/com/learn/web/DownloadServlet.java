package com.learn.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "DownloadServlet" ,urlPatterns = "/file/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fileName = request.getParameter("fileName");
        String upload = request.getServletContext().getRealPath("upload");
        File file=new File(upload,fileName);//构造一个绝对路径的文件地址
        response.setContentType("application/octet-stream");//对回应的固定设置
        response.addHeader("Content-Disposition", "attachment;filename="+fileName);//对回应的固定设置
        BufferedInputStream br=new BufferedInputStream(new FileInputStream(file));//获取文件输入流
        BufferedOutputStream bo=new BufferedOutputStream(response.getOutputStream());//由response获取输出流
        byte[] byts=new byte[1024];
        int len=-1;
        while((len=br.read(byts))>0){
            bo.write(byts,0,len);
        }
        br.close();
        bo.close();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

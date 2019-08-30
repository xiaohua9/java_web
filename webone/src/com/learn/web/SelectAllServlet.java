package com.learn.web;

import com.google.gson.Gson;
import com.learn.entity.User;
import com.learn.service.UserService;
import com.learn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SelectAllServlet",urlPatterns = "/login/SelectAllServlet")
public class SelectAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //响应ajax请求查所有数据
        UserService service=new UserServiceImpl();
        List<User> users = service.selectAll(1, 30);
        Gson gson=new Gson();
        String s = gson.toJson(users);
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(s);//将数据回应给ajax请求
        writer.close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
}

package com.learn.web;

import com.learn.dao.UserDaoI;
import com.learn.dao.impl.UserDaoImpl;
import com.learn.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SelectOneServlet",urlPatterns = "/login/SelectOneServlet")
public class SelectOneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoI dao=new UserDaoImpl();
        String userName = request.getParameter("userName");
        User select = dao.select(userName);
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        if (select==null){
            writer.print("名字可用");
        }else {
            writer.print("用户已存在");
        }
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
}

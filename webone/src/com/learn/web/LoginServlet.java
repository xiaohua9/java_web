package com.learn.web;

import com.learn.dao.impl.UserDaoImpl;
import com.learn.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");//获取表单元素值
        String userPwd = req.getParameter("userPwd");
        User user=new User(userName,userPwd);//当前试图登陆的用户
        UserDaoImpl dao=new UserDaoImpl();//构建一个用户表的数据访问对象
        List<User> users = dao.selectAll();//获取所有的用户列表
        if (users.contains(user)){
            resp.sendRedirect("/login/success.jsp");//返回登录成功页面
        }else {
            resp.sendRedirect("/login/login.jsp");//用户不存在，继续留在登录页面
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");//获取表单元素值
        String userPwd = req.getParameter("userPwd");
        User user=new User(userName,userPwd);//当前试图登陆的用户
        UserDaoImpl dao=new UserDaoImpl();//构建一个用户表的数据访问对象
        List<User> users = dao.selectAll();//获取所有的用户列表
        if (users.contains(user)){
            resp.sendRedirect("/login/success.jsp");
        }else {
            resp.sendRedirect("/login/login.jsp");
        }
    }

}

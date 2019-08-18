package com.learn.web;

import com.learn.dao.impl.UserDaoImpl;
import com.learn.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);////转向post请求的响应
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");//获取表单元素值
        String userPwd = req.getParameter("userPwd");
        User user=new User(userName,userPwd);//当前试图登陆的用户
        UserDaoImpl dao=new UserDaoImpl();//构建一个用户表的数据访问对象
        List<User> users = dao.selectAll();//获取所有的用户列表
        if (users.contains(user)){
            HttpSession session = req.getSession();//获取会话
            session.setAttribute("list",users);//将数据封装到会话中
            resp.sendRedirect("/login/success.jsp");
        }else {//用户名和密码在数据库中不存在
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print("<script>alert('用户名或密码错误');location.href='/login/login.jsp'</script>");
        }
    }

}

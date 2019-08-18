package com.learn.web;

import com.learn.dao.impl.UserDaoImpl;
import com.learn.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//删除组件
@WebServlet(urlPatterns = "/login/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将前端的请求数据解码
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");//获取超链接提交的需要删除的用户名
        userName=new String(userName.getBytes("iso-8859-1"),"UTF-8");
        UserDaoImpl dao=new UserDaoImpl();
        User user=new User(userName);
        int delete = dao.delete(user);//删除成功返回1，否则为0
        //然后将删除后的数据返回
        //因为删除是针对确定已经有的数据进行，所以无需判断，直接返回结果
        List<User> users = dao.selectAll();
        //构建会话，封装数据，伴随着页面一同返给请求方
        HttpSession session = req.getSession();
        session.setAttribute("list",users);
        resp.sendRedirect("/login/success.jsp");

    }
}

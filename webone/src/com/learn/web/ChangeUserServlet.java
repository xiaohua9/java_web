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

//更改信息服务器组件
public class ChangeUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//先将请求数据以utf-8编码
        User user=new User(req.getParameter("userName"),req.getParameter("userPassword"));//以提交信息构造对象
        UserDaoImpl dao=new UserDaoImpl();
        int update = dao.update(user);
        if (update>0){
            List<User> users = dao.selectAll();//获取当前数据库的所有信息
            HttpSession session = req.getSession();//获取与请求方的会话
            session.setAttribute("list",users);//封装数据到会话中
            resp.sendRedirect("/login/success.jsp");//回馈页面
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            //提示错误信息，并返回更改页面
            resp.getWriter().print("<script>alert('你要更改的用户不存在');location.href='/login/updateUser.jsp'</script>");
        }
    }
}

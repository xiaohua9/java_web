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

@WebServlet(name = "UserServlet",urlPatterns = "/login/userServlet")
public class UserServlet extends HttpServlet {
    //请求的总控制中心
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");//获取识别码
        if ("login".equals(method)){
            this.doLogin(request,response);
        }else if ("add".equals(method)){
            this.doAdd(request,response);
        }else if ("delete".equals(method)){
            this.doDelete(request,response);
        }else if ("change".equals(method)){
            this.doChange(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    //登录服务中心
    protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    //增加服务中心
    protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将前端的请求数据解码
        req.setCharacterEncoding("utf-8");
        //获取前端提交的信息
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        //将前端数据构成一个用户对象
        User user=new User(userName,userPassword);
        //构建一个用户的数据访问对象
        UserDaoImpl dao=new UserDaoImpl();
        //将数据加入数据库，flag接收影响的行数
        int flag=dao.insert(user);
        if (flag>0){
            //插入成功，直接登录
            HttpSession session = req.getSession();//获取会话
            List<User> users = dao.selectAll();//获取全部数据
            session.setAttribute("list",users);//将数据封装到会话中
            resp.sendRedirect("/login/success.jsp");
        }else {//用户已经存在，继续留在登录页面
            resp.setContentType("text/html;charset=UTF-8");//设置返回内容的编码格式
            //给出提示，返回注册页面
            resp.getWriter().print("<script>alert('用户已经存在');location.href='/login/addUser.jsp'</script>");

        }

    }
    //删除服务中心
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    //更改服务中心
    protected void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

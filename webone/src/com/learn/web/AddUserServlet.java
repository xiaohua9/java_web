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

//注册用户的服务器组件
@WebServlet(urlPatterns = "/login/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);//转向post请求的响应
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}

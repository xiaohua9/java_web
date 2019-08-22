package com.learn.web;
import com.learn.entity.User;
import com.learn.service.impl.UserServiceImpl;
import com.learn.utils.EmptyUtils;
import com.learn.utils.PageBean;
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
    UserServiceImpl userServiceImpl=new UserServiceImpl();//获取服务层对象
    //请求的总控制中心
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将前端的请求数据解码
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");//获取识别码
        if ("login".equals(method)){
            this.doLogin(request,response);
        }else if ("add".equals(method)){
            this.doAdd(request,response);
        }else if ("delete".equals(method)){
            this.doDelete(request,response);
        } else if ("change".equals(method)){
            this.doChange(request,response);
        }else {
            this.doFindAll(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    //登录服务中心
    protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");//获取表单元素值
        String userPassword = req.getParameter("userPwd");
        User user=this.userServiceImpl.login(userName,userPassword);//调用服务层登录
        if (user!=null){
            HttpSession session = req.getSession();//获取会话
            session.setAttribute("currentUser",user);//将当前登录的用户封装到会话中，用于后续网页的展示与判断（多页面展示）
            resp.sendRedirect("/login/userServlet");//执行查找所有的组件
        }else {//用户名和密码在数据库中不存在
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print("<script>alert('用户名或密码错误');location.href='/login/Login.jsp'</script>");
        }
    }
    //增加服务中心
    protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端提交的信息
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String userGender = req.getParameter("userGender");
        String userAge = req.getParameter("userAge");
        String userAddress = req.getParameter("userAddress");
        String userBirthday = req.getParameter("userBirthday");
        //将前端数据构成一个用户对象
        User user=new User(userName,userPassword,userGender,Integer.parseInt(userAge),userAddress,userBirthday);
        int flag = this.userServiceImpl.insert(user);//将数据加入数据库，flag接收影响的行数
        if (flag>0){
            //插入成功，直接登录
            HttpSession session = req.getSession();//获取会话
            session.setAttribute("currentUser",user);//将当前登录的用户封装到会话中，用于后续网页的展示与判断（多页面展示）
            resp.sendRedirect("/login/userServlet");//执行查找所有的组件
        }else {//用户已经存在，继续留在登录页面
            resp.setContentType("text/html;charset=UTF-8");//设置返回内容的编码格式
            //给出提示，返回注册页面
            resp.getWriter().print("<script>alert('用户已经存在');location.href='/login/AddUser.jsp'</script>");

        }
    }
    //删除服务中心
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过判断是否具有当前登录用户，可以过滤掉直接地址栏访问的请求
        if (req.getSession().getAttribute("currentUser")!=null) {
            String userName = req.getParameter("userName");//获取超链接提交的需要删除的用户名
            userName=new String(userName.getBytes("iso-8859-1"),"UTF-8");
            User user=new User(userName);//构造临时用户
            this.userServiceImpl.delete(user);//删除
            //重定向查所有组件
            resp.sendRedirect("/login/userServlet");//执行查找所有的组件
        }else {//如果有人试图想不登录删除，我就让他去登录
            resp.sendRedirect("/login/Login.jsp");
        }
    }
    //更改服务中心
    protected void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //以提交信息构造对象
        User user=new User(req.getParameter("userName"),req.getParameter("userPassword"),req.getParameter("userGender"),Integer.parseInt(req.getParameter("userAge")),req.getParameter("userAddress"),req.getParameter("userBirthday"));
        int update = this.userServiceImpl.update(user);//修改
        if (update>0){
            resp.sendRedirect("/login/userServlet");//执行查找所有的组件
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            //提示错误信息，并返回更改页面
            resp.getWriter().print("<script>alert('你要更改的用户不存在');location.href='/login/UpdateUser.jsp'</script>");
        }
    }
    //查找服务中心
    protected void doFindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBean pageBean = new PageBean();//构造一个页面显示信息类对象
        String currentUserAddress = req.getParameter("userAddress");//获取当前用户地址条件
        pageBean.setRows(this.userServiceImpl.selectCount(currentUserAddress));//获取总行数，并赋值,必须要放在这个位置，至少不能放在当前页数设置后面
        String page = req.getParameter("page");//获取前端提交过来的页数
        if (!EmptyUtils.isEmpty(page)){//在不为空的情况下进行当前页数设置
            int i = Integer.parseInt(page);
            pageBean.setCurrentPage(i);
        }
        List<User> users = this.userServiceImpl.selectAll(pageBean.getCurrentPage(),pageBean.getPageSize(),currentUserAddress);//获取分页数据
        pageBean.setUser(users);//将查到的数据赋值进页面对象
        List<Object> userAddress = this.userServiceImpl.selectColumn("userAddress");//获取地址,准确地写数据库的字段名
        req.setAttribute("userAddress",userAddress);//包装地址数据,用于条件选择
        req.setAttribute("pageBean",pageBean);//包装页面数据和查询结果
        req.setAttribute("currentUserAddress",currentUserAddress);//将前端提交的条件返回用于回显
        req.getRequestDispatcher("/login/Success.jsp").forward(req,resp);
    }
}

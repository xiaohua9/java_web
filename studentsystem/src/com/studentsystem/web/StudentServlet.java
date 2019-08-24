package com.studentsystem.web;

import com.studentsystem.entity.Clazz;
import com.studentsystem.entity.Student;
import com.studentsystem.service.ClazzServiceI;
import com.studentsystem.service.StudentServiceI;
import com.studentsystem.service.impl.ClazzServiceImpl;
import com.studentsystem.service.impl.StudentServiceImpl;
import com.studentsystem.utils.EmptyUtils;
import com.studentsystem.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet",urlPatterns = "/view/StudentServlet")
public class StudentServlet extends HttpServlet {
    StudentServiceI studentService=new StudentServiceImpl();//获取学生服务层对象
    ClazzServiceI clazzService=new ClazzServiceImpl();//获取班级服务对象
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将前端的请求数据解码
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");//获取识别码
        if ("add".equals(method)){
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
    //增加服务中心
    protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端提交的信息
        String studentName = req.getParameter("studentName");
        String age = req.getParameter("age");
        String boreDate = req.getParameter("boreDate");
        String classNo = req.getParameter("classNo");
        //将前端数据构成一个用户对象
        Student student = new Student(studentName, Integer.parseInt(age), boreDate, Integer.parseInt(classNo));
        int flag = this.studentService.insert(student);//将数据加入数据库，flag接收影响的行数
        if (flag>0){
            //插入成功
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('新增成功');location.href='/view/StudentServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置返回内容的编码格式
            //给出提示
            resp.getWriter().print("<script>alert('新增失败');location.href='/view/StudentServlet'</script>");

        }
    }
    //删除服务中心
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentNo = req.getParameter("studentNo");//获取超链接提交的需要删除的学号
        Student student = new Student(Integer.parseInt(studentNo));//构造临时学生
        this.studentService.delete(student);//删除
        resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
        resp.getWriter().print("<script>alert('删除成功');location.href='/view/StudentServlet'</script>");
    }
    //更改服务中心
    protected void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //以提交信息构造对象
        Student student = new Student(Integer.parseInt(req.getParameter("studentNo")), req.getParameter("studentName"), Integer.parseInt(req.getParameter("age")), req.getParameter("boreDate"), Integer.parseInt(req.getParameter("classNo")));
        int update = this.studentService.update(student);//修改
        if (update>0){
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('修改成功');location.href='/view/StudentServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            //提示错误信息
            resp.getWriter().print("<script>alert('修改失败');location.href='/view/StudentServlet'</script>");
        }
    }
    //查找全部
    protected void doFindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBean pageBean = new PageBean();//构造一个页面显示信息类对象
        String currentClassNo = req.getParameter("classNo");//获取当前学生班级
        pageBean.setRows(this.studentService.selectCount(currentClassNo));//获取总行数，并赋值,必须要放在这个位置，至少不能放在当前页数设置后面
        String page = req.getParameter("page");//获取前端提交过来的页数
        if (!EmptyUtils.isEmpty(page)){//在不为空的情况下进行当前页数设置
            int i = Integer.parseInt(page);
            pageBean.setCurrentPage(i);
        }
        List<Student> students = this.studentService.selectAll(pageBean.getCurrentPage(),pageBean.getPageSize(),currentClassNo);//获取分页数据
        pageBean.setStudent(students);//将查到的数据赋值进页面对象
        List<Clazz> clazzes = this.clazzService.selectAll();//获取所有班级
        req.getSession().setAttribute("clazzes",clazzes);//包装班级数据,用于条件选择,由于每个页面都要进行班级编号映射，故session
        req.setAttribute("pageBean",pageBean);//包装页面数据和查询结果
        req.setAttribute("currentClassNo",currentClassNo);//将前端提交的条件返回用于回显
        req.getRequestDispatcher("/view/StudentInfo.jsp").forward(req,resp);
    }
}




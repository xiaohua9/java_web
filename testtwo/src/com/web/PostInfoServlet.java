package com.web;

import com.entity.PostInfo;
import com.entity.Topic;
import com.service.PostInfoServiceI;
import com.service.TopicServiceI;
import com.service.impl.PostInfoServiceImpl;
import com.service.impl.TopicServiceImpl;
import com.utils.EmptyUtils;
import com.utils.PageBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "PostInfoServlet",urlPatterns = "/view/PostInfoServlet")
public class PostInfoServlet extends HttpServlet {
    PostInfoServiceI postInfoService=new PostInfoServiceImpl();//获取帖子服务层对象
    TopicServiceI topicService=new TopicServiceImpl();//获取类型服务对象
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将前端的请求数据解码
        request.setCharacterEncoding("utf-8");
        String method=null;
        if (ServletFileUpload.isMultipartContent(request)){//多媒体提交
            ServletFileUpload fileUpload=new ServletFileUpload(new DiskFileItemFactory());//文件上传对象
            List<FileItem> parseRequest=null;
            try {
                parseRequest = fileUpload.parseRequest(request);//解析请求
                request.setAttribute("parseRequest",parseRequest);//由于请求多媒体只能被解析一次，故以此传递
                for (FileItem fileItem : parseRequest) {
                    if (fileItem.isFormField()) {//普通表单项
                        String fieldName = fileItem.getFieldName();//表单名
                        String stringValue = fileItem.getString("utf-8");//表单值
                        //针对各个属性依次赋值
                        if ("method".equals(fieldName)) {
                            method=stringValue;//获取识别码
                            break;
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {//非多媒体提交
            method = request.getParameter("method");//获取识别码
        }
        if ("add".equals(method)){
            this.doAdd(request,response);
        }else if ("delete".equals(method)){
            this.doDelete(request,response);
        } else if ("change".equals(method)){
            this.doChange(request,response);
        }else if("find".equals(method)){
            this.doFind(request,response);
        }else {
            this.doFindAll(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
    //增加服务中心
    protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FileItem> parseRequest=null;
        String upload = req.getServletContext().getRealPath("upload");//当前项目下的upload 绝对路径
        PostInfo postInfo=new PostInfo();//先创建一个没有赋值的
        try {
            parseRequest = ( List<FileItem>)req.getAttribute("parseRequest");//解析请求
            for (FileItem fileItem:parseRequest){
                if (fileItem.isFormField()){//普通表单项
                    String fieldName = fileItem.getFieldName();//表单名
                    String stringValue = fileItem.getString("utf-8");//表单值
                    //针对各个属性依次赋值
                    if ("title".equals(fieldName)){
                        postInfo.setTitle(stringValue);
                    }
                    if ("postTime".equals(fieldName)){
                        postInfo.setPostTime(stringValue);
                    }
                    if ("clickNum".equals(fieldName)){
                        postInfo.setClickNum(Integer.parseInt(stringValue));
                    }
                    if ("content".equals(fieldName)){
                        postInfo.setContent(stringValue);
                    }
                    if ("topicId".equals(fieldName)){
                        postInfo.setTopicId(Integer.parseInt(stringValue));
                    }
                }else {//文件
                    String newFileName = getFileName(fileItem.getName());
                    postInfo.setPic(newFileName);//给文件名属性赋值
                    File file=new File(upload+"/"+newFileName);
                    try {
                        fileItem.write(file);//将数据写入文件
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int flag = this.postInfoService.insert(postInfo);//将数据加入数据库，flag接收影响的行数
        if (flag>0){
            //插入成功
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('新增成功');location.href='/view/PostInfoServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置返回内容的编码格式
            //给出提示
            resp.getWriter().print("<script>alert('新增失败');location.href='/view/PostInfoServlet'</script>");
        }
    }
    //删除服务中心
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");//获取超链接提交的需要删除的学号
        PostInfo postInfo = new PostInfo(Integer.parseInt(id));//构造临时贴
        int delete = this.postInfoService.delete(postInfo);//删除
        resp.getWriter().print(delete);//将影响的行数回应给ajax请求
    }
    //更改服务中心
    protected void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletFileUpload fileUpload=new ServletFileUpload(new DiskFileItemFactory());//文件上传对象
        List<FileItem> parseRequest=null;
        String upload = req.getServletContext().getRealPath("upload");//当前项目下的upload 绝对路径
        PostInfo postInfo=new PostInfo();//先创建一个没有赋值的
        try {
            parseRequest = ( List<FileItem>)req.getAttribute("parseRequest");//解析请求
            for (FileItem fileItem:parseRequest){
                if (fileItem.isFormField()){//普通表单项
                    String fieldName = fileItem.getFieldName();//表单名
                    String stringValue = fileItem.getString("utf-8");//表单值
                    //针对各个属性依次赋值
                    if ("id".equals(fieldName)){
                        postInfo.setId(Integer.parseInt(stringValue));
                    }
                    if ("title".equals(fieldName)){
                        postInfo.setTitle(stringValue);
                    }
                    if ("postTime".equals(fieldName)){
                        postInfo.setPostTime(stringValue);
                    }
                    if ("clickNum".equals(fieldName)){
                        postInfo.setClickNum(Integer.parseInt(stringValue));
                    }
                    if ("content".equals(fieldName)){
                        postInfo.setContent(stringValue);
                    }
                    if ("topicId".equals(fieldName)){
                        postInfo.setTopicId(Integer.parseInt(stringValue));
                    }
                }else {//文件
                    String oldFileName=fileItem.getName();
                    if (!EmptyUtils.isEmpty(oldFileName)){
                        //说明用户进行了头像更新
                        String newFileName = getFileName(fileItem.getName());
                        postInfo.setPic(newFileName);//给文件名属性赋值
                        File file=new File(upload+"/"+newFileName);
                        try {
                            fileItem.write(file);//将数据写入文件
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int update = this.postInfoService.update(postInfo);//修改
        System.out.println("zhe"+postInfo);
        if (update>0){
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('修改成功');location.href='/view/PostInfoServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            //提示错误信息
            resp.getWriter().print("<script>alert('修改失败');location.href='/view/PostInfoServlet'</script>");
        }
    }
    //查找全部
    protected void doFindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBean pageBean = new PageBean();//构造一个页面显示信息类对象
        String currentShortTitle = req.getParameter("currentShortTitle");//获取当前条件
        String currentTopicId = req.getParameter("currentTopicId");
        String currentBegain = req.getParameter("currentBegain");
        String currentEnd = req.getParameter("currentEnd");
        if (EmptyUtils.isEmpty(currentBegain)){//默认显示点击数1-100
            currentBegain=""+0;
        }
        if (EmptyUtils.isEmpty(currentEnd)){
            currentEnd=""+100;
        }
        pageBean.setRows(this.postInfoService.selectCount(currentShortTitle,currentTopicId,currentBegain,currentEnd));//获取总行数，并赋值,必须要放在这个位置，至少不能放在当前页数设置后面
        String page = req.getParameter("page");//获取前端提交过来的页数
        if (!EmptyUtils.isEmpty(page)){//在不为空的情况下进行当前页数设置
            int i = Integer.parseInt(page);
            pageBean.setCurrentPage(i);
        }
        List<PostInfo> postInfo = this.postInfoService.selectAll(pageBean.getCurrentPage(),pageBean.getPageSize(),currentShortTitle,currentTopicId,currentBegain,currentEnd);//获取分页数据
        pageBean.setPostInfo(postInfo);//将查到的数据赋值进页面对象
        List<Topic> topics = this.topicService.selectAll();//获取所有类型
        req.getSession().setAttribute("topics",topics);//包装类型数据,用于条件选择
        req.setAttribute("pageBean",pageBean);//包装页面数据和查询结果
        req.setAttribute("currentShortTitle",currentShortTitle);//将前端提交的条件返回用于回显
        req.setAttribute("currentTopicId",currentTopicId);
        req.setAttribute("currentBegain",currentBegain);
        req.setAttribute("currentEnd",currentEnd);
        req.getRequestDispatcher("/view/PostInfo.jsp").forward(req,resp);
    }
    //查找一个
    protected void doFind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PostInfo postInfo = postInfoService.select(id);
        req.setAttribute("postInfo",postInfo);
        req.getRequestDispatcher("/view/OnePostInfo.jsp").forward(req,resp);
    }
    //构建新文件名
    public String getFileName(String fileName){
        String substring = fileName.substring(fileName.lastIndexOf("."));//得到扩展名
        String newFileName = UUID.randomUUID().toString() + substring;
        return newFileName;
    }
}




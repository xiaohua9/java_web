package com.learn.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
/*字符编码转换过滤器*/
/*@WebFilter(filterName = "MyFilter",urlPatterns = "/filterExercise/*")*/
public class MyFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;//转换成子接口，获取请求方法
        String method = request.getMethod();
        if ("post".equalsIgnoreCase(method)){
            req.setCharacterEncoding("utf-8");
            chain.doFilter(req, resp);
        }else {
            Charset charset = new Charset(request);
            chain.doFilter(charset, resp);
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }
}
class Charset extends HttpServletRequestWrapper{
    public Charset(HttpServletRequest request) {
        super(request);
    }
    @Override
    public String getParameter(String name) {//重写方法，对获取字符的方法进行统一的编码转换
        String parameter = super.getParameter(name);
        String newParameter=null;
        try {
            newParameter=new String(parameter.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newParameter;
    }
}

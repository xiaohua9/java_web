package com.learn.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*权限控制过滤器*/
@WebFilter(filterName = "PermissionFilter",urlPatterns = "/filterExercise/Success.jsp")
public class PermissionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;//先将请求和回应向下转型
        HttpServletResponse response=(HttpServletResponse)resp;
        String name = request.getParameter("name");
        if ("小华".equals(name)){
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect("/filterExercise/Login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

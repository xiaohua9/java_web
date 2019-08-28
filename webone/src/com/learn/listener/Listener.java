package com.learn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
/*网页在线人数的监听器应用*/
@WebListener()
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    ServletContext servletContext;//web程序的作用域对象
    public Listener() {
    }
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
    }
    public void contextDestroyed(ServletContextEvent sce) {
    }
    public void sessionCreated(HttpSessionEvent se) {
        Integer num = (Integer) servletContext.getAttribute("num");
        if (num==null){//说明是第一个会话
            servletContext.setAttribute("num",new Integer(1));
        }else {//不是第一个会话，直接加1
            num++;
            servletContext.setAttribute("num",num);
        }
    }
    public void sessionDestroyed(HttpSessionEvent se) {
        Integer num = (Integer) servletContext.getAttribute("num");
        num--;//在会话销毁的时候减1
        servletContext.setAttribute("num",num);
    }
    public void attributeAdded(HttpSessionBindingEvent sbe) {
    }
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
    }
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
    }
}

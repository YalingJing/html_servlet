package com.login.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.LinkedList;
import java.util.List;

public class onlineListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {
    private ServletContext application = null;

    @Override
    public void contextInitialized(ServletContextEvent arg) {
        //初始化一个application对象
        this.application = arg.getServletContext();
        //设置一个列表属性，用于保存在线用户名
        this.application.setAttribute("online", new LinkedList<String>());
    }

    //往会话中添加属性时会回调的方法
    @Override
    public void attributeAdded(HttpSessionBindingEvent arg) {
        //取得用户名列表
        List<String> online = (List<String>) this.application.getAttribute("online");
        if ("username".equals(arg.getName())) {
            //将当前用户名添加到列表中
            online.add((String) arg.getValue());
        }
        //将添加后的列表重新设置到application属性中
        this.application.setAttribute("online", online);
    }


    //会话销毁时会回调的方法
    @Override
    public void sessionDestroyed(HttpSessionEvent arg) {
        //取得用户名列表
        List<String> online = (List<String>) this.application.getAttribute("online");
        //取得当前用户名
        String username = (String) arg.getSession().getAttribute("username");
        //将此用户名从列表中删除
        online.remove(username);
        //将删除后的列表重新设置到application属性中
        this.application.setAttribute("online", online);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
}

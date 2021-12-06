package com.login.servlet;

import com.login.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginSessionServlet", value = "/LoginSessionServlet")
public class LoginSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html; charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("check_code");
        String saveCode = (String) request.getSession().getAttribute("check_code");
        PrintWriter p = response.getWriter();
        //假设正确的用户名是13890794742
        // 密码是201931061165jing
        if(("13890794742").equals(username)&&("201931061165jing").equals(password)&&checkCode.equals(saveCode)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("username",username);
            response.sendRedirect("/myweb_servlet/indexServlet");
        }
        else if(("18848215712").equals(username)&&("java_ee_jingyalin").equals(password)&&checkCode.equals(saveCode)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("username",username);
            response.sendRedirect("/myweb_servlet/indexServlet");
        }
        else if(checkCode.equals(saveCode)){
            p.print("<h1 align='center'>用户名或者密码错误，登陆失败</h1>"+"<br>");
            p.print("<p align='center'>3s后将重新回到登陆页面</p>");
            response.setHeader("Refresh", "3,URl=http://localhost:8080/myweb_servlet/login1.html");
        }
        else{
            p.print("<h1 align='center'>验证码错误，登陆失败</h1>"+"<br>");
            p.print("<p align='center'>3s后将重新回到登陆页面</p>");
            response.setHeader("Refresh", "3,URl=http://localhost:8080/myweb_servlet/login1.html");
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

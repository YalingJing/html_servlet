package com.login.servlet;

import com.login.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "indexServlet", value = "/indexServlet")
public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码问题
        //response.setContentType("text/html; charset=utf-8");
        //创建或者获取保存用户信息的Session对象
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user == null){
            response.getWriter().print("<h3 align='center'>您还没有登陆，请<a href = '/myweb_servlet/login1.html'>登陆</a></h3>");
        }
        else{
            response.getWriter().print("<h1 align='center'>您已登陆，欢迎你！</h1>"+"<br>");
            response.getWriter().print("<h4>用户：</h4>" + user.getUsername() +"<br>");
            response.getWriter().print("<h4>密码：</h4>" + user.getPassword() +"<br>");
            response.getWriter().print("<a href = '/myweb_servlet/LogoutServlet' align='center'>退出</a>");
            List<String> online = (List<String>)getServletContext().getAttribute("online");
            response.getWriter().print("<br>"+"<br>"+"<br>");
            response.getWriter().print("<h2>当前在线用户列表:</h2>"+"<br>");
            int size = online == null ? 0 : online.size();
            for (int i = 0; i < size; i++) {
                if(i > 0){
                    response.getWriter().print("<br/>");
                }
                response.getWriter().print(i + 1 + "." + "  用户名：" + online.get(i)+"<br>");
            }
            response.getWriter().print("总在线人数："+online.size());


            //创建cookie存放Session的标识号
            Cookie cookie = new Cookie("JSESSION",session.getId());
            cookie.setMaxAge(60*30);
            cookie.setPath("/myweb_servlet");
            response.addCookie(cookie);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.login.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class CharacterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //拦截所有请求，解决全站中文乱码
        //指定request和response的编码
        //setCharacterEncoding只针对消息体
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        CharacterRequest characterRequest = new CharacterRequest(request);
        filterChain.doFilter(characterRequest,response);
    }

    @Override
    public void destroy() {
    }

    private class CharacterRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;
        public CharacterRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        @Override
        public String getParameter(String name){
            String value = super.getParameter(name);
            if(value == null){
                return null;
            }
            String method = super.getMethod();
            if("get".equalsIgnoreCase(method)){
                try{
                    value = new String(value.getBytes("iso-8859-1"),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            return value;
        }
    }
}


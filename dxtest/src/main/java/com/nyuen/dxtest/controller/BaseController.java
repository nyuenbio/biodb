package com.nyuen.dxtest.controller;

import com.nyuen.dxtest.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    protected HttpServletRequest getRequest() {
        return this.request;
    }

    protected HttpServletResponse getResponse() {
        return this.response;
    }

    public HttpSession getSession() {
        return this.session;
    }

    public String createToken(){
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    public void setToken(HttpServletRequest request, String tokenServerKey){
        String token = UUID.randomUUID().toString().replace("-", "");
        request.getSession().setMaxInactiveInterval(7200);/*session失效时间为7200秒*/
        request.getSession().setAttribute(tokenServerKey,token);
    }

    public void removeToken(HttpServletRequest request, String tokenServerKey){
        request.getSession().removeAttribute(tokenServerKey);
    }

    /**
     * 将系统管理员登录信息缓存到session
     */
    protected void putSysUser2Session(User user){
        getSession().setAttribute("sys_user", user);
    }
    /**
     * 从session缓存中获取系统管理员登录信息
     */
    protected User getSysUserFromSession(){
        return (User)getSession().getAttribute("sys_user");
    }
    /**
     * 将session中的用户缓存信息移除
     */
    protected void removeSysUserFromSession(){
        getSession().removeAttribute("sys_user");
    }


    protected void putToken2Session(String token){
        getSession().setAttribute("token_server", token);
    }

    protected String getTokenFromSession(){
        return (String)getSession().getAttribute("token_server");
    }

}

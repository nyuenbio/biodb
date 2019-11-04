package com.nyuen.dxtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.nyuen.dxtest.controller.BaseController;
import com.nyuen.dxtest.result.ResultFactory;
import com.nyuen.dxtest.util.CachedManager;
import com.nyuen.dxtest.util.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonInterceptor extends BaseController implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean verifyStatus = false;

        response.setContentType("application/json; charset=utf-8");
        String path = request.getRequestURL().toString();
        if(path.contains("/login")){
            return true;
        }
        String token_client = request.getHeader("Authorization");
        Object token_server = CachedManager.get("defaultcache","token");

        //System.out.println("token server => "+ token_server);
        //System.out.println("token client => "+ token_client);

        if(StringUtil.isEmpty(token_client)){
            failResponse(response);
            return verifyStatus;
        }

        if(token_server == null){
            failResponse(response);
        }else if(token_client.equals(token_server.toString())){
            verifyStatus = true;
        }else{
            failResponse(response);
        }
        return verifyStatus;
    }

    private static void failResponse(HttpServletResponse  response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, Content-Type, Accept, token, Authorization");
        response.getWriter().append(ResultFactory.buildLoginFailResult("token 验证失败").toJson());
    }



}

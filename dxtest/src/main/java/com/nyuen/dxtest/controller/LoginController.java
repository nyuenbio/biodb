package com.nyuen.dxtest.controller;

import com.alibaba.fastjson.JSON;
import com.nyuen.dxtest.dao.UserMapper;
import com.nyuen.dxtest.model.User;
import com.nyuen.dxtest.result.Result;
import com.nyuen.dxtest.result.ResultCode;
import com.nyuen.dxtest.result.ResultFactory;
import com.nyuen.dxtest.service.impl.UserServiceImpl;
import com.nyuen.dxtest.util.CachedManager;
import com.nyuen.dxtest.util.Md5Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD4;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
 * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
 * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class LoginController extends BaseController{

    @Resource
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody String userStr, HttpServletRequest request){
        User user = JSON.parseObject(userStr, User.class);

        List<User> userList = userServiceImpl.selectByUnamePwd(user);
        if(userList == null || userList.size()==0 || userList.isEmpty() ){
            return ResultFactory.buildFailResult("啊哟，登录失败了");
        }
        else{
            User user2 = userList.get(0);
            if(user.getUserpassword().equals(Md5Util.StringInMd5(user2.getUserpassword()))) {
                String token = UUID.randomUUID().toString().replace("-", "");
                CachedManager.put("token", token);
                System.out.println(" cache token => " + CachedManager.get("defaultcache", "token"));
                return ResultFactory.buildSuccessResult(token);
            }
            return ResultFactory.buildFailResult("嗷，登录失败");
        }

    }


    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        removeToken(request,"token");
        return ResultFactory.buildSuccessResult("退出登录成功 ");
    }

}

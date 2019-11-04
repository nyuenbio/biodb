package com.nyuen.dxtest.service.impl;

import com.nyuen.dxtest.dao.UserMapper;
import com.nyuen.dxtest.model.User;
import com.nyuen.dxtest.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> selectByUnamePwd(User user) {
        return userMapper.selectByUnamePwd(user);
    }
}

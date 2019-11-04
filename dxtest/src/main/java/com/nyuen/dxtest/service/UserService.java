package com.nyuen.dxtest.service;

import com.nyuen.dxtest.model.User;

import java.util.List;

public interface UserService {
    public List<User> selectByUnamePwd(User user);
}

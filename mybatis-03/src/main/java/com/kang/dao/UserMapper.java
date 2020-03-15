package com.kang.dao;

import com.kang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();
    //根据id查询用户
    User getUserById(int id);

}

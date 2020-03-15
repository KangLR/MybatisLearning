package com.kang.dao;

import com.kang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();
    //根据id查询用户
    User getUserById(int id);
    //插入一个用户
    int addUser(User user);
    //修改一个用户
    int updateUser(User user);
    //删除一个用户
    int deleteUser(int id);


    //万能的Map
    List<User> getUserByMap(Map<String, Object> map);

    int addUserByMap(Map<String, Object> map);

}

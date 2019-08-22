package com.learn.dao;

import com.learn.entity.User;

import java.util.List;

//用户的dao接口继承顶层dao 接口
public interface UserDaoI extends DaoI<User>{
    //用户添加登录的方法
    public User login(String userName,String userPassword);
}

package com.learn.service;

import com.learn.dao.impl.UserDaoImpl;
import com.learn.entity.User;

import java.util.List;

//用户服务层的接口
public interface UserService {
    UserDaoImpl dao=new UserDaoImpl();//数据访问对象
    public User login(String userName, String userPassword);//登陆
    public int insert(User user);//增加
    public int delete(User user);//删除
    public int update(User user);//更改
    public User select(Object userName);//查一条数据
    public List<User> selectAll(int page, int pageSize);//无条件分页查所有
    public int selectCount();//无条件查数据总数
    public List<User> selectAll(int page, int pageSize ,Object parameter);//按条件分页查所有
    public int selectCount(Object parameter);//无条件查数据总量
    public List<Object> selectColumn(Object parameter);//将某个字段的数据查出
}

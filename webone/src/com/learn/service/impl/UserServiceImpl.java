package com.learn.service.impl;

import com.learn.entity.User;
import com.learn.service.UserService;
import com.learn.utils.EmptyUtils;

import java.util.List;

//用户服务层实现类
public class UserServiceImpl implements UserService {
    @Override
    public User login(String userName, String userPassword) {
        return dao.login(userName,userPassword);
    }
    @Override
    public int insert(User user) {
        return dao.insert(user);
    }
    @Override
    public int delete(User user) {
        return dao.delete(user);
    }
    @Override
    public int update(User user) {
        return dao.update(user);
    }
    @Override
    public User select(Object userName) {
        return dao.select(userName);
    }
    @Override
    public List<User> selectAll(int page, int pageSize) {
        return dao.selectAll(page,pageSize);
    }
    @Override
    public int selectCount() {
        return dao.selectCount();
    }
    @Override
    public List<User> selectAll(int page, int pageSize, Object parameter) {
        if (EmptyUtils.isEmpty(parameter)){//如果条件参数是空，就调用无条件查所有方法
            return this.selectAll(page,pageSize);
        }else {
            return dao.selectAll(page, pageSize, parameter);
        }
    }
    @Override
    public int selectCount(Object parameter) {
        if (EmptyUtils.isEmpty(parameter)){//如果条件参数是空，就调用无条件查数量方法
            return this.selectCount();
        }else {
            return dao.selectCount(parameter);
        }
    }
    @Override
    public List<Object> selectColumn(Object parameter) {
        return dao.selectColumn(parameter);
    }
}

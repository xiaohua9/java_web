package com.learn.dao.impl;

import com.learn.dao.UserDaoI;
import com.learn.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDaoI {
    @Override
    public int insert(User user) {
        //构造sql字符串
        String sql="insert into user values(?,?)";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,user.getUserName(),user.getUserPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int delete(User user) {
        //构造sql字符串
        String sql="delete from user where userName=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,user.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int update(User user) {
        //构造sql字符串
        String sql="update user set userPassword=? where userName=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,user.getUserPassword(),user.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public User select(Object userName) {
        //构造sql字符串
        String sql="select * from user where userName=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        User user=null;//将查询结果赋值到改对象
        try {
            user=runner.query(sql,new BeanHandler<User>(User.class),userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;//返回结果对象
    }

    @Override
    public List<User> selectAll() {
        //构造sql字符串
        String sql="select * from user";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<User> list=null;//将查询结果赋值到改对象
        try {
            list= runner.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;//返回结果对象
    }
}

package com.learn.dao.impl;

import com.learn.dao.UserDaoI;
import com.learn.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDaoI {
    @Override
    public User login(String userName, String userPassword) {
        //构造sql字符串
        String sql="select * from user where userName=? and userPassword=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        User user=null;
        try {
            user = runner.query(sql, new BeanHandler<User>(User.class), userName, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public int insert(User user) {
        //构造sql字符串
        String sql="insert into user values(?,?,?,?,?,?)";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,user.getUserName(),user.getUserPassword(),user.getUserGender(),user.getUserAge(),user.getUserAddress(),user.getUserBirthday());
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
        String sql="update user set userPassword=?,userGender=?,userAge=?,userAddress=?,userBirthday=? where userName=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,user.getUserPassword(),user.getUserGender(),user.getUserAge(),user.getUserAddress(),user.getUserBirthday(),user.getUserName());
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
    //不按条件查所有分页
    @Override
    public List<User> selectAll(int page, int pageSize) {
        //构造sql字符串
        int i = (page - 1) * pageSize;
        String sql="select * from user limit ?,?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<User> list=null;//将查询结果赋值到改对象
        try {
            list= runner.query(sql, new BeanListHandler<User>(User.class),i,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;//返回结果对象
    }
    //不按条件的数量查询
    @Override
    public int selectCount() {
        //构造sql字符串
        String sql="select count(1) from user";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int count=0;//将查询结果数量保存
        try {
            count = runner.query(sql, new ScalarHandler<Long>()).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;//返回数量
    }
//////按条件分页查询
    @Override
    public List<User> selectAll(int page, int pageSize ,Object parameter) {
        //构造sql字符串
        int i = (page - 1) * pageSize;
        String sql="select * from user where userAddress=? limit ?,?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<User> list=null;//将查询结果赋值到改对象
        try {
            list= runner.query(sql, new BeanListHandler<User>(User.class),parameter,i,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;//返回结果对象
    }
    //按条件的数量查询
    @Override
    public int selectCount(Object parameter) {
        //构造sql字符串
        String sql="select count(1) from user where userAddress=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int count=0;//将查询结果数量保存
        try {
            count = runner.query(sql, new ScalarHandler<Long>(),parameter).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;//返回数量
    }

    @Override
    public List<Object> selectColumn(Object parameter){
        //构造sql字符串
        String sql="select distinct("+parameter+") from user";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<Object> object=null;//将查询结果赋值到改对象
        try {
            object= runner.query(sql, new ColumnListHandler<Object>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;//返回结果对象
    }
}

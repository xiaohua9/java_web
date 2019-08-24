package com.studentsystem.dao.impl;

import com.studentsystem.dao.ClazzDaoI;
import com.studentsystem.entity.Clazz;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;
//由于班级dao只需要查询全部的功能，故只实现查全部的功能
public class ClazzDaoImpl implements ClazzDaoI {
    @Override
    public int insert(Clazz clazz) {
        return 0;
    }

    @Override
    public int delete(Clazz clazz) {
        return 0;
    }

    @Override
    public int update(Clazz clazz) {
        return 0;
    }

    @Override
    public Clazz select(Object parameter) {
        return null;
    }

    @Override
    public List<Clazz> selectAll(int page, int pageSize, Object... parameter) {
        return null;
    }

    @Override
    public int selectCount(Object... parameter) {
        return 0;
    }

    @Override
    public List<Object> selectColumn(Object parameter) {
        return null;
    }

    @Override
    public List<Clazz> selectAll() {
        //构造sql字符串
        String sql="select * from clazz ";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<Clazz> clazz=null;//将查询结果赋值到改对象
        try {
            clazz=runner.query(sql,new BeanListHandler<Clazz>(Clazz.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazz;//返回结果对象
    }
}

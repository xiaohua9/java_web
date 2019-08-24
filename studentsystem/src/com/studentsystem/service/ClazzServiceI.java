package com.studentsystem.service;

import com.studentsystem.dao.ClazzDaoI;
import com.studentsystem.dao.impl.ClazzDaoImpl;
import com.studentsystem.entity.Clazz;

import java.util.List;

public interface ClazzServiceI {
    ClazzDaoI dao=new ClazzDaoImpl();//提取获取依赖对象
    public List<Clazz> selectAll();//查询所有数据
}

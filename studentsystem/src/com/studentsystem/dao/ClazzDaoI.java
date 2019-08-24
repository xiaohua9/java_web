package com.studentsystem.dao;

import com.studentsystem.entity.Clazz;

import java.util.List;

//班级的子接口
public interface ClazzDaoI extends DaoI<Clazz>{
    //查询所有数据
    public List<Clazz> selectAll();
}

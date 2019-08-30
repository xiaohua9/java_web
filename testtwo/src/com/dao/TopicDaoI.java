package com.dao;

import com.entity.Topic;

import java.util.List;

//班级的子接口
public interface TopicDaoI extends DaoI<Topic>{
    //查询所有数据
    public List<Topic> selectAll();
}

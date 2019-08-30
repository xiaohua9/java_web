package com.service;


import com.dao.TopicDaoI;
import com.dao.impl.TopicDaoImpl;
import com.entity.Topic;

import java.util.List;

public interface TopicServiceI {
    TopicDaoI dao=new TopicDaoImpl();//提取获取依赖对象
    public List<Topic> selectAll();//查询所有数据
}

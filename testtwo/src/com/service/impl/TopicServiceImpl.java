package com.service.impl;

import com.entity.Topic;
import com.service.TopicServiceI;

import java.util.List;

public class TopicServiceImpl implements TopicServiceI {
    @Override
    public List<Topic> selectAll() {
        return dao.selectAll();
    }
}

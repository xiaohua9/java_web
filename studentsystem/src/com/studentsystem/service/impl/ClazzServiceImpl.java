package com.studentsystem.service.impl;

import com.studentsystem.entity.Clazz;
import com.studentsystem.service.ClazzServiceI;

import java.util.List;

public class ClazzServiceImpl implements ClazzServiceI {
    @Override
    public List<Clazz> selectAll() {
        return dao.selectAll();
    }
}

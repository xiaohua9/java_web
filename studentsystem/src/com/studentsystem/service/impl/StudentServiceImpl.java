package com.studentsystem.service.impl;

import com.studentsystem.entity.Student;
import com.studentsystem.service.StudentServiceI;

import java.util.List;

//学生服务的实现类
public class StudentServiceImpl implements StudentServiceI {
    @Override
    public int insert(Student student) {
        return dao.insert(student);
    }

    @Override
    public int delete(Student student) {
        return dao.delete(student);
    }

    @Override
    public int update(Student student) {
        return dao.update(student);
    }

    @Override
    public Student select(Object studentNo) {
        return dao.select(studentNo);
    }

    @Override
    public List<Student> selectAll(int page, int pageSize, Object... parameter) {
        return dao.selectAll(page,pageSize,parameter);
    }

    @Override
    public int selectCount(Object... parameter) {
        return dao.selectCount(parameter);
    }

    @Override
    public List<Object> selectColumn(Object parameter) {
        return dao.selectColumn(parameter);
    }
}

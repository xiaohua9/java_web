package com.studentsystem.service;

import com.studentsystem.dao.StudentDaoI;
import com.studentsystem.dao.impl.StudentDaoImpl;
import com.studentsystem.entity.Student;

import java.util.List;

//学生的服务层接口
public interface StudentServiceI {
    StudentDaoI dao=new StudentDaoImpl();//将依赖的对象提前获取
    public int insert(Student student);//增加数据
    public int delete(Student student);//删除数据
    public int update(Student student);//更改数据
    public Student select(Object studentNo);//根据编号查一条数据
    public List<Student> selectAll(int page, int pageSize, Object...parameter);//根据条件分页查询数据
    public int selectCount(Object...parameter);//根据条件查询数据量
    public List<Object> selectColumn(Object parameter);//查得某个字段的所有数据，后续可用作条件字段的获取
}

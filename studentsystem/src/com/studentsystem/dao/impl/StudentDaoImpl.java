package com.studentsystem.dao.impl;
import com.studentsystem.dao.StudentDaoI;
import com.studentsystem.entity.Student;
import com.studentsystem.utils.EmptyUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDaoI {
    @Override
    public int insert(Student student) {
        //构造sql字符串
        String sql="insert into student(studentName,age,boreDate,classNo) values(?,?,?,?)";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,student.getStudentName(),student.getAge(),student.getBoreDate(),student.getClassNo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int delete(Student student) {
        //构造sql字符串
        String sql="delete from student where studentNo=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,student.getStudentNo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int update(Student student) {
        //构造sql字符串
        String sql="update student set studentName=?,age=?,boreDate=?,classNo=? where studentNo=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,student.getStudentName(),student.getAge(),student.getBoreDate(),student.getClassNo(),student.getStudentNo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }
///根据编号查一条学生记录
    @Override
    public Student select(Object studentNo) {
        //构造sql字符串
        String sql="select * from student where studentNo=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        Student student=null;//将查询结果赋值到改对象
        try {
            student=runner.query(sql,new BeanHandler<Student>(Student.class),studentNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;//返回结果对象
    }
    //按条件查所有分页
    @Override
    public List<Student> selectAll(int page, int pageSize,Object...parameter) {
        int i = (page - 1) * pageSize;
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<Student> list=null;//将查询结果赋值到改对象

        //判断查询条件的有无，分情况构造sql语句,
        String sql=null;
        if (EmptyUtils.isEmpty(parameter[0])){
            sql="select * from student limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<Student>(Student.class),i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            sql="select * from student where classNo=? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<Student>(Student.class),parameter[0],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;//返回结果对象
    }
    //按条件的数量查询
    @Override
    public int selectCount(Object...parameter) {
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //判断查询条件的有无，分情况构造sql语句,
        String sql=null;
        int count=0;//将查询结果数量保存
        if (EmptyUtils.isEmpty(parameter[0])){
            sql="select count(1) from student";
            try {
                count = runner.query(sql, new ScalarHandler<Long>()).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            sql="select count(1) from student where classNo=?";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[0]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;//返回数量
    }

    @Override
    public List<Object> selectColumn(Object parameter){
        //构造sql字符串
        String sql="select distinct("+parameter+") from student";
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

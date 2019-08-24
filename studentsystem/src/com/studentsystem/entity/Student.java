package com.studentsystem.entity;
public class Student {
    //属性:编号，姓名，年龄，生日，班号
    private int studentNo;
    private String studentName;
    private int age;
    private  String boreDate;
    private int classNo;
    //构造方法
    public Student() {
    }

    public Student(int studentNo) {
        this.studentNo = studentNo;
    }

    public Student(String studentName, int age, String boreDate, int classNo) {
        this.studentName = studentName;
        this.age = age;
        this.boreDate = boreDate;
        this.classNo = classNo;
    }

    public Student(int studentNo, String studentName, int age, String boreDate, int classNo) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.age = age;
        this.boreDate = boreDate;
        this.classNo = classNo;
    }
    //set/get方法

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBoreDate() {
        return boreDate;
    }

    public void setBoreDate(String boreDate) {
        this.boreDate = boreDate;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }
}

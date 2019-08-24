package com.studentsystem.entity;
//班级表
public class Clazz {
    //属性：班号，班名
    private int classNo;
    private String className;
    //构造方法
    public Clazz() {
    }

    public Clazz(String className) {
        this.className = className;
    }

    public Clazz(int classNo, String className) {
        this.classNo = classNo;
        this.className = className;
    }
    //set/get方法

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

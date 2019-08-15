package com.learn.entity;

import java.util.Objects;

//用户实体类
public class User {
    //属性：用户名，密码
    private String userName;
    private String UserPassword;
    //构造方法
    public User() {
    }
    public User(String userName, String userPassword) {
        this.userName = userName;
        UserPassword = userPassword;
    }
   //set/get方法
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return UserPassword;
    }
    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(UserPassword, user.UserPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, UserPassword);
    }
}

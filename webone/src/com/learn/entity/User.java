package com.learn.entity;

//用户实体类
public class User {
    //属性：用户名，密码,性别，年龄，地址，生日
    private String userName;
    private String userPassword;
    private String userGender;
    private int userAge;
    private String userAddress;
    private String userBirthday;
    //构造方法
    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String userPassword, String userGender, int userAge, String userAddress, String userBirthday) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userAddress = userAddress;
        this.userBirthday = userBirthday;
    }

    //set/get方法

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAge=" + userAge +
                ", userAddress='" + userAddress + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                '}';
    }
}

package com.learn.utils;

import org.apache.commons.codec.digest.DigestUtils;

//我的加密工具类
public class MyMD5 {
    public static String getPassword(String name){
        String md5Hex = DigestUtils.md5Hex(name+"xiaohua");
        return md5Hex;
    }
}

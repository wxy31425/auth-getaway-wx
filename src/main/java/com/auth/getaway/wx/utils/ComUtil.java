package com.auth.getaway.wx.utils;


import java.util.Random;

public class ComUtil {
    /**
     * 随便生产6位数的验证码
     * @return
     */
    public static String getRandomCode(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++){
            result+=random.nextInt(10);
        }
        return result;
    }
}

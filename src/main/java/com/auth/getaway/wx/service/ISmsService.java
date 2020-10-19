package com.auth.getaway.wx.service;

/**
 * 发送短信IAccountService
 * @author wxy
 * 2020 9-28
 */
public interface ISmsService {
    /**
     * 手机号发送验证码
     * @param phone
     * @return
     */
    String send(String phone);
}

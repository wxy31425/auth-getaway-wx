package com.auth.getaway.wx.service.impl;

import com.auth.getaway.wx.service.ISmsService;
import com.auth.getaway.wx.sms.SmsParm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 发送短信管理 ISmsService实现类
 * @author wxy
 * 2020 9-28
 */
@Service
@Slf4j
public class SmsServiceImpl implements ISmsService {

    /**
     * 发送短信
     * @param phone
     * @return
     */
    @Override
    public  String send(String phone) {
       String code =  SmsParm.send(phone);
       return code;

    }
}

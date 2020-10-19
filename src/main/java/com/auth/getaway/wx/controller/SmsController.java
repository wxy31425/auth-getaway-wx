package com.auth.getaway.wx.controller;


import com.auth.getaway.wx.controller.vm.SmsVM;
import com.auth.getaway.wx.service.ISmsService;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 *  发送短信
 * @author wxy
 *  2020 9-28
 */
@RestController
@RequestMapping("/Sms")
public class SmsController {
    private final StringRedisTemplate redisTemplate;
    @Autowired
    ISmsService smsService;

    public SmsController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 发送验证码
     * @param smsVM
     * @return
     */
    @PostMapping("sendSms")
    public ResposeStatus send(@RequestBody SmsVM smsVM) {
        String code =smsService.send(smsVM.getPhone());
        redisTemplate.opsForValue().set("code",code, 60 * 1, TimeUnit.SECONDS);
        return ResposeStatus.success();
    }

}


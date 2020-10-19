package com.auth.getaway.wx.controller;

import com.auth.getaway.wx.controller.vm.SmsVM;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


/**
 *  微信登录
 * @author wxy
 *  2020 9-28
 */
@RestController
@RequestMapping("/wx")
public class LoginController {
    private final StringRedisTemplate redisTemplate;

    public LoginController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 登录
     * @param smsVM
     * @return
     */
    @PostMapping("login")
    public ResposeStatus login(@RequestBody SmsVM smsVM) {
        String code = redisTemplate.opsForValue().get("code");
        if(code == null){
            return ResposeStatus.error("验证码以失效请重新获取验证码","210");
        } else if(code.equals(smsVM.getCode())){
            return ResposeStatus.success();
        } else {
            return ResposeStatus.error("验证码不正确","211");
        }
    }
}

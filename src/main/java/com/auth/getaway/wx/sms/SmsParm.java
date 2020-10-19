package com.auth.getaway.wx.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.auth.getaway.wx.utils.ComUtil;

/**
 * Created by wxy on 2019/7/15
 * @Description:阿里云短信参数配置
 */
public class SmsParm {
    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";
    private static final String accessKeyId = "LTAI85y31RGUvWwM";
    private static final String accessKeySecret = "W3UXibK3DdZcS60dKFs0hG2ZxfTtNE";

    private static SendSmsResponse sendSms(String phone, String code) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "6000");
        System.setProperty("sun.net.client.defaultReadTimeout", "6000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("天然科技");
        request.setTemplateCode("SMS_172010317");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        return acsClient.getAcsResponse(request);
    }

    public static String send(String phone) {
        SendSmsResponse response;
        String code =  ComUtil.getRandomCode();
        try {
            response = sendSms(phone,code);//发送

        } catch (ClientException e) {
            e.printStackTrace();
            return e.getErrMsg();
        }
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        return code;
    }
}

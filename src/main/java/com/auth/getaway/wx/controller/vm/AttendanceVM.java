package com.auth.getaway.wx.controller.vm;

import com.auth.getaway.wx.entity.Employees;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceVM {
    private String phone;
    private String address;
    private String time;
    private String attendDate;
    private Integer status;
//    private Integer timeStatus;
//    private Employees employees;
//    private String attendUpTime;
//    private String attendDownTime;
}

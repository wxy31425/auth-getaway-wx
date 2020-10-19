package com.auth.getaway.wx.service;

import com.auth.getaway.wx.entity.Employees;

/**
 * 个人信息EmployeesService
 * @author wxy
 * 2020 10-10
 */
public interface IEmployeesService {
    /**
     * 根据手机号码获取个人信息
     * @param phone
     * @return
     */
    Employees getEmployees(String phone);
}

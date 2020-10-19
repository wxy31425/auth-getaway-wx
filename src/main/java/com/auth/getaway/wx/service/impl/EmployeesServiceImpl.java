package com.auth.getaway.wx.service.impl;


import com.auth.getaway.wx.entity.Employees;
import com.auth.getaway.wx.repository.IEmployeesRepository;
import com.auth.getaway.wx.service.IEmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人信息IEmployeesService实现类
 * @author wxy
 * 2020 10-10
 */
@Service
@Slf4j
public class EmployeesServiceImpl implements IEmployeesService {
    @Autowired
    IEmployeesRepository employeesRepository;
    /**
     * 获取个人信息
     * @param phone
     * @return
     */
    @Override
    public Employees getEmployees(String phone) {
        return employeesRepository.findByPhone(phone);
    }
}

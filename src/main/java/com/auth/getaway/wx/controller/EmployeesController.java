package com.auth.getaway.wx.controller;



import com.auth.getaway.wx.entity.Employees;
import com.auth.getaway.wx.service.IEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人信息
 * @author wxy
 *  2020 10-10
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    IEmployeesService employeesService;
    /**
     * 获取个人信息
     * @param phone
     * @return
     */
    @PostMapping("getEmployees")
    public Employees getEmployees(@RequestBody String phone) throws Exception {
        return employeesService.getEmployees(phone);
    }
}

package com.auth.getaway.wx.service.impl;


import com.auth.getaway.wx.controller.vm.AttendanceVM;
import com.auth.getaway.wx.entity.Attendance;
import com.auth.getaway.wx.entity.Employees;
import com.auth.getaway.wx.repository.IAttendanceRepository;
import com.auth.getaway.wx.repository.IEmployeesRepository;
import com.auth.getaway.wx.service.IAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考勤打卡IAttendanceService实现类
 * @author wxy
 * 2020 1-22
 */
@Service
@Slf4j
public class AttendanceServiceImpl implements IAttendanceService {
    @Autowired
    IEmployeesRepository employeesRepository;
    @Autowired
    IAttendanceRepository attendanceRepository;

    /**
     * 发起打卡
     * @param attendanceVM
     * @return
     */
    @Transactional
    @Override
    public Object clockIn(AttendanceVM attendanceVM) throws Exception {
        Attendance attendance = new Attendance();
        Employees employees = employeesRepository.findByPhone(attendanceVM.getPhone());
        attendance = attendanceRepository.findByEmployeesAndAttendDate(employees, attendanceVM.getAttendDate());
        Date date = new SimpleDateFormat("hh:mm:ss").parse(attendanceVM.getTime());
        int hours = date.getHours();
        int min = date.getMinutes();
        int timeStatus = 0;
        int complaintStatus = 0;
        int i = 0;
        if (!StringUtils.isEmpty(attendance)) {
            int count = attendance.getCount();
            count++;
            i = attendanceRepository.updatelockIn(attendanceVM.getTime(), count, attendance.getId());
            if (i < 1) {
                throw new SQLException();
            } else {
                return true;
            }
        } else {
            if (hours >= 9 && min > 3) {
                timeStatus = 1;
                complaintStatus = 1;
            }
            if(hours <= 10){
                 i = attendanceRepository.insertDownlockIn(attendanceVM.getAddress(), attendanceVM.getAttendDate(),
                        employees.getId(), attendanceVM.getStatus(), timeStatus, attendanceVM.getTime(), 1, complaintStatus);
            } else if(hours >= 18){
                i = attendanceRepository.insertUplockIn(attendanceVM.getAddress(), attendanceVM.getAttendDate(),
                        employees.getId(), attendanceVM.getStatus(), timeStatus, attendanceVM.getTime(), 1, complaintStatus);
            }
            if (i < 1) {
                throw new SQLException();
            } else {
                return true;
            }
        }
    }

    /**
     * 查询打卡情况
     * @param attendanceVM
     * @return
     */
    @Override
    public Boolean getAttendance(AttendanceVM attendanceVM) throws ParseException {
        Attendance attendance = new Attendance();
        Employees employees = employeesRepository.findByPhone(attendanceVM.getPhone());
        attendance = attendanceRepository.findByEmployeesAndAttendDate(employees, attendanceVM.getAttendDate());
        if (!StringUtils.isEmpty(attendance)) {
            if (!StringUtils.isEmpty(attendance.getAttendDownTime())) {
                Date date = new SimpleDateFormat("hh:mm:ss").parse(attendance.getAttendDownTime());
                int hours = date.getHours();
                if (hours <= 10) {
                    return true;
                }
            } else if (!StringUtils.isEmpty(attendance.getAttendUpTime())) {
                Date date1 = new SimpleDateFormat("hh:mm:ss").parse(attendance.getAttendUpTime());
                int hours1 = date1.getHours();
                if (hours1 >= 18) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}

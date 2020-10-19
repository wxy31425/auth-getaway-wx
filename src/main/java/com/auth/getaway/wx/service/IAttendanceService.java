package com.auth.getaway.wx.service;


import com.auth.getaway.wx.controller.vm.AttendanceVM;
import com.auth.getaway.wx.entity.Attendance;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * 考勤打卡AttendanceService
 * @author wxy
 * 2020 9-29
 */
public interface IAttendanceService {
    /**
     * 发起打卡
     * @param attendanceVM
     * @return
     */
    Object clockIn(AttendanceVM attendanceVM) throws Exception;

    /**
     * 查询打卡情况
     * @param attendanceVM
     * @return
     */
    Boolean getAttendance(AttendanceVM attendanceVM) throws ParseException;

}

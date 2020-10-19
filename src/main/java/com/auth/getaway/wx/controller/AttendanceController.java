package com.auth.getaway.wx.controller;


import com.auth.getaway.wx.controller.vm.AttendanceVM;
import com.auth.getaway.wx.service.IAttendanceService;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;


/**
 * 考勤打卡
 * @author wxy
 *  2020 9-29
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    IAttendanceService attendanceService;

    /**
     * 打卡
     * @param attendanceVM
     * @return
     */
    @PostMapping("clockIn")
    public ResposeStatus clockIn(@RequestBody AttendanceVM attendanceVM) throws Exception {
        attendanceService.clockIn(attendanceVM);
        return ResposeStatus.success();
    }

    /**
     * 打卡情况
     * @return
     */
    @PostMapping("getAttendance")
    public ResposeStatus getAttendance(@RequestBody AttendanceVM attendanceVM) throws ParseException {
       if(attendanceService.getAttendance(attendanceVM) == true){
           return ResposeStatus.success();
       } else {
           return ResposeStatus.error("未进行打卡","301");
       }
    }
}


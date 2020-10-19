package com.auth.getaway.wx.repository;
import com.auth.getaway.wx.entity.Attendance;
import com.auth.getaway.wx.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 考勤记录实体持久化接口
 * @author wxy
 * 2020-3-08
 */
@Repository
public interface IAttendanceRepository extends JpaSpecificationExecutor<Attendance>, JpaRepository<Attendance, String> {
    /**
     * 根据员工ID日期查询考勤记录
     * @param employees
     * @param attendDate
     * @return
     */
    Attendance findByEmployeesAndAttendDate(Employees employees,String attendDate);

    /**
     * 添加打卡记录Down打卡
     * @param address
     * @param attendDate
     * @param employeesId
     * @param status
     * @param timeStatus
     * @param attendDownTime
     * @param count
     * @return
     */
    @Modifying
    @Query(value = "insert into auth_getaway_attendance(id,address,attend_date,employees_id,status,time_status,attend_down_time,count,complaint_status)" +
            "values(replace(uuid(),'-',''),:address,:attendDate,:employeesId,:status,:timeStatus,:attendDownTime,:count,:complaintStatus)",nativeQuery = true)
    Integer insertDownlockIn(@Param("address")String address,@Param("attendDate") String attendDate,
                         @Param("employeesId")String employeesId,@Param("status")int status,
                         @Param("timeStatus")int timeStatus,@Param("attendDownTime")String attendDownTime,
                         @Param("count")int count,@Param("complaintStatus")int complaintStatus);

    /**
     * 添加打卡记录Up打卡
     * @param address
     * @param attendDate
     * @param employeesId
     * @param status
     * @param timeStatus
     * @param attendUpTime
     * @param count
     * @return
     */
    @Modifying
    @Query(value = "insert into auth_getaway_attendance(id,address,attend_date,employees_id,status,time_status,attend_up_time,count,complaint_status)" +
            "values(replace(uuid(),'-',''),:address,:attendDate,:employeesId,:status,:timeStatus,:attendUpTime,:count,:complaintStatus)",nativeQuery = true)
    Integer insertUplockIn(@Param("address")String address,@Param("attendDate") String attendDate,
                             @Param("employeesId")String employeesId,@Param("status")int status,
                             @Param("timeStatus")int timeStatus,@Param("attendUpTime")String attendUpTime,
                             @Param("count")int count,@Param("complaintStatus")int complaintStatus);

    /**
     * 更新打卡记录
     * @param attendUpTime
     * @param count
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_attendance set " +
            "attend_down_time = :attendUpTime,count=:count  where id =:id", nativeQuery = true)
    Integer updatelockIn(@Param("attendUpTime") String attendUpTime,@Param("count") int count,@Param("id") String id);
}

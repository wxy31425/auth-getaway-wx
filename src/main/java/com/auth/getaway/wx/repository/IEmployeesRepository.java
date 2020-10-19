package com.auth.getaway.wx.repository;
import com.auth.getaway.wx.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 员工实体持久化接口
 * @author wxy
 * 2020-2-19
 */
@Repository
public interface IEmployeesRepository extends JpaSpecificationExecutor<Employees>, JpaRepository<Employees, String> {

    /**
     * 根据手机号查询员工id
     * @param phone
     * @return
     */
    Employees findByPhone (String phone);





}

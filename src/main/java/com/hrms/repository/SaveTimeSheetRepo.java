package com.hrms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SaveTimeSheet;

 
@Repository
public interface SaveTimeSheetRepo extends JpaRepository<SaveTimeSheet, Integer> {
		@Query("FROM SaveTimeSheet where  month=?1 and year=?2  and calweek=?3 and id=?4")
	public SaveTimeSheet GetAllDetails(int month, int year, int calweek, int id);

	

	@Query("select calweek, mon_hours,tue_hours,wed_hours,thurs_hours,fri_hours,sat_hours,sun_hours From SaveTimeSheet where month=?1 and emp_id=?2 and year=?3")
	public List<Object[]> getDetailsByMon(int month, String empId, int year);

	@Query("select id from SaveTimeSheet where emp_id=?1")
	public int getTimeSheetId(String empId);

	@Query("select status from SaveTimeSheet where emp_id=?1")
	public String getStatus(String empid);

	@Query("select createdBy from SaveTimeSheet where emp_id=?1 and id=?2")
	public int findByCretedBy(String emp, int id);

	@Query("select modifiedBy from SaveTimeSheet where emp_id=?1 and id=?2")
	public int findmodiBy(String empId, int id);

//	@Query("select empId from EmployeeDetails where empId=?1") //			+ "and id not in(1,2)")
	@Query("select emp from SaveTimeSheet where year=?1 and month=?2")
    public List<String> getSaveTimeSheetempid(int year, int month);
//	public SaveTimeSheet findByEmpId(String empid);
	@Query("select emp from SaveTimeSheet where year=?1 and month=?2")
    public String getTimeSheetempid(int year, int month);
//    @Query("select cal_week,month_name from timeSheet_add_calWeek_month where month_id=?1")
//	public List<SaveTimeSheet> getWeekMonthNameByMonthId(int month);

//	public List<EmployeeDetails> getDetailByRepId(int repId);
}

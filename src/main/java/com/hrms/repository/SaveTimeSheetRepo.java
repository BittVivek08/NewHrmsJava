package com.hrms.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.response.bean.ProjectResponse;

@Repository
public interface SaveTimeSheetRepo extends JpaRepository<SaveTimeSheet, Integer> {
	@Query("FROM SaveTimeSheet where  month=?1 and year=?2  and calweek=?3 and emp.userId=?4")
	public List<SaveTimeSheet> GetAllDetails(int month, int year, int calweek, int Id);

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

	@Query("select distinct emp.empId from SaveTimeSheet  where year=?1 and month=?2")
	public List<String> getSaveTimeSheetempid(int year, int month);

	@Query("select emp.empId from SaveTimeSheet where year=?1 and month=?2 and emp_id=?3")
	public String getTimeSheetempid(int year, int month, List<String> emp);

	@Query("from SaveTimeSheet where emp.userId=?1 and calweek=?2 order by  calweek desc")
	public ArrayList<SaveTimeSheet> getLeaveStatusByUserId(Integer userId, int calWeek);

	@Query("select sum(mon_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForMonTotalHours(String employeeId, int calWeek);

	@Query("select sum(tue_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForTueTotalHours(String employeeId, int calWeek);

	@Query("select sum(wed_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForWedTotalHours(String employeeId, int calWeek);

	@Query("select sum(thurs_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForThrTotalHours(String employeeId, int calWeek);

	@Query("select sum(fri_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForFriTotalHours(String employeeId, int calWeek);

	@Query("select sum(sat_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForSatTotalHours(String employeeId, int calWeek);

	@Query("select sum(sun_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Double queryForSunTotalHours(String employeeId, int calWeek);

	@Query("select max(mon_date) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Date startDate(String employeeId, int calWeek);

	@Query("select max(sun_Date) From SaveTimeSheet where emp_id=?1 and  calweek=?2 ")
	public Date endDate(String employeeId, int calWeek);

	@Query("From SaveTimeSheet where status=?2 and emp.userId=?1")
	public ArrayList<SaveTimeSheet> getLeaveStatusByUserId1(Integer userId, String status);

	@Query("select emp.employeeName from SaveTimeSheet where emp.userId=?1")
	public String getEmployeeNameById(int userId);

	@Query("From SaveTimeSheet where status=?2 and emp.userId=?1  and status=?2 and calweek=?3 and  year=?4")
	public ArrayList<SaveTimeSheet> getEmployeeWorkingReportsByUserIdStatusCalweekYear(Integer userId, String status,
			int calWeek, int year);

	@Query("select sum(mon_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForMonTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select sum(tue_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForTueTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select sum(wed_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForWedTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select sum(thurs_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForThrTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select sum(fri_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForFriTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select sum(sat_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForSatTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select sum(sun_hours) From SaveTimeSheet where emp_id=?1 and  calweek=?2  and year=?3 and status=?4")
	public Double queryForSunTotalHours(String employeeId, int calWeek, int year, String status);

	@Query("select max(mon_date) From SaveTimeSheet where emp_id=?1 and  calweek=?2 and year=?3 and status=?4")
	public Date startDate(String employeeId, int calWeek, int year, String status);

	@Query("select max(sun_Date) From SaveTimeSheet where emp_id=?1 and  calweek=?2 and year=?3 and status=?4")
	public Date endDate(String employeeId, int calWeek, int year, String status);

}

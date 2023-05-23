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

//	@Query("select empId from EmployeeDetails where empId=?1") //			+ "and id not in(1,2)")
	@Query("select distinct emp.empId from SaveTimeSheet  where year=?1 and month=?2")
    public List<String> getSaveTimeSheetempid(int year, int month);
//	public SaveTimeSheetfindByEmpId(String empid);
	@Query("select emp.empId from SaveTimeSheet where year=?1 and month=?2 and emp_id=?3")
    public String getTimeSheetempid(int year, int month, List<String> emp);
//    @Query("select cal_week,month_name from timeSheet_add_calWeek_month where month_id=?1")
//	public List<SaveTimeSheet> getWeekMonthNameByMonthId(int month);
//   @Query(select )
//	public ArrayList<SaveTimeSheet> getLeaveStatusByUserId(Integer userId, String status);
//    @Query("select proj.projectId,proj.projectName from SaveTimeSheet ")
//	public List<ProjectResponse> getProjectAndId();


 

//@Query("select emp.userId from SaveTimeSheet where emp.userId not in(select emp.empId from SaveTimeSheet where year=?1 and month=?2) and emp.userId not in(1,2)")
//public List<Integer> getUserId(int month,int year);

      
}

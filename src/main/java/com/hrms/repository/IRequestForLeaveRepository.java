package com.hrms.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hrms.entity.RequestForLeave;
import com.hrms.response.bean.LeavesResponseBean;

public interface IRequestForLeaveRepository extends JpaRepository<RequestForLeave, Integer> {

	@Query("select min(avalCasualLeave) from com.hrms.entity.RequestForLeave t where t.emp_id=:emp_id")
	public Integer minCasualLeave(String emp_id);

	@Query("select min(avalSickLeave) from com.hrms.entity.RequestForLeave t where t.emp_id=:emp_id")
	public Integer minSickLeave(String emp_id);

//    @Query("select startDate from RequestForLeave where startDate=?1")
//	public LocalDate findByStartDate(LocalDate date);


	public List<RequestForLeave> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate,LocalDate endDate);

	@Query("FROM RequestForLeave where emp_id=:empId")
	public Optional<RequestForLeave> findByEmpid(String empId);

	@Query("select startDate from RequestForLeave where startDate=?1 and  emp_id=?2")
	public LocalDate findByStartDate(LocalDate date, String emp);

	@Query("select emp_id From RequestForLeave where emp_id=?1")
	public String findByEmpId(String emp);
	
	
	
}

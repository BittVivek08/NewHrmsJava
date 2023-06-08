package com.hrms.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.beans.CurrentWeekRequest;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.TimeSheetResponse;

@Repository
public interface SaveTimeSheetRepo extends JpaRepository<SaveTimeSheet, Integer> {
	@Query("From SaveTimeSheet where emp.empId=?1")
	List<SaveTimeSheet> findByEmpId(String empId);

	@Query("from SaveTimeSheet where workDate=?1")
	List<SaveTimeSheet> getByDate(Date Date);

	@Query("From SaveTimeSheet where workDate BETWEEN ?1 AND ?2")
	List<SaveTimeSheet> getDetailsByStartDateEndDate(Date date1, Date date2);

}

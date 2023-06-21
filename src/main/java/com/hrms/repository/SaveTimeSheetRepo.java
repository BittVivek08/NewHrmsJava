package com.hrms.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hrms.entity.SaveTimeSheet;

public interface SaveTimeSheetRepo extends JpaRepository<SaveTimeSheet, Integer> {
	@Query("From SaveTimeSheet where emp.empId=?1")
	List<SaveTimeSheet> findByEmpId(String empId);

	@Query("from SaveTimeSheet where workDate=?1")
	List<SaveTimeSheet> getByDate(Date Date);

	@Query("From SaveTimeSheet where workDate BETWEEN ?1 AND ?2")
	List<SaveTimeSheet> getDetailsByStartDateEndDate(Date date1, Date date2);

	@Query("From SaveTimeSheet where emp.reportingManagerId=?1")
	List<SaveTimeSheet> getTimesheetUsingRpId(String rpId);

	@Query("From SaveTimeSheet where emp.reportingManagerId=?1 and emp.empId=?2")
	List<SaveTimeSheet> getTimeSheetUsingRepIdEmpId(String repId, String empId);

	@Query("From SaveTimeSheet where emp.reportingManagerId=?1 and workDate=?2")
	List<SaveTimeSheet> getDetailsByRepIdDate(String repId, Date date1);

	@Query("From SaveTimeSheet where emp.empId=?1 and emp.reportingManagerId=?4 and workDate BETWEEN ?2 and ?3 ")
	List<SaveTimeSheet> getDetails(String empId, Date date1, Date date2, String repId);
	
	SaveTimeSheet findById(int id);
     
}

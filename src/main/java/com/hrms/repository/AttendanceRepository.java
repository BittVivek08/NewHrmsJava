package com.hrms.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.EmployeeAttendance;


public interface AttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {
	
	   List<EmployeeAttendance> findByEmpIdAndDateBetween(String empId, LocalDate startDate, LocalDate endDate);

	   List<EmployeeAttendance> findByEmpId(String empId);
	   
	  // List<EmployeeAttendance> findByDateBetween(LocalDate startDate, LocalDate endDate);
	   
//	   List<EmployeeAttendance> findAllByDate(LocalDate date);
	   
//	   @Query("SELECT e FROM EmployeeAttendance e WHERE e.calendarEntity.date2 = :date")
//	   public EmployeeAttendance findByCalendarEntity(@Param("date") LocalDate date);


}

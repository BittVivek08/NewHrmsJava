package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveDetailsEntity;

public interface EmployeeLeaveDetailsRepository extends JpaRepository<EmployeeLeaveDetailsEntity, Integer>{

	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity el SET el.usedCasualLeaves = COALESCE(el.usedCasualLeaves, 0) + :days WHERE el.emp_id = :emp_id AND el.year = YEAR(CURRENT_DATE)")
	void updateUsedCasualLeavesByUserIdAndCurrentYear(@Param("days") float days, @Param("emp_id") String emp_id);
	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity el SET el.usedSickLeaves = COALESCE(el.usedSickLeaves, 0) + :days WHERE el.emp_id = :emp_id AND el.year = YEAR(CURRENT_DATE)")
	void updateUsedSickLeavesByUserIdAndCurrentYear(@Param("days") float days, @Param("emp_id") String emp_id);
	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity m SET m.usedCasualLeaves = IFNULL(m.usedCasualLeaves, 0) - :days WHERE m.emp_id = :emp_id AND m.year = YEAR(CURDATE())")
	void updateUsedCasualLeaves(@Param("days") float days, @Param("emp_id") String emp_id);
	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity m SET m.usedSickLeaves = IFNULL(m.usedSickLeaves, 0) - :days WHERE m.emp_id = :emp_id AND m.year = YEAR(CURDATE())")
	void updateUsedSickLeaves(@Param("days") float days, @Param("emp_id") String emp_id);
	
	
}

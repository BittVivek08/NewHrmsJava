package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveDetailsEntity;

public interface EmployeeLeaveDetailsRepository extends JpaRepository<EmployeeLeaveDetailsEntity, Integer>{

	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity el SET el.usedCasualLeaves = COALESCE(el.usedCasualLeaves, 0) + :days WHERE el.empId = :empId AND el.year = YEAR(CURRENT_DATE)")
	void updateUsedCasualLeavesByUserIdAndCurrentYear(@Param("days") float days, @Param("empId") String empId);
	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity el SET el.usedSickLeaves = COALESCE(el.usedSickLeaves, 0) + :days WHERE el.empId = :empId AND el.year = YEAR(CURRENT_DATE)")
	void updateUsedSickLeavesByUserIdAndCurrentYear(@Param("days") float days, @Param("empId") String empId);
	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity m SET m.usedCasualLeaves = IFNULL(m.usedCasualLeaves, 0) - :days WHERE m.empId = :empId AND m.year = YEAR(CURDATE())")
	void updateUsedCasualLeaves(@Param("days") float days, @Param("empId") String empId);
	
	@Modifying
	@Query("UPDATE EmployeeLeaveDetailsEntity m SET m.usedSickLeaves = IFNULL(m.usedSickLeaves, 0) - :days WHERE m.empId = :empId AND m.year = YEAR(CURDATE())")
	void updateUsedSickLeaves(@Param("days") float days, @Param("empId") String empId);
	
	 @Query("SELECT usedCasualLeaves FROM EmployeeLeaveDetailsEntity e WHERE e.empId = :empId")
	   public Float findByEmpIdCasual(@Param("empId") String empId);
	 
	// @Query("SELECT e FROM EmployeeLeaveDetailsEntity e WHERE e.empId =: empId")
	// public EmployeeLeaveDetailsEntity Validation(@Param (value="empId") String empId);
	 
	 EmployeeLeaveDetailsEntity findByEmpId(String empName);

	 
	 @Query("SELECT usedSickLeaves FROM EmployeeLeaveDetailsEntity e WHERE e.empId = :empId")
	   public Float findByEmpIdSick(@Param("empId") String empId);
}

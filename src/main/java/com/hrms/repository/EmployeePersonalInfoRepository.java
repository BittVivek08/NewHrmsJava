package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.EmployeeJobHistory;

public interface EmployeePersonalInfoRepository extends JpaRepository<EmployeeJobHistory, Integer> {
	
//    @Query("select positionId from EmployeeJobHistory where positionId=?1")
	public EmployeeJobHistory getByPositionId(int positionId);
	
	public EmployeeJobHistory findByPositionId(int id);

//	public int updateEmployeeJobHistory(EmployeeJobHistory employeeJobHistory);
	

}

package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hrms.entity.EmployeeJobHistory;

public interface EmployeePersonalInfoRepository extends JpaRepository<EmployeeJobHistory, Integer> {

	public EmployeeJobHistory getByPositionId(int positionId);
	
	
	public EmployeeJobHistory findByPositionId(int id);

//	public EmployeeJobHistory findBypositionId(int positionId);

	//public int updateEmployeeJobHistory(EmployeeJobHistory employeeJobHistory);
	

}

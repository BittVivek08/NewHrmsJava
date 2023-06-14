package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.entity.ProjectEmployeeEntity;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployeeEntity, Integer> {

	
	//public List<ProjectEmployeeEntity> findByProjectAndEmployee(int id,String empid);
	
	@Query("from ProjectEmployeeEntity p where p.project.projectId = :pid and p.employee.empId = :sid" )
	public List<ProjectEmployeeEntity> getProjectEmployeeExistedDetails(int pid,String sid );
	
	
	
}

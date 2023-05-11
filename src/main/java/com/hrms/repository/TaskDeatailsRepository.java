package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.TaskDetailsEntity;



public interface TaskDeatailsRepository extends JpaRepository<TaskDetailsEntity, Integer>{
	
	@Query("FROM TaskDetailsEntity as t WHERE t.project.projectId = :projectId")
	List<TaskDetailsEntity> findByProject(@Param("projectId") Integer projectId);
	
	
	@Query("FROM TaskDetailsEntity as e WHERE e.emp.empId = :empid")
	List<TaskDetailsEntity> findByEmp(@Param("empid") String empid);
	

	TaskDetailsEntity getById(Integer id);

	
	
	
	
	
	
	
	
	
	
}

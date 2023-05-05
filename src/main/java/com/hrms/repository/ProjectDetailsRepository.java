package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.ProjectDetailsEntity;

public interface ProjectDetailsRepository extends JpaRepository<ProjectDetailsEntity, Integer>{
	
	@Query("FROM  ProjectDetailsEntity as p WHERE p.client.clientid = :clientid")
    List<ProjectDetailsEntity> findByClient(@Param("clientid") Integer clientid);
	
	
	ProjectDetailsEntity findByProjectId(int projectId);
	
	
	
	
	

}

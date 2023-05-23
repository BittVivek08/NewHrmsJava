package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.response.bean.ProjectResponse;



public interface ProjectDetailsRepository extends JpaRepository<ProjectDetailsEntity, Integer>{

	@Query("FROM  ProjectDetailsEntity as p WHERE p.client.id = :clientid")
	List<ProjectDetailsEntity> findByClient(@Param("clientid") Integer clientid);

	ProjectDetailsEntity findByProjectId(int projectId);
//    @Query("From  projectName,projectId ProjectDetailsEntity")
//	List<ProjectDetailsEntity> getProjectAndId();

}

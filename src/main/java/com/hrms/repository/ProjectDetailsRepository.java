package com.hrms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.ProjectDetailsEntity;

public interface ProjectDetailsRepository extends JpaRepository<ProjectDetailsEntity, Integer> {

	@Query("FROM  ProjectDetailsEntity as p WHERE p.client.id = :clientid")
	List<ProjectDetailsEntity> findByClient(@Param("clientid") Integer clientid);

	ProjectDetailsEntity findByProjectId(int projectId);

	@Query("select enddate from ProjectDetailsEntity  where projectId=?1")
	LocalDate enddate(int projectId);

	@Query(" select manager.empId From  ProjectDetailsEntity where projectId=?1 ")
	String getprojectManager(int projectId);



}

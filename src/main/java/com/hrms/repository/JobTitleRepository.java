package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.JobTitlesEntity;
import com.hrms.entity.PositionEntity;


public interface JobTitleRepository extends JpaRepository<JobTitlesEntity, Integer> {
	
	@Query("SELECT p FROM PositionEntity p JOIN JobTitlesEntity j ON p.jobTitleId.id = j.id WHERE p.jobTitleId.id = :jobTitleId AND p.isActive = 1 AND j.isActive = 1")
	public List<PositionEntity> listOfPositions(int jobTitleId);

}

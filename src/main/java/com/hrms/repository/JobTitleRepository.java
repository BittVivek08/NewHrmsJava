package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.JobTitlesEntity;


public interface JobTitleRepository extends JpaRepository<JobTitlesEntity, Integer> {

}

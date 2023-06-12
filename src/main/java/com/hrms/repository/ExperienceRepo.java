package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.ExperinceEntity;

public interface ExperienceRepo extends JpaRepository<ExperinceEntity, Integer> {
	@Query("from ExperinceEntity where employeedetails.empId=?1")

	Optional<ExperinceEntity> findByEmpId(String empId);

}

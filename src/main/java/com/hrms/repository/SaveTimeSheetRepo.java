package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.SaveTimeSheet;

@Repository
public interface SaveTimeSheetRepo extends JpaRepository<SaveTimeSheet, Integer> {
	
}

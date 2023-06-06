package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.ContactDetails;

public interface ContactRepo  extends JpaRepository<ContactDetails, Integer> {
	
	@Query("from ContactDetails where employeedetails.empId=?1")
	Optional<ContactDetails> findByEmpId(String empId);

}

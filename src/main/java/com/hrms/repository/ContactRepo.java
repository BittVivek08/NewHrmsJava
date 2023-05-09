package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.ContactDetails;

public interface ContactRepo  extends JpaRepository<ContactDetails, Integer> {

}

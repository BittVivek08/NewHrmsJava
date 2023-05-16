package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.MaritalStatusEntity;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Integer> {

}

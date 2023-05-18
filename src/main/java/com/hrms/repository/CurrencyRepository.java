package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.SalaryCurrencyEntity;

public interface CurrencyRepository extends JpaRepository<SalaryCurrencyEntity, Integer>{

}

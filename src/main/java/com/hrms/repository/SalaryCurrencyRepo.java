package com.hrms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.SalaryCurrencyEntity;

public interface SalaryCurrencyRepo extends JpaRepository<SalaryCurrencyEntity, Integer>{

}

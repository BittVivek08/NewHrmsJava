package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer>{

}

package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.Tbl_CitiesEntity;
import com.hrms.entity.Tbl_CountriesEntity;

public interface Tbl_CountriesEntityRepository extends JpaRepository<Tbl_CountriesEntity, Integer>{

	Optional<Tbl_CountriesEntity> findById(Tbl_CountriesEntity countryId);


}

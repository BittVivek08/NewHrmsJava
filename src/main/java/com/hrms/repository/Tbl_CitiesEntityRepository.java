package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hrms.entity.Tbl_CitiesEntity;

public interface Tbl_CitiesEntityRepository extends JpaRepository<Tbl_CitiesEntity, Integer>{

	Optional<Tbl_CitiesEntity> findById(Tbl_CitiesEntity cityId);
	
//	public Tbl_CitiesEntity findByCityId(int id);

}

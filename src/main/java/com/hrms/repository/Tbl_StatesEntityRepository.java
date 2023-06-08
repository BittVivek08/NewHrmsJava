package com.hrms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.Tbl_StatesEntity;

public interface Tbl_StatesEntityRepository extends JpaRepository<Tbl_StatesEntity, Integer>{

	Optional<Tbl_StatesEntity> findById(Tbl_StatesEntity stateId);
	
	@Query("from Tbl_StatesEntity where countryId=?1")
	public List<Tbl_StatesEntity> nameOfStates(int countryId);

}

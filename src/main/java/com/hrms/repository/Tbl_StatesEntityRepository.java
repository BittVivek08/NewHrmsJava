package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.Tbl_StatesEntity;

public interface Tbl_StatesEntityRepository extends JpaRepository<Tbl_StatesEntity, Integer>{

	Optional<Tbl_StatesEntity> findById(Tbl_StatesEntity stateId);

}

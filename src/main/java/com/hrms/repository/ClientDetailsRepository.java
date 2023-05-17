package com.hrms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.ClientDetailsEntity;

public interface ClientDetailsRepository extends JpaRepository<ClientDetailsEntity, Integer> {

	public Optional<ClientDetailsEntity> findById(int  clientId);

}

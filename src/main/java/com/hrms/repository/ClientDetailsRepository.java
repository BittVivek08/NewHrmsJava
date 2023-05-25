package com.hrms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.ClientDetailsEntity;

public interface ClientDetailsRepository extends JpaRepository<ClientDetailsEntity, Integer> {

	public Optional<ClientDetailsEntity> findById(int  clientId);
    @Query("select  clientName  from ClientDetailsEntity where id=?1")
	public String findByClientName(int clientN);

}

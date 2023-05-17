package com.hrms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.ClientDetailsEntity;

public interface ClientDetailsRepository extends JpaRepository<ClientDetailsEntity, Integer> {

	public ClientDetailsEntity findByClientid(int  clientId);

}

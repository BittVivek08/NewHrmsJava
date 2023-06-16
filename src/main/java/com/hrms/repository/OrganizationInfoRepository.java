package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.OrganizationInfoEntity;

public interface OrganizationInfoRepository extends JpaRepository<OrganizationInfoEntity, Integer>{
	
	@Query("from OrganizationInfoEntity where isActive = 1")
	public OrganizationInfoEntity getOrganizationInfo();

}

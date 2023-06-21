package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.VisaDocumentsEntity;

public interface VisaDocRepository extends JpaRepository<VisaDocumentsEntity, Integer>{
	
	/*
	 * @Query("SELECT id FROM VisaDocumentsEntity WHERE documentName =?1") String
	 * visaDocByName(String documentName);
	 */
	@Query("SELECT documentName FROM VisaDocumentsEntity WHERE id =?1")
	String getVisaDocumentById(int id);
	
	

}

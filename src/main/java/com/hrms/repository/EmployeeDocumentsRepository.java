package com.hrms.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeDocumentsEntity;

public interface EmployeeDocumentsRepository extends JpaRepository<EmployeeDocumentsEntity,Integer> {

	Optional<EmployeeDocumentsEntity> findByDocumentName(String fileName);
	
	@Query("SELECT e1 FROM EmployeeDocumentsEntity e1 JOIN EmployeeDetails e2 ON e1.userId = e2.userId")
    public EmployeeDocumentsEntity findByUserIdInEmployeeDetails(Integer userId);

//	@Query("UPDATE EmployeeDocumentsEntity doc SET doc.attachmentDocumentPath = :uploadPath WHERE doc.userId = :userId AND doc.documentId = :documentId")
//	boolean uploadDocument(int userId, int documentId, String uploadPath);

}

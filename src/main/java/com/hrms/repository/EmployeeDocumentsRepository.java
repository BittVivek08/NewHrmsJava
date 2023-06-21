package com.hrms.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hrms.entity.EmployeeDocumentsEntity;


public interface EmployeeDocumentsRepository extends JpaRepository<EmployeeDocumentsEntity,Integer> {

	Optional<EmployeeDocumentsEntity> findByDocumentName(String fileName);
	
	Optional<EmployeeDocumentsEntity> findByFileName(String fileName);
	
	@Query("from EmployeeDocumentsEntity where empdetails.empId = ?1")
	List<EmployeeDocumentsEntity> findByEmpIds(String empId);

    void deleteByFileName(String fileName);
	
	@Query("SELECT e1 FROM EmployeeDocumentsEntity e1 JOIN EmployeeDetails e2 ON e1.userId = e2.userId")
    public EmployeeDocumentsEntity findByUserIdInEmployeeDetails(Integer userId);
	
	@Query("SELECT e1.fileName, empdetails.empId FROM EmployeeDocumentsEntity e1 JOIN e1.empdetails empdetails WHERE empdetails.empId = :empId")
	List<Object[]> findByEmpId(@Param("empId") String empId);

//	@Query("UPDATE EmployeeDocumentsEntity doc SET doc.attachmentDocumentPath = :uploadPath WHERE doc.userId = :userId AND doc.documentId = :documentId")
//	boolean uploadDocument(int userId, int documentId, String uploadPath);
	
	

}

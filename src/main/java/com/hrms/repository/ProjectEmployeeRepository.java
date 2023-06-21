package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.ProjectEmployeeEntity;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployeeEntity, Integer> {

	// public List<ProjectEmployeeEntity> findByProjectAndEmployee(int id,String
	// empid);

	@Query("from ProjectEmployeeEntity p where p.project.projectId = :pid and p.employee.empId = :sid")
	public List<ProjectEmployeeEntity> getProjectEmployeeExistedDetails(int pid, String sid);

//    @Query("From ProjectEmployeeEntity where id=?1 ")
//	public String getprojectManager(int projectId);
	@Query("Select employee.empId From ProjectEmployeeEntity where project_id=?1 and emp_id=?2 ")
	public String findEmpId(int projectId,String empId);

//	@Query("From ProjectEmployeeEntity where startdate=?1 and endDate=?2")
//	public Boolean getstartdate(ProjectDetailsEntity proj);

}

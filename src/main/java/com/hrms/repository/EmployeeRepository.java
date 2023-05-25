package com.hrms.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.EmpRole;
import com.hrms.entity.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String>{
	 

	Optional<EmployeeDetails> findByEmailAndPassword(String email, String password);
	
	EmployeeDetails findByEmail(String email);

	Optional<EmployeeDetails> findById(Integer id);

	public EmployeeDetails findByEmpId(String empid);
	
    public List<EmployeeDetails> findByUserId(Integer userId);
    
	EmployeeDetails findByFirstName(String firstName);
    
	@Query(" from EmployeeDetails where reportingManagerId=?1")
	List<EmployeeDetails> getDetailByRepId(Integer repId);

	@Query("select reportingManagerId from EmployeeDetails where userId=?1")
    public  Integer  getByRepId(Integer repId);
	
    @Query("select reportingManager from EmployeeDetails  where reportingManagerId=?1 and userId=?2")
	String empName(Integer repId,Integer userId);
    
    @Query("select empId from EmployeeDetails where empId=?1")
    public List<EmployeeDetails> findEmpId(List<EmployeeDetails> emp);
    
    @Query("select empId from EmployeeDetails where empId=?1")
    public List<String> findEmpIdId(String emp);
    
    @Query("select userId From EmployeeDetails where empId=?1")
	String getuserID(List<String> findEmpId);
   
    @Query("select empId From EmployeeDetails where userId=?1")
	String getEmployeeId(Integer i);
    
    @Query("select firstName From EmployeeDetails where userId=?1")
    String findByFirstNameEmp(int userId);
	
    @Query("select employeeName from EmployeeDetails  where reportingManagerId=?1")
	String empName(int repManId);


    
    @Query("From EmployeeDetails where reportingManagerId=?1 ")
	List<EmployeeDetails> getEmpUserListByReportingManagerId(int repId);
    
    @Query("from EmployeeDetails where empRoleId in(1,2) or empRoleId=4 and businessunitId=:businessunitId")
    List<EmployeeDetails> getHrManager(int businessunitId);
    
    @Query("from EmployeeDetails where empRoleId in(1,3) or empRoleId=4 and businessunitId=:businessunitId")
    List<EmployeeDetails> getImmManager(int businessunitId);
    
    @Query("SELECT e FROM EmployeeDetails e WHERE e.empRoleId = :empRoleId " +
            "AND (e.businessunitId = :businessunitId OR e.businessunitId = 0) " +
            "AND (e.departmentId = :departmentId OR e.departmentId = 0)")
    List<EmployeeDetails> reportingManagerListAddEmployee(int empRoleId, int businessunitId,int departmentId);
    
    @Query("SELECT e FROM EmployeeDetails e WHERE e.empRoleId = :empRoleId " +
            "AND e.businessunitId = :businessunitId " +
            "AND e.departmentId = :departmentId")
    List<EmployeeDetails> reportingManagerListAddEmployee2(int empRoleId, int businessunitId,int departmentId);
    
    @Query("SELECT e FROM EmployeeDetails e WHERE e.empRoleId = :empRoleId " +
            "AND (e.departmentId = :departmentId OR e.departmentId = 0)")
    List<EmployeeDetails> fetchReportManagetListBasedOnRoleIdDu(int empRoleId, int departmentId);

    //GetMailOfReportingMangerByid
    @Query("select email from  EmployeeDetails where reportingManagerId=?1 ")
    String findEmailByMangerId(int mid);  
}

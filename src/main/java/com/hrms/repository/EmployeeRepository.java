package com.hrms.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String>{
	 

	Optional<EmployeeDetails> findByEmailAndPassword(String email, String password);
	
	EmployeeDetails findByEmail(String email);

	Optional<EmployeeDetails> findById(Integer id);

	public EmployeeDetails findByEmpId(String empid);
	
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
    
//    @Query("select userId from EmployeeDetails where user_id not in(select emp_id from SaveTimeSheet where ts_year=:?1 and ts_month=?2) and user_id not in(1,2)")
  
    
        
//        public String getUserId(int month,int year);

 
    
}

package com.hrms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SaveTimeSheet;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String> {

	Optional<EmployeeDetails> findByEmailAndPassword(String email, String password);

	EmployeeDetails findByEmail(String email);

	Optional<EmployeeDetails> findById(Integer id);

	//	Optional<EmployeeDetails> findByEmpId(String empid);

	public EmployeeDetails findByEmpId(String empId);

	public List<EmployeeDetails> findByUserId(Integer userId);

	EmployeeDetails findByFirstName(String firstName);

	@Query(" from EmployeeDetails where reportingManagerId=?1")
	List<EmployeeDetails> getDetailByRepId(String repId);

	@Query("select reportingManagerId from EmployeeDetails where userId=?1")
	public String getByRepId(Integer userId);

	@Query("select reportingManager from EmployeeDetails  where reportingManagerId=?1 and userId=?2")
	String empName(String repId, Integer userId);

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
	String empName(String repManId);

	@Query("select distinct employeeName from EmployeeDetails  where empId=?1")
	List<String> getReportingManagerByEmpId(String repManId);

	@Query("select distinct employeeName from EmployeeDetails where empId=?1")
	List<String> getHrManagerByEmpId(String hrId);

	@Query("select distinct employeeName from EmployeeDetails where empId=?1")
	List<String> getImmManagerByEmpId(String immId);

	@Query("From EmployeeDetails where reportingManagerId=?1 ")
	List<EmployeeDetails> getEmpUserListByReportingManagerId(String repId);

	@Query("from EmployeeDetails where empRoleId in(1,2) or empRoleId=4 and businessunitId=:businessunitId")
	List<EmployeeDetails> getHrManager(int businessunitId);

	@Query("from EmployeeDetails where empRoleId in(1,3) or empRoleId=4 and businessunitId=:businessunitId")
	List<EmployeeDetails> getImmManager(int businessunitId);

	@Query("SELECT e FROM EmployeeDetails e WHERE e.empRoleId = :empRoleId "
			+ "AND (e.businessunitId = :businessunitId OR e.businessunitId = 0) "
			+ "AND (e.departmentId = :departmentId OR e.departmentId = 0)")
	List<EmployeeDetails> reportingManagerListAddEmployee(int empRoleId, int businessunitId, int departmentId);

	@Query("SELECT e FROM EmployeeDetails e WHERE e.empRoleId = :empRoleId " + "AND e.businessunitId = :businessunitId "
			+ "AND e.departmentId = :departmentId")
	List<EmployeeDetails> reportingManagerListAddEmployee2(int empRoleId, int businessunitId, int departmentId);

	@Query("SELECT e FROM EmployeeDetails e WHERE e.empRoleId = :empRoleId "
			+ "AND (e.departmentId = :departmentId OR e.departmentId = 0)")
	List<EmployeeDetails> fetchReportManagetListBasedOnRoleIdDu(int empRoleId, int departmentId);

	@Query("SELECT max(userId) FROM EmployeeDetails ")
	Integer getMaxUserId();

	@Query("select e.reportingManagerId from EmployeeDetails e where e.empId = :empid")
	String getReportingManagerId(@Param(value="empid") String empid);

	@Query("From EmployeeDetails where reportingManagerId=?1")
	List<EmployeeDetails> getTimesheetUsingRpId(String repId);

	//GetMailOfReportingMangerByid
	@Query("select email from  EmployeeDetails where reportingManagerId=?1 ")
	String findEmailByMangerId(int mid);  

	List<EmployeeDetails> findByEmpRole(String role);

	@Query("from  EmployeeDetails e where e.empRole = :role")
	List<EmployeeDetails> maangerIdByRole(String role);
	
	@Query("SELECT selectedDocumentsIds FROM EmployeeDetails WHERE empId =?1")
	String getSelectedDocumentName(String empId);

}

package com.hrms.service;

import java.util.List;

import com.hrms.beans.CommonResponseBean;
import com.hrms.beans.ContactBean;
import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EmployeeDto;
import com.hrms.beans.EmployeeEducationDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.ContactDetails;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeInformation;
import com.hrms.entity.EmployeeSalaryDetails;
import com.hrms.request.bean.EmployeeSalaryRequestBean;

public interface EmployeeDetailsService {
	
	//Employee Basic Information

	public EntityBeanResponse saveEmpDetails(EmployeeDetails employeeDetails);

	public List<EmployeeDetails> getAllEmpDetails();

	public EmployeeDetails getEmpById(Integer id);
	
	public EmployeeDetails getEmpByEmpId(String empId);

	public EntityBeanResponse updateEmpDetails(EmployeeDetails employeeDetails, String empId);

	public List<EmpBirthResponse> findBirthdayDetails();

	public EntityBeanResponse loginEmployee(LoginDto loginDto);


	//Employee information
	
	public EntityBeanResponse saveEmployeeInformation(String empId, EmployeeInformation empinformation);
	
	public EntityBeanResponse updateEmployeeInformation(EmployeeInformation entity);
	
	public EmployeeInformation getEmpInfoById(Integer id);
	
	public EmployeeInformation getEmpInfoByEmpId(String empId);
	
	//Employee Contact Details

	public ContactBean saveContactdata(String empId,ContactDetails details);

	public List<ContactDetails> getContactdata();

	public ContactBean updateContact(ContactDetails entity,String empId);

	public ContactDetails getcontactDetails(int id);
	
	public ContactDetails getcontactDetails(String empId);
	
	//Employee Education
	
	public EmployeeEducationDetailsBean saveEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails,String empId);


	public EmployeeEducationDetails getEmpeducationdetalsById(Integer id);
	
	public EmployeeEducationDetails getEmpeducationdetalsByEmpId(String empId);

	public List<EmployeeEducationDetails> getAllEmpeducationdetails();
	
	//Employee Salary
	
	public CommonResponseBean saveSalaryDetails (String empId, EmployeeSalaryRequestBean empSalReqBean);
	
	public CommonResponseBean updateSalaryDetails(EmployeeSalaryDetails empSalDetails,String empId);
	
	public EmployeeSalaryDetails getEmpSalaryById(Integer id);
	
	public EmployeeSalaryDetails getEmpSalaryByEmpId(String empId);


	EmployeeEducationDetailsBean updateEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails, String empId);

	EmployeeDto loginViaJWT(String email);
			

}

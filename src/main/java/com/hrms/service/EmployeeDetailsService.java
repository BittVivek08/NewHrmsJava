package com.hrms.service;

import java.util.List;

import com.hrms.beans.ContactBean;
import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EmployeeEducationDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.ContactDetails;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeInformation;

public interface EmployeeDetailsService {

	public EntityBeanResponse saveEmpDetails(EmployeeDetails employeeDetails);

	public List<EmployeeDetails> getAllEmpDetails();

	public EmployeeDetails getEmpById(Integer id);

	public EntityBeanResponse updateEmpDetails(EmployeeDetails employeeDetails);

	public List<EmpBirthResponse> findBirthdayDetails();

	public EntityBeanResponse loginEmployee(LoginDto loginDto);


	// saving employee information
	
	public EntityBeanResponse saveEmployeeInformation(EmployeeInformation empinformation);
	
	public EntityBeanResponse updateEmployeeInformation(EmployeeInformation entity);
	
	public EmployeeInformation getEmpInfoById(Integer id);
	
	// Contact Details
	public ContactBean saveContactdata(ContactDetails details);

	public List<ContactDetails> getContactdata();

	public ContactDetails updateContact(ContactDetails entity);

	public ContactDetails getcontactDetails(int id);
	
	public EmployeeEducationDetailsBean saveEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails,String empId);

	public EmployeeEducationDetailsBean updateEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails);

	public EmployeeEducationDetails getEmpeducationdetalsById(Integer id);

	public List<EmployeeEducationDetails> getAllEmpeducationdetails();

}

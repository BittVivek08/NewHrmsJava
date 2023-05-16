package com.hrms.serviceImpl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmployeeEducationBean;
import com.hrms.beans.EmployeeEducationDetailsBean;
import com.hrms.beans.ExperianceDetails;
import com.hrms.entity.EmpEducationDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.ExperinceEntity;
import com.hrms.repository.EmployeeEducationRepo;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ExperianceRepo;
import com.hrms.service.EmployeePersonalInfoService2;

@Service
public class EmployeePersonalInfoImpl2  implements EmployeePersonalInfoService2{
	
	@Autowired
	private EmployeeRepository employeerepo;
	
	@Autowired
	private ExperianceRepo experiancerepo;
	
	@Autowired
	private ExperianceDetails experiancedetails;
	
	@Autowired
	private EmployeeEducationRepo employeeeducationrepo;
	
	@Autowired
	private EmployeeEducationBean educationbean;
	
	@Override
	public ExperianceDetails saveEmployeeExperianceData(String empId,ExperinceEntity experianceentity) {
		
		EmployeeDetails employeedetails =employeerepo.findByEmpId(empId);
		
		experianceentity.setEmployeedetails(employeedetails);
		
		ExperinceEntity	 experiance =experiancerepo.save(experianceentity);
		
		if(experiance!=null) {
			experiancedetails.setMessage("employee expiriance details saved successfully");
			experiancedetails.setStatus(true);
		}
		else {
			experiancedetails.setMessage("something went wrong");
			experiancedetails.setStatus(false);
			
		}
		
		return experiancedetails;
		
		
	}

	@Override
	public ExperinceEntity getExperiancedetails(int id) {
		
		Optional<ExperinceEntity> entity=experiancerepo.findById(id);
		if(entity.isPresent()) {
			return entity.get();
		}
		return null;
	}

	@Override
	public ExperianceDetails updateExperiancedetails(ExperinceEntity entity) {

		EmployeeDetails employeeDetails=employeerepo.findByEmpId(entity.getEmployeedetails().getEmpId());
		
		entity.setEmployeedetails(employeeDetails);

		ExperinceEntity exp = experiancerepo.save(entity);

		if (exp != null) {
			experiancedetails.setMessage("Experiance  details updated successfully");
			experiancedetails.setStatus(true);
		} else {
			experiancedetails.setMessage(" something went wrong!");
			experiancedetails.setStatus(false);
		}

		return experiancedetails ;
	}


	//employee education details
@Override
public EmployeeEducationBean saveEmployeeEducation(String empId,EmpEducationDetailsEntity employeeeducation) {
		
		EmployeeDetails employeedetails =employeerepo.findByEmpId(empId);
		
		employeeeducation.setEmployeedetails(employeedetails);
		
		EmpEducationDetailsEntity empedu=employeeeducationrepo.save(employeeeducation);
		
		if(empedu!=null) {
			educationbean.setMessage("employee education details saved successfully");
			educationbean.setStatus(true);
		}
		else {
			educationbean.setMessage("something went wrong");
			educationbean.setStatus(false);
			
		}
		
		return educationbean;
	
	
}
@Override
public EmpEducationDetailsEntity getEducationdetails(int id) {
	
	Optional<EmpEducationDetailsEntity> entity=employeeeducationrepo.findById(id);
	if(entity.isPresent()) {
		return entity.get();
	}
	return null;

}

@Override
public EmployeeEducationBean updateEmpEducationdetails(EmpEducationDetailsEntity educatonentity) {

	EmployeeDetails employeeDetails=employeerepo.findByEmpId(educatonentity.getEmployeedetails().getEmpId());
	
	educatonentity.setEmployeedetails(employeeDetails);

	EmpEducationDetailsEntity edu = employeeeducationrepo.save(educatonentity);

	if (edu != null) {
		educationbean.setMessage("employee education details updated successfully");
		educationbean.setStatus(true);
	} else {
		educationbean.setMessage(" something went wrong!");
		educationbean.setStatus(false);
	}

	return educationbean ;
}

}
package com.hrms.serviceImpl;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.EmployeeEducationBean;
import com.hrms.beans.ExperianceDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ExperinceEntity;
import com.hrms.repository.EmployeeEducationRepo;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ExperianceRepo;
import com.hrms.service.EmployeePersonalInfoService;

@Service
public class EmployeePersonalInfoImpl  implements EmployeePersonalInfoService{
	Logger logging = LoggerFactory.getLogger(EmployeePersonalInfoImpl.class);
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
		logging.info("entered into saveEmployeeExperianceData method of service implementation class");
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
		logging.info("entered into getExperiancedetails method of service implementation class");
		Optional<ExperinceEntity> entity=experiancerepo.findById(id);
		if(entity.isPresent()) {
			return entity.get();
		}
		return null;
	}

	@Override
	public ExperianceDetails updateExperiancedetails(ExperinceEntity entity) {
		logging.info("entered into updateExperiancedetails method of service implementation class");
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
public EmployeeEducationBean saveEmployeeEducation(String empId,EmployeeEducationDetails employeeeducation) {
	   logging.info("entered into saveEmployeeEduction method of service implementation class ");
		EmployeeDetails employeedetails =employeerepo.findByEmpId(empId);
		
		employeeeducation.setEmployeeDetails(employeedetails);
		
		EmployeeEducationDetails empedu=employeeeducationrepo.save(employeeeducation);
		
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
public EmployeeEducationDetails getEducationdetails(int id) {
	logging.info("entered into getEduction method of service class ");
	Optional<EmployeeEducationDetails> entity=employeeeducationrepo.findById(id);
	if(entity.isPresent()) {
		return entity.get();
	}
	return null;

}

@Override
public EmployeeEducationBean updateEmpEducationdetails(EmployeeEducationDetails educatonentity) {
	logging.info("entered into updateEmpEducationdetails of service clsaa implementation");
	EmployeeDetails employeeDetails=employeerepo.findByEmpId(educatonentity.getEmployeeDetails().getEmpId());
	
	educatonentity.setEmployeeDetails(employeeDetails);

	EmployeeEducationDetails edu = employeeeducationrepo.save(educatonentity);

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
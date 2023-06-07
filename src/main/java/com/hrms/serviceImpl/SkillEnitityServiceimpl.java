package com.hrms.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.SkillEnitityBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SkillEnitity;
import com.hrms.entity.Tbl_CitiesEntity;
import com.hrms.entity.Tbl_CountriesEntity;
import com.hrms.entity.Tbl_StatesEntity;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.SkillEnitityServiceRepository;
import com.hrms.repository.Tbl_CitiesEntityRepository;
import com.hrms.repository.Tbl_CountriesEntityRepository;
import com.hrms.repository.Tbl_StatesEntityRepository;
import com.hrms.service.SkillEnitityService;

@Service
public class SkillEnitityServiceimpl implements SkillEnitityService{
	Logger logging = LoggerFactory.getLogger(SkillEnitityServiceimpl.class);
	@Autowired
	private SkillEnitityServiceRepository skillentityrepo;
	@Autowired
	private SkillEnitityBean skillentitybean;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private Tbl_CitiesEntityRepository citiesrepo;
	
	@Autowired
	private Tbl_StatesEntityRepository entityRepository;
	
	@Autowired
	private Tbl_CountriesEntityRepository countriesEntityRepo;

	@Override
	public SkillEnitityBean saveskills(String empId, SkillEnitity entity) {
		logging.info("entered saveskills method in service class implementation ");

	    EmployeeDetails employeeDetails = empRepo.findByEmpId(empId);
	    
	    Tbl_CitiesEntity cityOptional = citiesrepo.findById(entity.getCity().getId()).get();
	    Tbl_StatesEntity stateOptional = entityRepository.findById(entity.getState().getId()).get();
	    Tbl_CountriesEntity countryOptional = countriesEntityRepo.findById(entity.getCountry().getId()).get();
	    Tbl_CitiesEntity city = cityOptional;
	    Tbl_StatesEntity state = stateOptional;
	    Tbl_CountriesEntity country = countryOptional;

	    entity.setCity(city);
	    entity.setState(state);
	    entity.setCountry(country);
	    entity.setEmployeeDetails(employeeDetails);

	    SkillEnitity skillentity = skillentityrepo.save(entity);

	    if (skillentity != null) {
	        skillentitybean.setMessage("Skills saved successfully");
	        skillentitybean.setStatus(true);
	        logging.info("successfully skills saved in service");
	    } else {
	        skillentitybean.setMessage("Skills saved failed");
	        skillentitybean.setStatus(false);
	        logging.info("failed to save skills in service");
	    }

	    return skillentitybean;
	}
	
	@Override
	public List<SkillEnitity> getskillDetails() {
		logging.info("entered get skilldetails method in service class implemention");
		return skillentityrepo.findAll();
	}

	@Override
	public SkillEnitityBean updateskilldetails(int id,SkillEnitity entity) {
		logging.info("entered update skill method in service class implemention");
		
		 EmployeeDetails employeeDetails = empRepo.findByEmpId(entity.getEmployeeDetails().getEmpId());
		    
		    Tbl_CitiesEntity cityOptional = citiesrepo.findById(entity.getCity().getId()).get();
		    Tbl_StatesEntity stateOptional = entityRepository.findById(entity.getState().getId()).get();
		    Tbl_CountriesEntity countryOptional = countriesEntityRepo.findById(entity.getCountry().getId()).get();

		    Tbl_CitiesEntity city = cityOptional;
		    Tbl_StatesEntity state = stateOptional;
		    Tbl_CountriesEntity country = countryOptional;
		    
		    entity.setId(id);
		    entity.setCity(city);
		    entity.setState(state);
		    entity.setCountry(country);
		    entity.setEmployeeDetails(employeeDetails);

		    SkillEnitity skillentity = skillentityrepo.save(entity);

		    if (skillentity != null) {
		        skillentitybean.setMessage("Skills updated successfully");
		        skillentitybean.setStatus(true);
		        logging.info("successfully skills updated in service ");
		    } else {
		        skillentitybean.setMessage("Skills updating failed");
		        skillentitybean.setStatus(false);
		        logging.info("failed to update skills in service ");
		    }

		    return skillentitybean;
		
		
	}
}

	
	

	



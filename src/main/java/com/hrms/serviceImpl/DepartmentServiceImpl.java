package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.Departmentbean;
import com.hrms.entity.Businessunit;
import com.hrms.entity.Department;
import com.hrms.repository.BusinessunitRepository;
import com.hrms.repository.DepartmentRepo;
import com.hrms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	Logger logging = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	@Autowired
	private DepartmentRepo departmentrepo;
	@Autowired
	private Departmentbean departmentbean;
	@Autowired
	private BusinessunitRepository businessrepo;
	@Override
	public Departmentbean departmentDetails(Department department) {
		logging.info("entered departmentDetails method  of service implentation class");
		Businessunit business = businessrepo.findByBid(department.getBusinessunit().getBid());
		department.setBusinessunit(business);
		department.setAddress1(department.getAddress1());
		department.setAddress2(department.getAddress2());
		department.setAddress3(department.getAddress3());
		department.setCity(department.getCity());
		department.setCountry(department.getCountry());
		department.setCreatedby(department.getCreatedby());
		department.setDepHead(department.getDepHead());
		department.setDepName(department.getDepHead());
		department.setUnitid(department.getUnitid());
		department.setTimezone(department.getTimezone());
		department.setState(department.getState());
		department.setStartDate(department.getStartDate());
		department.setModifiedby(department.getModifiedby());
		department.setDipCode(department.getDipCode());
		department.setDescription(department.getDescription());
		department.setBusinessunitName(department.getBusinessunitName());
		
		departmentrepo.save(department);
		if (departmentrepo != null) {
			departmentbean.setMessage("data insert successfully");
			departmentbean.setStatus(true);
		} else {
			departmentbean.setMessage("data inserting fail");
			departmentbean.setStatus(false);
		}
		return departmentbean;

	}

	@Override
	public List<Department> getAllDepartmentDetails() {
		logging.info("entered getAllDepartmentDetails method  of service implentation class");
		return departmentrepo.findAll();
	}
	
	@Override
	public Department updateDepartment(int id, Department departmentDetails) {
		logging.info("entered updateDepartment method of service implentation class");
		Optional<Department> departmentOptional = departmentrepo.findById(id);
		try {
	    if (departmentOptional.isPresent()) {
	   
	        Department department = departmentOptional.get();
	        Businessunit business = businessrepo.findByBid(department.getBusinessunit().getBid());
			department.setBusinessunit(business);
	        department.setDepName(departmentDetails.getDepName());
	        department.setDescription(departmentDetails.getDescription());
	        department.setDepHead(departmentDetails.getDepHead());
	        department.setBusinessunitName(departmentDetails.getBusinessunitName());
	        department.setCity(departmentDetails.getCity());
	        department.setCountry(departmentDetails.getCountry());
	        department.setDipCode(departmentDetails.getDipCode());
	        department.setAddress1(departmentDetails.getAddress1());
	        department.setAddress2(department.getAddress2());
	        department.setAddress3(department.getAddress3());
	        department.setCreatedby(department.getCreatedby());
	        department.setModifiedby(department.getModifiedby());
	        department.setUnitid(department.getUnitid());
	        department.setStartDate(department.getStartDate());
	        department.setState(department.getState());

	        return departmentrepo.save(department);
	    }
		}
	    catch(Exception e) {
	    	e.printStackTrace();
	}
		return null;
	}
	
	@Override
	public Departmentbean deleteById(int id) {
		logging.info("entered deleteById method  of service implentation class");
		Department department=this.departmentrepo.getById(id);
		if( department!=null) {
	    departmentrepo.deleteById(id);
		departmentbean.setMessage("department details deleted successfully");
		departmentbean.setStatus(true);
		return departmentbean;
		
		}
		else {
			departmentbean.setMessage("department details not deleted");
			departmentbean.setStatus(false);
			return departmentbean;
		}
	}	
	
}

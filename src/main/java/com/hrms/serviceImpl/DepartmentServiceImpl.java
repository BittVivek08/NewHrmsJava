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
	public Departmentbean departmentDetails(Department department,int bid) {
		logging.info("entered departmentDetails method  of service implentation class");
		Businessunit business = businessrepo.getByBid(bid);
		department.setBusinessunit(business);
		Department insert = departmentrepo.save(department);
		if (insert != null) {
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
	        
	        department.setDepName(departmentDetails.getDepName());
	        department.setDescription(departmentDetails.getDescription());
	        department.setDepHead(departmentDetails.getDepHead());
	       // department.setBusinessId(departmentDetails.getBusinessId());
	        department.setBusinessunitName(departmentDetails.getBusinessunitName());
	        department.setCity(departmentDetails.getCity());
	        department.setCountry(departmentDetails.getCountry());
	        department.setDipCode(departmentDetails.getDipCode());

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

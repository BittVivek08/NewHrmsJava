package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.Departmentbean;

import com.hrms.entity.Department;
import com.hrms.repository.DepartmentRepo;
import com.hrms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepo departmentrepo;

	@Autowired
	private Departmentbean departmentbean;

	@Override
	public Departmentbean departmentDetails(Department department) {

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

		return departmentrepo.findAll();
	}
	

	@Override
	public Department updateDepartment(int id, Department departmentDetails) {
		
		Optional<Department> departmentOptional = departmentrepo.findById(id);
		try {
	    if (departmentOptional.isPresent()) {
	   
	        Department department = departmentOptional.get();
	        
	        department.setDepName(departmentDetails.getDepName());
	        department.setDescription(departmentDetails.getDescription());
	        department.setDepHead(departmentDetails.getDepHead());
	        department.setBusinessId(departmentDetails.getBusinessId());
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
		
		departmentrepo.deleteById(id);;
		departmentbean.setMessage("department deatails  delete successfully");
		departmentbean.setStatus(true);
		return departmentbean;
	}
	
	
}

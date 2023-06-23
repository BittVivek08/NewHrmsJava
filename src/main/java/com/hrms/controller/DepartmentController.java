package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.beans.Departmentbean;
import com.hrms.entity.Department;
import com.hrms.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {
	@Autowired
	private DepartmentService departmentservice;
	
	@PostMapping("/saveDepartmentdetails/{bid}")
	public Departmentbean saveDepartmentDetails(@RequestBody Department department,@PathVariable int bid) {
		log.info("entered saveDepartmentDetails method in controller class");
		return departmentservice.departmentDetails(department,bid);
	}
	
	@GetMapping("/getDepartmentDetails")
	public ResponseEntity<List<Department>> getDepartmentDetails(){
		log.info("entered getDepartmentDetails method in controller class");
		List<Department> depart= departmentservice.getAllDepartmentDetails();
		return new ResponseEntity<> (depart, HttpStatus.OK);
	}
	
	 @PutMapping("/updateDetails/{id}")    
	 public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") int id, @RequestBody Department departmentDetails) {
		 log.info("entered updateDepartment method in controller class");
		 Department updatedDepartment =departmentservice.updateDepartment(id, departmentDetails);
	        return ResponseEntity.ok(updatedDepartment);
	    }
	
	 @DeleteMapping("/delete/{id}")
		public Departmentbean  deleteByPositionId(@PathVariable("id") int id) {
		 log.info("entered deleteByPositionId method in controller class");
			return departmentservice.deleteById(id);
		}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
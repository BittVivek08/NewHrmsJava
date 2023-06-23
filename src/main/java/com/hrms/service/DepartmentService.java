package com.hrms.service;


import java.util.List;

import com.hrms.beans.Departmentbean;

import com.hrms.entity.Department;


public interface DepartmentService {
	
	public Departmentbean departmentDetails(Department department, int bid);
	public List<Department> getAllDepartmentDetails();
	public Department updateDepartment(int id, Department departmentDetails);
	public Departmentbean deleteById(int id);

}

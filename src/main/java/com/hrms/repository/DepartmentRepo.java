package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}

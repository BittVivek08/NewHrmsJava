package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.Privileges;

public interface PrivilegesRepo extends JpaRepository<Privileges, Integer>{

	@Query("from Privileges as p where p.role=:roleId and p.obj=:menuId and isActive=1")
	public List<Privileges> getPrivileges(int roleId, int menuId);
}

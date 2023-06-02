package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmpRole;
@Repository
public interface EmpRoleRepo extends JpaRepository<EmpRole, Integer> {
//	@Query("select roleName from EmpRoleRepo where id=?1")
//	public String findSuperAdminName(int roleId);
//
//	@Query("select roleName from EmpRoleRepo where id=?1")
//	public String getReportingManagerNameByRMId(int roleId);
}

package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.MyLeaveRequestEntity;

public interface MyLeaveRequestRepository extends JpaRepository<MyLeaveRequestEntity, Integer> {
	
	@Query("from MyLeaveRequestEntity l where l.emp_id=:eid")
	public MyLeaveRequestEntity findByEmpid(@Param("eid") String eid);
	
	@Query("select email from MyLeaveRequestEntity m where m.emp_id=:eid")
	public String mailIdofEmp(@Param("eid") String eid);
	
	
//	@Query("select * from MyLeaveRequestEntity l where l.emp_id=:eid")
//	public MyLeaveRequestEntity findByEmp_id(@Param("eid") String eid);
	
}

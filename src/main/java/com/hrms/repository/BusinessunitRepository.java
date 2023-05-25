package com.hrms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.Businessunit;


public interface BusinessunitRepository  extends JpaRepository<Businessunit, Integer>{
	
	public Businessunit getByBid(int bid);	

}

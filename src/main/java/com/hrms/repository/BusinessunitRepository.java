package com.hrms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.Businessunit;


public interface BusinessunitRepository  extends JpaRepository<Businessunit, Integer>{
	
	public Businessunit getByBid(int bid);
	
	public Businessunit findByBid(int bid);

	//public void deleteByBid(int bid);

	//public void deleteBybId(int bid);

}

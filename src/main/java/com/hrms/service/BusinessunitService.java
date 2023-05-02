package com.hrms.service;

import java.util.List;
import com.hrms.beans.Businessbean;
import com.hrms.entity.Businessunit;

public interface BusinessunitService {

	public Businessbean saveBusinessDetails(Businessunit businessunit);

	public List<Businessunit> getAllbusinessdetails();

	public Businessunit updatebusinessdetails(int bid, Businessunit entity);
	
	public Businessbean deleteByBid(int bid);
	
	public Businessunit getByBid(int bid);
	
	

}
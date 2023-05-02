package com.hrms.service;

import java.util.List;
import com.hrms.beans.Businessbean;
import com.hrms.entity.Businessunit;

public interface BusinessunitService {

	public Businessbean saveBusinessDetails(Businessunit businessunit);

	public List<Businessunit> getAllbusinessdetails();

	public Businessunit updatebusinessdetails(int bid, Businessunit entity);

	//Businessbean deleteById(int id);

	//Businessunit deletebid(int bid);

	//Businessbean deletebid(int bid);

}
package com.hrms.service;

import java.util.List;

import com.hrms.beans.Tbl_CitiesEntityBean;
import com.hrms.entity.Tbl_CitiesEntity;

public interface Tbl_CitiesEntityService {

	

	Tbl_CitiesEntityBean savecitiesDetails(Tbl_CitiesEntity citiesentity);
	
	public Tbl_CitiesEntity getById( int id);
	
	public Tbl_CitiesEntity updatecitiesdetails( int id, Tbl_CitiesEntity entity) ;

	public Tbl_CitiesEntityBean deleteById( int id);

	List<Tbl_CitiesEntity> getcitiesDetails();
	

}




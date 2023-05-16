package com.hrms.service;

import java.util.List;

import com.hrms.beans.Tbl_CountriesEntityBean;
import com.hrms.entity.Tbl_CountriesEntity;

public interface Tbl_CountriesEntityService {

	 public Tbl_CountriesEntityBean savecountriesdetails(Tbl_CountriesEntity countriesentity);

	Tbl_CountriesEntity getById(int id);

	List<Tbl_CountriesEntity> getcountriesDetails();

	Tbl_CountriesEntity updatecountriedetails(int id, Tbl_CountriesEntity entity);

	Tbl_CountriesEntityBean deleteById(int id);

}

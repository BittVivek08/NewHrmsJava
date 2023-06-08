package com.hrms.service;

import java.util.List;
import java.util.Optional;

import com.hrms.beans.LocationResponseBean;
import com.hrms.beans.Tbl_StatesEntityBean;
import com.hrms.entity.Tbl_StatesEntity;

public interface Tbl_StatesEntityService {

	Tbl_StatesEntityBean savestatesdetails(Tbl_StatesEntity statesentity);

	Tbl_StatesEntity getById(int id);

	List<Tbl_StatesEntity> getallstatesdetails();

	Tbl_StatesEntity updatestatesdetails(int id, Tbl_StatesEntity entity);

	Tbl_StatesEntityBean deleteById(int id);
	
	public LocationResponseBean getStateBasedOnCountry(int countryId);

	

}

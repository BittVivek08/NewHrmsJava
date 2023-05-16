package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.Tbl_CountriesEntityBean;
import com.hrms.entity.Tbl_CountriesEntity;
import com.hrms.repository.Tbl_CountriesEntityRepository;
import com.hrms.service.Tbl_CountriesEntityService;
@Service
public class Tbl_CountriesEntityimpl implements Tbl_CountriesEntityService{
	
	@Autowired
	private Tbl_CountriesEntityBean countriesbean;
	
	@Autowired
	private Tbl_CountriesEntityRepository countriesentityrepo;

	@Override
	public Tbl_CountriesEntityBean savecountriesdetails(Tbl_CountriesEntity countriesentity) {
		
		Tbl_CountriesEntity entity = countriesentityrepo.save(countriesentity);
		
		if (entity != null) {
			countriesbean.setMessage("countries data save successfully");
			countriesbean.setStatus(true);
		} else {
			countriesbean.setMessage("data not saved");
			countriesbean.setStatus(false);
		}
		return countriesbean;
	}


	@Override
	public Tbl_CountriesEntity getById(int id) {
		
		Optional<Tbl_CountriesEntity> bean = countriesentityrepo.findById(id);
		return bean.get();
	}


	@Override
	public List<Tbl_CountriesEntity> getcountriesDetails() {
		
		
		return countriesentityrepo.findAll();
	}

	@Override
	public Tbl_CountriesEntity updatecountriedetails(int id, Tbl_CountriesEntity entity) {
		Tbl_CountriesEntity countriesentity = countriesentityrepo.getById(id);
		if (countriesentity != null) {
			countriesentity.setCountryCode(entity.getCountryCode());
			countriesentity.setCountryCode2(entity.getCountryCode2());
			countriesentity.setCountryName(entity.getCountryName());
			countriesentity.setCreatedDate(entity.getCreatedDate());
			countriesentity.setIsActive(entity.getIsActive());
			countriesentity.setModifieddate(entity.getModifieddate());
			return countriesentityrepo.save(countriesentity);
			
		}
		
		return null;
	}

	@Override
	public Tbl_CountriesEntityBean deleteById(int id) {
		Tbl_CountriesEntity countrie = countriesentityrepo.getById(id);
		if (countrie != null) {
			countriesentityrepo.delete(countrie);
			countriesbean.setMessage("countriesdetails deleted successfully");
			countriesbean.setStatus(true);
		} else {
			countriesbean.setMessage("Failed to Delete details ");
			countriesbean.setStatus(false);
		}
		return countriesbean;
		}

	
		
	
	
		
		
		
		
	}



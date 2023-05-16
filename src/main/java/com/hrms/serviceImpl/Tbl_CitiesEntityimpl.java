package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.Tbl_CitiesEntityBean;
import com.hrms.entity.Tbl_CitiesEntity;
import com.hrms.repository.Tbl_CitiesEntityRepository;
import com.hrms.service.Tbl_CitiesEntityService;

@Service
public class Tbl_CitiesEntityimpl implements Tbl_CitiesEntityService {
	@Autowired
	private Tbl_CitiesEntityBean citiesenititybean;

	@Autowired
	private Tbl_CitiesEntityRepository citiesentityrepo;

	@Override
	public Tbl_CitiesEntityBean savecitiesDetails(Tbl_CitiesEntity citiesentity) {

		Tbl_CitiesEntity save = citiesentityrepo.save(citiesentity);
		if (save != null) {
			citiesenititybean.setMessage("cities data save successfully");
			citiesenititybean.setStatus(true);
		} else {
			citiesenititybean.setMessage("data not saved");
			citiesenititybean.setStatus(false);
		}
		return citiesenititybean;
	}

	@Override
	public Tbl_CitiesEntity getById(int id) {

		Optional<Tbl_CitiesEntity> bean = citiesentityrepo.findById(id);
		return bean.get();
	}

	@Override
	public Tbl_CitiesEntity updatecitiesdetails(int id, Tbl_CitiesEntity entity) {
		Tbl_CitiesEntity citiesentity = citiesentityrepo.getById(id);
		if (citiesentity != null) {
			citiesentity.setCityName(entity.getCityName());

			citiesentity.setCreatedDate(entity.getCreatedDate());
			citiesentity.setModifiedDate(entity.getModifiedDate());
			citiesentity.setStateId(entity.getStateId());
			citiesentity.setIsActive(entity.getIsActive());
			return citiesentityrepo.save(citiesentity);
		}
		return null;
	}

	@Override
	public Tbl_CitiesEntityBean deleteById(int id) {
		Tbl_CitiesEntity bean = citiesentityrepo.getById(id);
		if (bean != null) {
			citiesentityrepo.delete(bean);
			citiesenititybean.setMessage("buinessdetails deleted successfully");
			citiesenititybean.setStatus(true);
		} else {
			citiesenititybean.setMessage("Failed to Delete details ");
			citiesenititybean.setStatus(false);
		}
		return citiesenititybean;

	}

	@Override
	public List<Tbl_CitiesEntity> getcitiesDetails() {
		return citiesentityrepo.findAll();
	}
}

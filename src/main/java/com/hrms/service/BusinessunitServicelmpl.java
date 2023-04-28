package com.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.hrms.beans.Businessbean;
import com.hrms.entity.Businessunit;
import com.hrms.repository.BusinessunitRepository;

@Service
@Component
public class BusinessunitServicelmpl implements BusinessunitService {

	@Autowired
	private BusinessunitRepository businessunitrepository;

	@Autowired
	private Businessbean businessbean;

	@Override
	public Businessbean saveBusinessDetails(Businessunit businessunit) {

		Businessunit save = businessunitrepository.save(businessunit);
		if (save != null) {
			businessbean.setMessage("data save successfully");
			businessbean.setStatus(true);
		} else {
			businessbean.setMessage("data not saved");
			businessbean.setStatus(false);
		}
		return businessbean;
	}

	@Override
	public List<Businessunit> getAllbusinessdetails() {

		return businessunitrepository.findAll();
	}

	@Override
	public Businessunit updatebusinessdetails(int id, Businessunit entity) {
		Optional<Businessunit> businessOptional = businessunitrepository.findById(id);
		try {
			if (businessOptional.isPresent()) {

				Businessunit details = businessOptional.get();

				details.setId(entity.getId());
				details.setBid(entity.getBid());
				details.setName(entity.getName());
				details.setStartdate(entity.getStartdate());
				details.setState(entity.getState());
				details.setDescription(entity.getDescription());
				details.setCountry(entity.getCountry());
				details.setCode(entity.getCode());
				details.setAddress1(entity.getAddress1());
				details.setAddress2(entity.getAddress2());
				details.setAddress3(entity.getAddress3());
				return businessunitrepository.save(details);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public Businessbean deleteById(int id) {
		
		businessunitrepository.deleteById(id);
		businessbean.setMessage("businessdetails delete successfully");
		businessbean.setStatus(true);
		return businessbean;
	}

}

package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.Tbl_StatesEntityBean;
import com.hrms.entity.Tbl_CountriesEntity;
import com.hrms.entity.Tbl_StatesEntity;
import com.hrms.repository.Tbl_StatesEntityRepository;
import com.hrms.service.Tbl_StatesEntityService;
@Service
public class Tbl_StatesEntityServiceimpl implements Tbl_StatesEntityService {
	
	@Autowired
	private Tbl_StatesEntityBean statesentitybean;
	
	@Autowired
	private Tbl_StatesEntityRepository statesentityrepo;

	@Override
	public Tbl_StatesEntityBean savestatesdetails(Tbl_StatesEntity statesentity) {
		
		Tbl_StatesEntity entity=statesentityrepo.save(statesentity);
		if(entity !=null)
		{
			statesentitybean.setMessage("states details saved successfully");
			
			statesentitybean.setStatus(true);
		}
		else
		{
			statesentitybean.setMessage("details not saved");
			
			statesentitybean.setStatus(false);
		}
		
		
		
		return statesentitybean;
	}

	@Override
	public Tbl_StatesEntity getById(int id) {
		
		Optional<Tbl_StatesEntity> bean = statesentityrepo.findById(id);
			return bean.get();
		}

	@Override
	public List<Tbl_StatesEntity> getallstatesdetails() {
		
		return statesentityrepo.findAll();
	}

	@Override
	public Tbl_StatesEntity updatestatesdetails(int id, Tbl_StatesEntity entity) {
		
		
		Tbl_StatesEntity statesentity = statesentityrepo.getById(id);
			if (statesentity != null)
			{
				statesentity.setCountryId(entity.getCountryId());
				statesentity.setCreatedDate(entity.getCreatedDate());
				statesentity.setIsactive(entity.getIsactive());
				statesentity.setModifiedDate(entity.getModifiedDate());
				statesentity.setStateCode(entity.getStateCode());
				statesentity.setStateName(entity.getStateName());
				return statesentityrepo.save(statesentity);
				
			}
		
		
		return null;
			
	}

	@Override
	public Tbl_StatesEntityBean deleteById(int id) {
		Tbl_StatesEntity countrie = statesentityrepo.getById(id);
		if (countrie != null) {
			statesentityrepo.delete(countrie);
			statesentitybean.setMessage("countriesdetails deleted successfully");
			statesentitybean.setStatus(true);
		} else {
			statesentitybean.setMessage("Failed to Delete details ");
			statesentitybean.setStatus(false);
		}
		return statesentitybean;
		}
		
		
		
	}
	
	




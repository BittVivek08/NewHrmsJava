package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.LocationResponseBean;
import com.hrms.beans.Tbl_StatesEntityBean;
import com.hrms.entity.Tbl_StatesEntity;
import com.hrms.repository.Tbl_StatesEntityRepository;
import com.hrms.service.Tbl_StatesEntityService;
@Service
public class Tbl_StatesEntityServiceimpl implements Tbl_StatesEntityService {
	
	Logger logging = LoggerFactory.getLogger(Tbl_StatesEntityServiceimpl.class);
	
	@Autowired
	private Tbl_StatesEntityBean statesentitybean;
	
	@Autowired
	private Tbl_StatesEntityRepository statesentityrepo;

	@Override
	public Tbl_StatesEntityBean savestatesdetails(Tbl_StatesEntity statesentity) {
		logging.info("Entered Savestatesdetails in service class impl");

		
		Tbl_StatesEntity entity=statesentityrepo.save(statesentity);
		if(entity !=null)
		{
			statesentitybean.setMessage("states details saved successfully");
			
			statesentitybean.setStatus(true);
			
			logging.info("Successfully saved states details");
		}
		else
		{
			statesentitybean.setMessage("details not saved");
			
			statesentitybean.setStatus(false);
			logging.info("not saved states details successfully" );
		}
		
		
		
		return statesentitybean;
	}

	@Override
	public Tbl_StatesEntity getById(int id) {
		logging.info("Entered Savebusinessdetails in service class impl");

		
		Optional<Tbl_StatesEntity> bean = statesentityrepo.findById(id);
			return bean.get();
		}

	@Override
	public List<Tbl_StatesEntity> getallstatesdetails() {
		logging.info("Entered Get statesdetails in service ");

		
		return statesentityrepo.findAll();
	}

	@Override
	public Tbl_StatesEntity updatestatesdetails(int id, Tbl_StatesEntity entity) {
		logging.info("Entered updatestatesdetails in service class impl");

		
		
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
		logging.info("Entered deletestatesdetails in service class impl");

		Tbl_StatesEntity countrie = statesentityrepo.getById(id);
		if (countrie != null) {
			statesentityrepo.delete(countrie);
			statesentitybean.setMessage("countriesdetails deleted successfully");
			statesentitybean.setStatus(true);
			logging.info("Successfully delete countriesdeletes");
			
		} else {
			statesentitybean.setMessage("Failed to Delete details ");
			statesentitybean.setStatus(false);
			logging.info("countriesdetails not saved successfully");
		}
		return statesentitybean;
		}

	@Override
	public LocationResponseBean getStateBasedOnCountry(int countryId) {
		
		LocationResponseBean response = new LocationResponseBean();
		 
		List<Tbl_StatesEntity> nameOfStates = statesentityrepo.nameOfStates(countryId);
		
		if(nameOfStates.size() != 0) {
			response.setMessage("Retrival of State Based On Country Retrieve Successfully");
			response.setStatus(true);
			response.setList(nameOfStates);
		}else {
			response.setMessage("Retrival of State Based On Country Retrieve Failes !!!");
			response.setStatus(false);
		}
		return response;
	}

}
	
	




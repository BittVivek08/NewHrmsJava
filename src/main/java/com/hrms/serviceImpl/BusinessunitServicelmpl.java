package com.hrms.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.hrms.beans.Businessbean;
import com.hrms.entity.Businessunit;
import com.hrms.repository.BusinessunitRepository;
import com.hrms.service.BusinessunitService;
@Service
@Component
public class BusinessunitServicelmpl implements BusinessunitService {
	Logger logging = LoggerFactory.getLogger(BusinessunitServicelmpl.class);
	@Autowired
	private BusinessunitRepository businessunitrepository;

	@Autowired
	private Businessbean businessbean;

	@Override
	public Businessbean saveBusinessDetails(Businessunit businessunit) {
		logging.info("Entered Savebusinessdetails in service class impl");

		Businessunit save = businessunitrepository.save(businessunit);
		if (save != null) {
			businessbean.setMessage("Bussiness data save successfully");
			businessbean.setStatus(true);
			logging.info("successfully Businessdetails saved in service ");
			
		} else {
			businessbean.setMessage("data not saved");
			businessbean.setStatus(false);
			logging.warn("Exception occured in service");
		}
		return businessbean;
	}
	@Override
	public Businessunit getByBid(int bid) {
		logging.info("Entered Get businessdetails By bid in service ");

		
		Businessunit bean=this.businessunitrepository.getByBid(bid);
		logging.info("Successfully get businessdetails by bid in service");

		
		return bean;
	}

	@Override
	public List<Businessunit> getAllbusinessdetails() {
		logging.info("Entered get all businessdetails method in servcie ");

		return businessunitrepository.findAll();
	}

@Override
public Businessunit updatebusinessdetails(int bid, Businessunit entity) {
	logging.info("Entered update businessdetails by bid method in service ");


	Businessunit bean = businessunitrepository.getByBid(bid);
	
		if (bean != null) {
			bean.setName(entity.getName());
			bean.setStartdate(entity.getStartdate());
			bean.setState(entity.getState());
			bean.setDescription(entity.getDescription());
			bean.setCountry(entity.getCountry());
			bean.setCode(entity.getCode());
			bean.setAddress1(entity.getAddress1());
			bean.setAddress2(entity.getAddress2());
			
			bean.setCreatedby(entity.getCreatedby());
			bean.setCreateddate(entity.getCreateddate());
			bean.setIsactive(entity.getIsactive());
			bean.setModifiedBy(entity.getModifiedBy());
			bean.setModifiedDate(entity.getModifiedDate());
			bean.setTimezone(entity.getTimezone());
			

		return businessunitrepository.save(bean);
		}
		return null;
	
}

@Override
public Businessbean deleteByBid(int bid) {
	logging.info("Entered Delete busniessdetails By bid in service");
	
	Businessunit bean = this.businessunitrepository.getByBid( bid);
		if(bean!=null) {
			
			businessbean.setMessage("Cannot delete Business Unit as it is associated to department");
	        businessbean.setStatus(false);
	        return businessbean;
	      }
		return null; 
		
}

//
//@Override
//public Businessunit updatebusinessdetails(int id, Businessunit entity) {
//	Optional<Businessunit> businessOptional = businessunitrepository.findById(id);
//	try {
//		if (businessOptional.isPresent()) {
//
//			Businessunit details = businessOptional.get();
//
//			details.setId(entity.getId());
//			details.setBid(entity.getBid());
//			details.setName(entity.getName());
//			details.setStartdate(entity.getStartdate());
//			details.setState(entity.getState());
//			details.setDescription(entity.getDescription());
//			details.setCountry(entity.getCountry());
//			details.setCode(entity.getCode());
//			details.setAddress1(entity.getAddress1());
//			details.setAddress2(entity.getAddress2());
//			details.setAddress3(entity.getAddress3());
//			return businessunitrepository.save(details);
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//
//	}
//	return null;
//}
//
//@Override
//public Businessbean deleteById(int id) {
//	
//	businessunitrepository.deleteById(id);
//	businessbean.setMessage("businessdetails delete successfully");
//	businessbean.setStatus(true);
//	return businessbean;
//}
//}


//public Businessunit deletebid(int bid) {
//
//Businessunit bean = this.businessunitrepository.findBybid(bid);
//if(bean!=null) {
//	this.businessunitrepository.deleteBybId(bean);
//	businessbean.setMessage("bussniessdetails  delete successfully");
//	businessbean.setStatus(true);
//} else {
//	businessbean.setMessage("Failed to Delete details ");
//	businessbean.setStatus(false);
//}
//return bean;
//}

}


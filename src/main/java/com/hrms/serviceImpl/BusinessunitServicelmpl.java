package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;
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
	public Businessunit getByBid(int bid) {
		
		Businessunit bean=this.businessunitrepository.getByBid(bid);
		
		return bean;
	}

	@Override
	public List<Businessunit> getAllbusinessdetails() {

		return businessunitrepository.findAll();
	}

@Override
public Businessunit updatebusinessdetails(int bid, Businessunit entity) {

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
			bean.setAddress3(entity.getAddress3());

		return businessunitrepository.save(bean);
		}
		return null;
	
}

@Override
public Businessbean deleteByBid(int bid) {
	
	Businessunit bean = this.businessunitrepository.getByBid( bid);
		if(bean!=null) {
			this.businessunitrepository.delete(bean);
			businessbean.setMessage("buinessdetails deleted successfully");
			businessbean.setStatus(true);
		} else {
			businessbean.setMessage("Failed to Delete details ");
			businessbean.setStatus(false);
		}
		return businessbean;
		
	

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


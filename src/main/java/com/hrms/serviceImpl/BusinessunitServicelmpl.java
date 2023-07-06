package com.hrms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.hrms.beans.Businessbean;
import com.hrms.entity.Businessunit;
import com.hrms.entity.Department;
import com.hrms.repository.BusinessunitRepository;
import com.hrms.repository.DepartmentRepo;
import com.hrms.response.bean.BusinessUnitResponseBean;
import com.hrms.service.BusinessunitService;

@Service
@Component
public class BusinessunitServicelmpl implements BusinessunitService {
	Logger logging = LoggerFactory.getLogger(BusinessunitServicelmpl.class);
	@Autowired
	private BusinessunitRepository businessunitrepository;

	@Autowired
	private Businessbean businessbean;

	@Autowired
	private DepartmentRepo departmentrepo;

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
		Businessunit bean = this.businessunitrepository.getByBid(bid);
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
			bean.setCity(entity.getCity());
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
		Optional<Department> dep = departmentrepo.finddepartment(bid);
		Businessunit business = this.businessunitrepository.findByBid(bid);
		if(dep.isEmpty()) {
			businessunitrepository.delete(business);
			businessbean.setMessage("deleted successfully bid" + business.getBid());
			businessbean.setStatus(true);	

		}else {			
			businessbean.setMessage("Cannot delete Business Unit as it is associated to department");
			businessbean.setStatus(false);
		}

		return businessbean;
	}
	@Override
	public Businessbean getBuByOrgId(String orgId) {

		Businessbean response = new Businessbean();
		BusinessUnitResponseBean bean;
		List<BusinessUnitResponseBean> buList = new ArrayList<>();
		List<Businessunit> findByOrgIds = businessunitrepository.findByOrgIds(orgId);

		if(!findByOrgIds.isEmpty()) {
			for(Businessunit bu : findByOrgIds) {
				bean = new BusinessUnitResponseBean();
				bean.setId(bu.getId());
				bean.setBid(bu.getBid());
				bean.setOrgId(bu.getOrgInfoEntity().getOrgId());
				bean.setName(bu.getName());
				bean.setAddress1(bu.getAddress1());
				bean.setAddress2(bu.getAddress2());
				bean.setCity(bu.getCity());
				bean.setCode(bu.getCode());
				bean.setCountry(bu.getCountry());
				bean.setState(bu.getState());
				bean.setCreatedby(bu.getCreatedby());
				bean.setCreateddate(bu.getCreateddate());
				bean.setDescription(bu.getDescription());
				bean.setIsactive(bu.getIsactive());
				bean.setModifiedBy(bu.getModifiedBy());
				bean.setModifiedDate(bu.getModifiedDate());
				bean.setStartdate(bu.getStartdate());
				bean.setTimezone(bu.getTimezone());

				buList.add(bean);
			}
			response.setMessage("Successfully Fetched BusinessUnit Based On orgId");
			response.setStatus(true);
			response.setListOfBU(buList);

		}else {
			response.setMessage("No Data Found");
			response.setStatus(false);
			response.setListOfBU(null);
		}
		return response;
	}
}

package com.hrms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.CommonResponseBean;
import com.hrms.beans.OrganizationStructureResponseBean;
import com.hrms.entity.Businessunit;
import com.hrms.entity.Department;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.OrganizationInfoEntity;
import com.hrms.entity.Privileges;
import com.hrms.repository.BusinessunitRepository;
import com.hrms.repository.DepartmentRepo;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.OrganizationInfoRepository;
import com.hrms.repository.PrivilegesRepo;
import com.hrms.service.OrganizationInfoService;

@Service
public class OrganizationInfoServiceImpl implements OrganizationInfoService{
	
	@Autowired
	private PrivilegesRepo privilegesRepo;
	
	@Autowired
	private OrganizationInfoRepository orgInfoRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private CommonResponseBean response;
	
	@Autowired
	private BusinessunitRepository businessunitrepo;
	
	@Autowired
    private EmployeeRepository employeerepo;
	
	@Autowired
	private OrganizationStructureResponseBean orgresponsebean;

	@Override
	public CommonResponseBean getOrganizationInfo(int roleId, int menuId) {
		List<Privileges> listprivilleges = privilegesRepo.getPrivileges(roleId, menuId);
		OrganizationInfoEntity responseBean = orgInfoRepo.getOrganizationInfo();
		
		if (responseBean != null) {
			response.setMsg("Organization Info Data Retrived.");
			response.setStatus(true);
			response.setList(responseBean);
			response.setPrivillegesList(listprivilleges);
		}else {
			response.setMsg("Something went wrong!!");
			response.setStatus(false);
		}
		return response;
	}

	@Override
	public CommonResponseBean getDepartmentBasedOnBu(int businessId) {
	
		List<Department> listOfDuBasedOnBu = departmentRepo.listOfDuBasedOnBu(businessId);
		if(listOfDuBasedOnBu.size() != 0) {
			response.setMsg("List Of Department Based On BusinessId Fetched Successfully .");
			response.setStatus(true);
			response.setList(listOfDuBasedOnBu);
		}else {
			response.setMsg("Fetching Of List Of Department Based On BusinessId Failed");
			response.setStatus(false);
		}
		return response;
	}
	@Override
	public OrganizationStructureResponseBean getOrganizationStructure() {
		 List<Businessunit> business=businessunitrepo.findAll();
		 if(business.isEmpty()) {
			 response.setMsg("something went wrong!!");
			 response.setStatus(false);
			 return orgresponsebean;
			 
		 }else {
			 
			 orgresponsebean.setName("Rainier Softech Solutions");
			 orgresponsebean.setDesignation("Software Development");
			 orgresponsebean.setSubordinates(business);
			 return orgresponsebean;
			 
		 }
		 
			
	}
	@Override
	public List<EmployeeDetails> getOrganizationHirarchy(){
		List<EmployeeDetails> emp =employeerepo.findAll();
		return emp;	
		
	}

	@Override
	public CommonResponseBean saveorganization(OrganizationInfoEntity infoentity) {
		OrganizationInfoEntity saveentity=orgInfoRepo.save(infoentity);
		if(saveentity!=null)
		{
			response.setMsg("OrganizationInfo data save successfully");
			response.setStatus(true);
			}
		else {
			response.setMsg("OrganizationInfo data not saved");
			response.setStatus(false);;
			
		}
		
		
		return response;
	}

	@Override
	public List<OrganizationInfoEntity> getorganizationInfo() {
		
		return orgInfoRepo.findAll();
	}
	
}

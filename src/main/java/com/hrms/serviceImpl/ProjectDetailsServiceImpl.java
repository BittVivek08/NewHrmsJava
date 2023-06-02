package com.hrms.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.repository.ClientDetailsRepository;
import com.hrms.repository.ProjectDetailsRepository;
import com.hrms.repository.SalaryCurrencyRepo;
import com.hrms.service.ProjectDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

	// //oldHrms
	@Autowired
	private ProjectDetailsRepository projectRepo;
	
//	@Autowired
//	private CurrencyRepository currencyRepo;
	
	@Autowired
	private SalaryCurrencyRepo salaCurrencyRepo;
	

	@Autowired
	private EntityBeanResponseCommon beanResponse;

	@Autowired
	private ClientDetailsRepository clientRepo;
	

    @Autowired
	private ProjectResponseBean projectbean;

	// save
	// oldhrms
	@Override
	public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity projentity) {
		
		this.log.info("Entered save project Details in service ");

		//ClientDetailsEntity client = clientRepo.findByClientid(projentity.getClient().getClientid());
		//Optional<ClientDetailsEntity> client = this.clientRepo.findById(projentity.getClient().getId());
		Optional<ClientDetailsEntity> client = this.clientRepo.findById(projentity.getClient().getId());


		//Optional<Currency> currency = this.currencyRepo.findById(projentity.getCurrency().getCurrencyId());
		Optional<SalaryCurrencyEntity> currency = this.salaCurrencyRepo.findById(projentity.getCurrency().getId());

		
		if (client != null && currency!=null) {
			projentity.setCreated_date(LocalDateTime.now());
			projentity.setModifiedDate(LocalDateTime.now());
			
			projentity.setClient(client.get());
			projentity.setCurrency(currency.get());
			
			ProjectDetailsEntity save = this.projectRepo.save(projentity);
			this.log.info("successfully  saved project Details in service ");

			if (save != null) {
				beanResponse.setMsg("Successfully Project Details Saved");
				beanResponse.setStatus(true);
			} else {
				beanResponse.setMsg("ProjectDeatails not saved");
				beanResponse.setStatus(false);
				this.log.info("failed to   save project Details in service ");

			}
		} else {
			beanResponse.setMsg("No Clients : and Currency:  details not found  " + projentity.getClient().getId()+projentity.getCurrency().getId());
			beanResponse.setStatus(false);
		}
		return beanResponse;
	}


	// oldhrms
	// @Override
	public EntityBeanResponseCommon updateProjectDetails(int pid, ProjectDetailsEntity entity) {
		this.log.info("Entered update project Details in service ");

		Optional<ProjectDetailsEntity> updateEntity = this.projectRepo.findById(pid);
		ProjectDetailsEntity entityDB = updateEntity.get();
		//ClientDetailsEntity client = clientRepo.findByClientid(entity.getClient().getClientid());

		if (entityDB != null) {
			//entityDB.setCurrencyname(entity.getCurrencyname());
			//entityDB.setCurrency(entity.getCurrency().getCurrencyId());
			//entityDB.setProjectId(entity.getProjectId());
			//entityDB.setClient(client);
			entityDB.setDescription(entity.getDescription());
			entityDB.setEnddate(entity.getEnddate());
			entityDB.setStartdate(entity.getStartdate());
			entityDB.setEstimatedhours(entity.getEstimatedhours());
			entityDB.setIsactive(entity.getIsactive());
			entityDB.setLeadapprove(entity.getLeadapprove());
			entityDB.setProjectName(entity.getProjectName());
			entityDB.setProjectstatus(entity.getProjectstatus());
			//entityDB.setProjecttype(entity.getProjecttype());
			entityDB.setProject_type(entity.getProject_type());

			this.projectRepo.save(entityDB);
			this.log.info("successfully  updated project Details in service ");

			beanResponse.setMsg("Successfully Updated Project Details of Project Id :" + pid);
			beanResponse.setStatus(true);
		} else {
			beanResponse.setMsg("ProjectDetails Not Updated");
			beanResponse.setStatus(false);
			this.log.info("failed to  updated project Details in service ");
		}

		return beanResponse;
	}

	// oldhrms
	// specificFiledsOfprojectsByProjectId
	@Override
	public List<ProjectResponseBean> getAllProjects(int id) {
		this.log.info("Entered fetch  project Details by client id in service ");
		
		//ClientDetailsEntity clientInfo = clientRepo.findByClientid(id);
		Optional<ClientDetailsEntity> clientInfo = this.clientRepo.findById(id);
		
		
		ProjectResponseBean res;
		List<ProjectResponseBean> list = new ArrayList<ProjectResponseBean>();
		//List<ProjectDetailsEntity> projectList = this.projectRepo.findByClient(clientInfo.getClientid() );
		List<ProjectDetailsEntity> projectList= this.projectRepo.findByClient(clientInfo.get().getId());
		for (ProjectDetailsEntity proj : projectList) {
			res = new ProjectResponseBean();
			res.setProjectId(proj.getProjectId());
			//res.setClientid(proj.getClient().getClientid());
			res.setClientid(proj.getClient().getId());
			res.setDescription(proj.getDescription());
			//res.setCurrencyName(proj.getCurrencyname());
			res.setCurrencyid(proj.getCurrency().getId());
			//res.setEndDate(proj.getEnddate());
			res.setEndDate(proj.getEnddate());
			res.setEstimatedhours(proj.getEstimatedhours());
			res.setIs_active(proj.getIsactive());
			res.setLeadAppove(proj.getLeadapprove());
			res.setProjectName(proj.getProjectName());
			//res.setProjectType(proj.getProjecttype());
			res.setProjectType(proj.getProject_type());
			//res.setStartDate(proj.getStartdate());
			res.setStartDate(proj.getStartdate());
			res.setStatus(proj.getProjectstatus());
		
			list.add(res);
			
			this.log.info("successfully fteched  project Details in service ");
		}

		return  list;

	}

}

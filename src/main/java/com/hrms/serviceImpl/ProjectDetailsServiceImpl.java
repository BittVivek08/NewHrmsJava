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
	

	//@Autowired
	//private ProjectRespoonseBean projectbean;

	// save
	// oldhrms
	@Override
	public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity projentity) {

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

			if (save != null) {
				beanResponse.setMsg("Successfully Project Details Saved");
				beanResponse.setStatus(true);
			} else {
				beanResponse.setMsg("ProjectDeatails not saved");
				beanResponse.setStatus(false);
			}
		} else {
			beanResponse.setMsg("No Clients : and Currency:  details not found  " + projentity.getClient().getId()+projentity.getCurrency().getId());
			beanResponse.setStatus(false);
		}
		return beanResponse;
	}

	// oldHrms
	// FindProjectDetailsByClienId
//	@Override
//	public List<ProjectDetailsEntity> getProjectListByClienyId(int cid) {
//		List<ProjectDetailsEntity> listProjects = this.projectRepo.findByClient(cid);
//		return listProjects;
//	}

	// oldhrms
	// @Override
	public EntityBeanResponseCommon updateProjectDetails(int pid, ProjectDetailsEntity entity) {

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

			beanResponse.setMsg("Successfully Updated Project Details of Project Id :" + pid);
			beanResponse.setStatus(true);
		} else {
			beanResponse.setMsg("ProjectDetails Not Updated");
			beanResponse.setStatus(false);
		}

		return beanResponse;
	}

	// oldhrms
	// specificFiledsOfprojectsByProjectId
	@Override
	public List<ProjectResponseBean> getAllProjects(int id) {
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
		}

		return  list;

	}

	/*
	 * //postNaotherway
	 * 
	 * @Override public EntityBeanResponse postProjectDetails(ProjectDetailsEntity
	 * entity) {
	 * 
	 * ProjectDetailsEntity p=new ProjectDetailsEntity();
	 * p.setClient(entity.getClient()); ProjectDetailsEntity save =
	 * this.projectRepo.save(p); if(save!=null) { beanResponse.setMsg("success");
	 * beanResponse.setStatus(true); }
	 * 
	 * 
	 * 
	 * return beanResponse;
	 * 
	 * }
	 */

	// findProjectsByClientId

	/*
	 * @Override public List<ProjectDetailsEntity> getProjectListByClienyId(int cid)
	 * { ClientDetailsEntity findByClientid = clientRepo.findByClientid(cid);
	 * 
	 * 
	 * 
	 * List<ProjectDetailsEntity> findByClient = this.projectRepo.findByClient(cid);
	 * 
	 * return findByClient; }
	 */

	/*
	 * //getprojectDetailsForClientId
	 * 
	 * @Override public ProjectRespoonseBean getListOfProjectDetailsByid(int client)
	 * {
	 * 
	 * Optional<ProjectDetailsEntity> findById = this.projectRepo.findById(client);
	 * 
	 * if(!findById.isEmpty()) {
	 * projectbean.setMesaagae("Successfully Project Details Details fetched");
	 * projectbean.setStatus(true); projectbean.setProjectlist(findById);
	 * 
	 * 
	 * } else { projectbean.setMesaagae("Failed to fetch projectDetails");
	 * projectbean.setStatus(false); }
	 * 
	 * 
	 * return projectbean; }
	 */

	// FindProjectDetailsByClienId

//	@Override
//	public List<ProjectDetailsEntity> getProjectListByClienyId(int cid) {
//		ClientDetailsEntity clientInfo = clientRepo.findByClientid(cid);
//		ProjectResponse res;
//		List<ProjectResponse> list;
//		List<ProjectDetailsEntity> projectList = this.projectRepo.findByClient(clientInfo.getClientid());
//		for (ProjectDetailsEntity proj : projectList) {
//			res.setProjectId(proj.getProjectid());
//
//			list.add(res);
//		}
//
//		return projectList;
//	}
	
	
//...................................OldHrms............................................................	
	
	// save
		// oldhrms
//		@Override
//		public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity projentity) {
//
//			ClientDetailsEntity client = clientRepo.findByClientid(projentity.getClient().getClientid());
//			if (client != null) {
//				projentity.setClient(client);
//				ProjectDetailsEntity save = this.projectRepo.save(projentity);
//
//				if (save != null) {
//					beanResponse.setMsg("Successfully Project Details Saved");
//					beanResponse.setStatus(true);
//				} else {
//					beanResponse.setMsg("ProjectDeatails not saved");
//					beanResponse.setStatus(false);
//				}
//			} else {
//				beanResponse.setMsg("No Clients found with clientId " + projentity.getClient().getClientid());
//				beanResponse.setStatus(false);
//			}
//			return beanResponse;
//		}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

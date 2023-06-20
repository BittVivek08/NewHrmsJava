package com.hrms.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.ProjectEmployeeDataBean;
import com.hrms.beans.ProjectEmployeeFetchBean;
import com.hrms.beans.ProjectEmployeeFetchResponse;
import com.hrms.beans.ProjectEmployeeResponseBean;
//import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.ProjectEmployeeEntity;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ProjectDetailsRepository;
import com.hrms.repository.ProjectEmployeeRepository;
import com.hrms.service.ProjectEmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService {

	@Autowired
	private ProjectDetailsRepository projectRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private ProjectEmployeeRepository projectEmpRepo;

	@Autowired
	private ProjectEmployeeResponseBean resBean;

	@Autowired
	private ProjectEmployeeFetchResponse response;

	@Override
	public ProjectEmployeeResponseBean saveProjectEmployee(ProjectEmployeeDataBean Databean) {

		ProjectDetailsEntity projectId = this.projectRepo.findById(Databean.getProjectid()).get();

		EmployeeDetails empId = this.employeeRepo.findByEmpId(Databean.getEmpid());

		// List<ProjectEmployeeEntity> listProjectAndEmployee =
		// this.projectEmpRepo.findByProjectAndEmployee(Databean.getProjectid(),
		// Databean.getEmpid());
		List<ProjectEmployeeEntity> listProjectAndEmployee = this.projectEmpRepo
				.getProjectEmployeeExistedDetails(Databean.getProjectid(), Databean.getEmpid());
		if (listProjectAndEmployee.isEmpty()) {

			ProjectEmployeeEntity entity = new ProjectEmployeeEntity();
			if (Databean != null) {
				entity.setProject(projectId);
				// entity.setProjectName(projectId.getProjectName());
				entity.setEmployee(empId);
				entity.setCreated(LocalDateTime.now());
				entity.setCreatedby(Databean.getEmpid());
				//LocalDate startdate = LocalDate.parse(Databean.getStartdate());
				//entity.setStartdate(startdate);
				entity.setStartdate(projectId.getStartdate());
				//LocalDate enddate = LocalDate.parse(Databean.getEnddate());
				//entity.setEndDate(enddate);
				entity.setEndDate(projectId.getEnddate());
				entity.setBillable(Databean.getBillable());
				// entity.setModified(LocalDateTime.now());

				ProjectEmployeeEntity projectEmpEntity = projectEmpRepo.save(entity);

				if (projectEmpEntity != null) {
					this.resBean.setMessage("successfully saved projectEmployee Details");
					this.resBean.setStatus(true);

				} else {
					this.resBean.setMessage("failed to  save projectEmployee details");
					this.resBean.setStatus(false);
				}
			}
		} else {
			resBean.setMessage("Already data mapped");
			resBean.setStatus(false);
		}

		return resBean;
	}

	@Override
	public ProjectEmployeeFetchResponse getAllDetails() {

		ProjectEmployeeFetchBean bean;

		List<ProjectEmployeeFetchBean> listbean = new ArrayList<>();

		List<ProjectEmployeeEntity> listentity = this.projectEmpRepo.findAll();
		for (ProjectEmployeeEntity entity : listentity) {

			bean = new ProjectEmployeeFetchBean();
			bean.setId(entity.getId());
			bean.setProjectId(entity.getProject().getProjectId());
			bean.setEmployeeId(entity.getEmployee().getEmpId());
			bean.setCreatedby(entity.getCreatedby());
			bean.setModifiedby(entity.getModifiedby());
			bean.setEndDate(entity.getEndDate());
			bean.setStartDate(entity.getStartdate());
			bean.setCreatedDate(entity.getCreated());
			bean.setModifiedDate(entity.getModified());
			bean.setBillable(entity.getBillable());

			boolean add = listbean.add(bean);
		}

		if (listbean != null) {
			this.response.setListProjectEmpDeatils(listbean);
			this.response.setMessage("successfully fteched details");
			this.response.setStatus(true);
		} else {
			this.response.setMessage("failed to fetch details");
			this.response.setStatus(false);
		}

		return response;
	}

	@Override
	public ProjectEmployeeResponseBean updateProjEmpDetails(int id, ProjectEmployeeEntity entity) {
		try {
			ProjectEmployeeEntity ProjEmp = this.projectEmpRepo.findById(id).get();
			if (ProjEmp != null) {

				ProjEmp.setEndDate(entity.getEndDate());
				ProjEmp.setStartdate(entity.getStartdate());
				ProjEmp.setModified(LocalDateTime.now());
				ProjEmp.setModifiedby(ProjEmp.getEmployee().getEmpId());
				ProjEmp.setBillable(entity.getBillable() );
				ProjectEmployeeEntity save = this.projectEmpRepo.save(ProjEmp);

				if (save != null) {
					this.resBean.setMessage(
							"successfully updated details of EmployeeOId : " + ProjEmp.getEmployee().getEmpId()
									+ " and ProjectId  : " + ProjEmp.getProject().getProjectId());
					this.resBean.setStatus(true);
				} else {
					this.resBean.setMessage("Failed to update details ");
					this.resBean.setStatus(false);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resBean.setMessage("Details not found");
			resBean.setStatus(false);

		}

		return resBean;
	}

}

package com.hrms.serviceImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmployeeTaskDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.TasksDetailsResponseBean;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.repository.ClientDetailsRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ProjectDetailsRepository;
import com.hrms.repository.TaskDeatailsRepository;
import com.hrms.request.bean.TaskListResponseBean;
import com.hrms.service.TaskDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskDetailsServiceImpl implements TaskDetailsService {

	@Autowired
	private TaskDeatailsRepository taskRepo;

	@Autowired
	private ProjectDetailsRepository projrepo;

	@Autowired
	private EntityBeanResponseCommon beanResponse;

	@Autowired
	private TasksDetailsResponseBean taskbean;

	@Autowired
	private EmployeeTaskDetailsBean emptasjbean;

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	private TaskListResponseBean listOfTaskBean;

	// OldHrms
	// save
	@Override
	public EntityBeanResponseCommon saveTaskDeatils(TaskDetailsEntity entity) {

		this.log.info("Entered save task details in service");

		// ClientDetailsEntity Client =
		// this.clientRepo.findByClientid(entity.getProject().getClient().getClientid());
		// ProjectDetailsEntity Project =
		// this.projrepo.findByProjectId(entity.getProject().getProjectId());
		// Project.setClient(Client);

		TaskDetailsEntity task = new TaskDetailsEntity();

		task.setTasknmae(entity.getTasknmae());
		task.setCreated_by(entity.getCreated_by());
		task.setCreateddate(LocalDateTime.now());
		task.setIs_active(1);
		task.setIs_default(true);
		TaskDetailsEntity save = this.taskRepo.save(task);
		this.log.info("successsfully save task details in service");
		if (save != null) {
			beanResponse.setMsg("successfully saved Task details");
			beanResponse.setStatus(true);

		} else {
			beanResponse.setMsg("Failed to save task details");
			beanResponse.setStatus(false);
			this.log.info("failed to  save task details in service");

		}

		return beanResponse;
	}

	// OldHrms
	// fetchTasksByProjectId
	@Override
	public TaskListResponseBean getTaskByProjectId() {

		this.log.info("Entered fetch list  task details in service");

		TasksDetailsResponseBean bean = null;

		List<TasksDetailsResponseBean> list = new ArrayList<TasksDetailsResponseBean>();

		List<TaskDetailsEntity> listOfTaskEntity = this.taskRepo.findAll();

		for (TaskDetailsEntity listof : listOfTaskEntity) {

			bean = new TasksDetailsResponseBean();
			bean.setTaskid(listof.getTaskid());
			bean.setTaskname(listof.getTasknmae());
			bean.setCreateddate(listof.getCreateddate());
			bean.setCreatedBy(listof.getCreated_by());
			bean.setModifiedBy(listof.getModified_by());
			bean.setIs_active(listof.getIs_active());
			bean.setModifiedDate(listof.getModifieddate());

			boolean add = list.add(bean);
		}

		if (!list.isEmpty()) {
			listOfTaskBean.setMessage("successfully fetched details");
			listOfTaskBean.setStatus(true);
			listOfTaskBean.setListOfTasks(list);
			this.log.info("successfully fetch list  task details in service");

		} else {
			listOfTaskBean.setMessage("Failed to fetched details");
			listOfTaskBean.setStatus(false);
		}

		return listOfTaskBean;
	}

	// OldHrms
	// delete
	@Override
	public EntityBeanResponseCommon deleteTaskById(int id) {
		this.log.info("Entered delete task details in service");
       try {
       
		Optional<TaskDetailsEntity> task = this.taskRepo.findById(id);
		if (id==task.get().getTaskid()) {
			this.taskRepo.deleteById(id);
			this.beanResponse.setMsg("Successfully deleted task  id is :" + id);
			this.beanResponse.setStatus(true);
			this.log.info("successfully delete task details in service");
		} else {
			this.beanResponse.setMsg("Enter Valid Id Of Task");
			this.beanResponse.setStatus(false);
			this.log.info("failed to delete task details in service");
		}
		}catch (Exception e) {
			e.printStackTrace();
			beanResponse.setMsg("please enter valid task id ");
			beanResponse.setStatus(false);
		}

		return beanResponse;
	}

	// OldHrms
	// update
@Override
	public EntityBeanResponseCommon updateTaskByTaskId(int id, TaskDetailsEntity task) {
		this.log.info("Entered update  task details in service");

	try {
	
		TaskDetailsEntity entity = this.taskRepo.findById(id).orElse(null);
		if(entity!=null)
		{
			entity.setTasknmae(task.getTasknmae());
			entity.setModifieddate(LocalDateTime.now());
			entity.setIs_active(1);
			entity.setModified_by(task.getModified_by());
			entity.setIs_default(true);
			TaskDetailsEntity save = this.taskRepo.save(entity);
			if(save!=null) {
			this.log.info("succcessfully updated  task details in service");
			beanResponse.setMsg("successfully updated Task details of task id : "+id );
			beanResponse.setStatus(true);}
			else {
				beanResponse.setMsg("failed to  update Task details of task id : "+id );
				beanResponse.setStatus(true);}
			}else {
				beanResponse.setMsg("please enter valid task id/2");
				beanResponse.setStatus(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			beanResponse.setMsg("Deatils not found with given id "+id );
			beanResponse.setStatus(false);
			}
		
			this.log.info("failed to  updated  task details in service");
		return beanResponse;
	}
	

}

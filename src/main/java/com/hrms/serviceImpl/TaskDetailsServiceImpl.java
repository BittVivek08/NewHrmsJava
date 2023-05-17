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
import com.hrms.service.TaskDetailsService;

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
	ClientDetailsRepository clientRepo;

	
	//OldHrms
	//save
//	@Override
	public EntityBeanResponseCommon saveTaskDeatils(TaskDetailsEntity entity) {

		// ClientDetailsEntity Client =
		// this.clientRepo.findByClientid(entity.getProject().getClient().getClientid());
		ProjectDetailsEntity Project = this.projrepo.findByProjectId(entity.getProject().getProjectId());
		// Project.setClient(Client);

		entity.setProject(Project);
		entity.setCreated_by(1);
		entity.setCreateddate(LocalDateTime.now());
		entity.setIs_active(1);
		entity.setIs_default(true);
		entity.setModified_by(1);
		entity.setModifieddate(LocalDateTime.now());
		
		TaskDetailsEntity save = this.taskRepo.save(entity);
		if (save != null) {
			beanResponse.setMsg("successfully saved Task details");
			beanResponse.setStatus(true);

		} else {
			beanResponse.setMsg("Failed to save task details");
			beanResponse.setStatus(false);

		}

		return beanResponse;
	}

	
	
	//OldHrms
	// fetchTasksByProjectId
	@Override
	public List<TasksDetailsResponseBean> getTaskByProjectId(int id) {

		TasksDetailsResponseBean bean = null;

		List<TasksDetailsResponseBean> list = new ArrayList<TasksDetailsResponseBean>();

		List<TaskDetailsEntity> task = this.taskRepo.findByProject(id);
		for (TaskDetailsEntity entity : task) {
			bean = new TasksDetailsResponseBean();
			bean.setTaskid(entity.getTaskid());
			bean.setProjectId(entity.getProject().getProjectId());
			bean.setCreateddate(entity.getCreateddate());
			bean.setModifiedDate(entity.getModifieddate());
			bean.setIs_active(entity.getIs_active());
			bean.setTaskname(entity.getTasknmae());

			list.add(bean);
		}

		return list;
	}

	//OldHrms
	//delete
	@Override
	public EntityBeanResponseCommon deleteTaskById(int id) {
		Optional<TaskDetailsEntity> task = this.taskRepo.findById(id);
		if (id == task.get().getTaskid()) {
			this.taskRepo.deleteById(id);
			this.beanResponse.setMsg("Successfully deleted task  id is :" + id);
			this.beanResponse.setStatus(true);
		} else {
			this.beanResponse.setMsg("Enter Valid Id Of Task");
			this.beanResponse.setStatus(false);
		}

		return beanResponse;
	}
	
	//OldHrms
	//update
	@Override
	public EntityBeanResponseCommon updateTaskByTaskId(int id, TaskDetailsEntity task) {

		//ProjectDetailsEntity project = this.projrepo.findById(id).orElse(null);
		TaskDetailsEntity entity = this.taskRepo.findById(id).orElse(null);
		//entity.setProject(project);
		// BeanUtils.copyProperties(task,
		//if (entity.getTaskid() == task.getTaskid()) {
		
		if(task!=null && entity!=null)
		{
			entity.setTasknmae(task.getTasknmae());
			//entity.setProject(task.getProject());
			//entity.setTaskid(task.getTaskid());
			//entity.setCreateddate(task.getCreateddate());
			entity.setCreateddate(LocalDateTime.now());
			//entity.setModifieddate(task.getModifieddate());
			entity.setModifieddate(LocalDateTime.now());
			entity.setIs_active(1);
			entity.setIs_default(true);
			entity.setModified_by(1);
			entity.setCreated_by(1);
			entity.setIs_default(true);
			
			this.taskRepo.save(entity);
			beanResponse.setMsg("successfully updated Task details of task id : "+id );
			beanResponse.setStatus(true);
		} else {
			beanResponse.setMsg("Failed to update Task Detils please enter valid data");
			beanResponse.setStatus(false);
		}

		return beanResponse;
	}

}

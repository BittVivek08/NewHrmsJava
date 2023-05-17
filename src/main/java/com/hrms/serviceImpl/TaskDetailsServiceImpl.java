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

	
	
	
	

    //OldHrms
	//addListOfTaskToEmp
//	@Override
//	public EntityBeanResponseCommon addListOfTaskToEmp(TaskDetailsEntity task) {
//
//		EmployeeDetails empl = employeeRepo.findByEmpId(task.getEmp().getEmpId());
//		task.setEmp(empl);
//		// ProjectDetailsEntity project =
//		// projrepo.findByProjectId(task.getProject().getProjectId());
//		// ClientDetailsEntity client =
//		// clientRepo.findByClientid(project.getClient().getClientid());
//		// project.setClient(client);
//		// task.setProject(project);
//		TaskDetailsEntity save = this.taskRepo.save(task);
//
//		if (save != null) {
//			beanResponse.setMsg("successfully saved List of Task Details to One Employee ");
//			beanResponse.setStatus(true);
//		} else {
//			beanResponse.setMsg("failed to save Task details to emp");
//			beanResponse.setStatus(false);
//		}
//		return beanResponse;
//	}
    
	////OldHrms
	//getListOfTaskByEmpid
	//@Override
//	public List<TaskDetailsEntity> getListTasksByEmpid(String id) {
//
//		List<TaskDetailsEntity> listEmp = this.taskRepo.findByEmp(id);
//
//		return listEmp;
//	}

	//OldHrms
	//getlistoftasksByEmpId
//	@Override
//	public List<EmployeeTaskDetailsBean> getEmpListOfTasksByEmpId(String eid) {
//
//		EmployeeTaskDetailsBean EmpBean = null;
//
//		List<EmployeeTaskDetailsBean> listEmpBean = new ArrayList<>();
//
//		List<TaskDetailsEntity> TaskEntity = this.taskRepo.findByEmp(eid);
//
//		for (TaskDetailsEntity taskemp : TaskEntity) {
//			EmpBean = new EmployeeTaskDetailsBean();
//			EmpBean.setTaskid(taskemp.getTaskid());
//			EmpBean.setTaskname(taskemp.getTasknmae());
//			EmpBean.setCreatedDate(taskemp.getCreateddate());
//			EmpBean.setModifiedDate(taskemp.getModifieddate());
//			EmpBean.setIs_active(taskemp.getIs_active());
//			EmpBean.setProjectId(taskemp.getProject().getProjectId());
//			EmpBean.setProjectName(taskemp.getProject().getProjectName());
//
//			listEmpBean.add(EmpBean);
//		}
//
//		return listEmpBean;
//	}

	// fetchTasksByProjectId
	/*
	 * @Override public List<TaskDetailsEntity> getTaskByProjectId(int id) {
	 * 
	 * List<TaskDetailsEntity> listtasks = this.taskRepo.findByProject(id);
	 * 
	 * return listtasks; }
	 */

	// @Override
	// public EntityBeanResponse saveTaskDetails(TaskDetailsEntity entity) {

	// TaskDetailsEntity save = this.taskRepo.save(entity);
	// if(save!=null) {
	// beanResponse.setMsg("Task Details Successfully saved");
	// beanResponse.setStatus(true);
	// }else {
	// beanResponse.setMsg("Task Details not saved..");
	// beanResponse.setStatus(false);
	// }
	// return beanResponse;
	// }

	// @Override
	// public EntityBeanResponse updateTask(int id, TaskDetailsEntity entity) {

	// Optional<TaskDetailsEntity> findById = this.taskRepo.findById(id);
	// if(entity!=null) {
	// findById.orElseThrow().setTaskid(entity.getTaskid());
	// findById.orElseThrow().setTasknmae(entity.getTasknmae());
	// findById.orElseThrow().setCreateddate(entity.getCreateddate());
	// findById.orElseThrow().setModifieddate(entity.getModifieddate());
	// findById.orElseThrow().setIs_active(entity.getIs_active());

	// this.taskRepo.save(findById.orElseThrow());
	// beanResponse.setMsg("successfully updated task details");
	// beanResponse.setStatus(true);
	// }else {

	// beanResponse.setMsg("Failed to update Task detils");
	// beanResponse.setStatus(false);

	// }

	// return beanResponse;
	// }

	// @Override
	// public EntityBeanResponse deleteTakById(int id) {
	// Optional<TaskDetailsEntity> findById = this.taskRepo.findById(id);
	// this.taskRepo.delete(findById.orElseThrow());
	// beanResponse.setMsg("successfully Task Deleted Id :"+id);
	// beanResponse.setStatus(true);

	// return beanResponse;
	// }

	// @Override
	// public TasksDetailsResponseBean getAllTasks() {
	// List<TaskDetailsEntity> findAll = this.taskRepo.findAll();

	// if(!findAll.isEmpty()) {
	// taskbean.setMessage("successfully all fetched task details ");
	// taskbean.setStatus(true);
	// taskbean.setListOfTask(findAll);
	// }else {
	// taskbean.setMessage("failed to fetch the all task details");
	// taskbean.setStatus(false);
	// }

	// return taskbean;
	// }

	/*
	 * @Override public EntityBeanResponse updateTaskByTaskId(int id,
	 * TaskDetailsEntity task) {
	 * 
	 * //Optional<ProjectDetailsEntity> project = this.projrepo.findById(id);
	 * Optional<TaskDetailsEntity> entity = this.taskRepo.findById(id);
	 * //entity.orElseThrow().setProject(project); // BeanUtils.copyProperties(task,
	 * entity); if (entity.get().getTaskid() == task.getTaskid()) {
	 * 
	 * entity.orElseThrow().setTasknmae(task.getTasknmae());
	 * entity.orElseThrow().setProject(task.getProject());
	 * entity.orElseThrow().setTaskid(task.getTaskid());
	 * entity.orElseThrow().setCreateddate(task.getCreateddate());
	 * entity.orElseThrow().setModifieddate(task.getModifieddate());
	 * entity.orElseThrow().setIs_active(task.getIs_active());
	 * 
	 * this.taskRepo.save(entity.orElseThrow());
	 * beanResponse.setMsg("successfully updated Task details");
	 * beanResponse.setStatus(true); } else {
	 * beanResponse.setMsg("Failed to update Task Detils please enter valid data");
	 * beanResponse.setStatus(false); }
	 * 
	 * return beanResponse; }
	 */
	
	
	
	//.............................................OldHrms...............................
	
	//OldHrms
	//save
//	@Override
//	public EntityBeanResponseCommon saveTaskDeatils(TaskDetailsEntity entity) {
//
//		// ClientDetailsEntity Client =
//		// this.clientRepo.findByClientid(entity.getProject().getClient().getClientid());
//		ProjectDetailsEntity Project = this.projrepo.findByProjectId(entity.getProject().getProjectId());
//		// Project.setClient(Client);
//
//		entity.setProject(Project);
//
//		TaskDetailsEntity save = this.taskRepo.save(entity);
//		if (save != null) {
//			beanResponse.setMsg("successfully saved Task details");
//			beanResponse.setStatus(true);
//
//		} else {
//			beanResponse.setMsg("Failed to save task details");
//			beanResponse.setStatus(false);
//
//		}
//
//		return beanResponse;
//	}
	
	
	
	//OldHrms
	// fetchTasksByProjectId
//	@Override
//	public List<TasksDetailsResponseBean> getTaskByProjectId(int id) {
//
//		TasksDetailsResponseBean bean = null;
//
//		List<TasksDetailsResponseBean> list = new ArrayList<TasksDetailsResponseBean>();
//
//		List<TaskDetailsEntity> task = this.taskRepo.findByProject(id);
//		for (TaskDetailsEntity entity : task) {
//			bean = new TasksDetailsResponseBean();
//			bean.setTaskid(entity.getTaskid());
//			bean.setProjectId(entity.getProject().getProjectId());
//			bean.setCreateddate(entity.getCreateddate());
//			bean.setModifiedDate(entity.getModifieddate());
//			bean.setIs_active(entity.getIs_active());
//			bean.setTaskname(entity.getTasknmae());
//
//			list.add(bean);
//		}
//
//		return list;
//	}	
	
	
	
	
	//OldHrms
	//delete
//	@Override
//	public EntityBeanResponseCommon deleteTaskById(int id) {
//		Optional<TaskDetailsEntity> task = this.taskRepo.findById(id);
//		if (id == task.get().getTaskid()) {
//			this.taskRepo.deleteById(id);
//			this.beanResponse.setMsg("Successfully deleted task  id is :" + id);
//			this.beanResponse.setStatus(true);
//		} else {
//			this.beanResponse.setMsg("Enter Valid Id Of Task");
//			this.beanResponse.setStatus(false);
//		}
//
//		return beanResponse;
//	}
		
	
	
	//OldHrms
		//update
//		@Override
//		public EntityBeanResponseCommon updateTaskByTaskId(int id, TaskDetailsEntity task) {
//
//			ProjectDetailsEntity project = this.projrepo.findById(id).orElse(null);
//			TaskDetailsEntity entity = this.taskRepo.findById(id).orElse(null);
//			entity.setProject(project);
//			// BeanUtils.copyProperties(task,
//			if (entity.getTaskid() == task.getTaskid()) {
//
//				entity.setTasknmae(task.getTasknmae());
//				entity.setProject(task.getProject());
//				entity.setTaskid(task.getTaskid());
//				entity.setCreateddate(task.getCreateddate());
//				entity.setModifieddate(task.getModifieddate());
//				entity.setIs_active(task.getIs_active());
//
//				this.taskRepo.save(entity);
//				beanResponse.setMsg("successfully updated Task details");
//				beanResponse.setStatus(true);
//			} else {
//				beanResponse.setMsg("Failed to update Task Detils please enter valid data");
//				beanResponse.setStatus(false);
//			}
//
//			return beanResponse;
//		}
	
	
	
//..................oldHrms.........................	
	
	  //OldHrms
		//addListOfTaskToEmp
//		@Override
//		public EntityBeanResponseCommon addListOfTaskToEmp(TaskDetailsEntity task) {
	//
//			EmployeeDetails empl = employeeRepo.findByEmpId(task.getEmp().getEmpId());
//			task.setEmp(empl);
//			// ProjectDetailsEntity project =
//			// projrepo.findByProjectId(task.getProject().getProjectId());
//			// ClientDetailsEntity client =
//			// clientRepo.findByClientid(project.getClient().getClientid());
//			// project.setClient(client);
//			// task.setProject(project);
//			TaskDetailsEntity save = this.taskRepo.save(task);
	//
//			if (save != null) {
//				beanResponse.setMsg("successfully saved List of Task Details to One Employee ");
//				beanResponse.setStatus(true);
//			} else {
//				beanResponse.setMsg("failed to save Task details to emp");
//				beanResponse.setStatus(false);
//			}
//			return beanResponse;
//		}
	    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

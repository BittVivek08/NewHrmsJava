package com.hrms.serviceImpl;

import java.awt.geom.Area;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.CurrentWeekDatesResponse;
import com.hrms.beans.DateResponseInTimeSheet;
import com.hrms.beans.EntityBeanResponse;

import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
//import com.hrms.entity.EmployeeWorkStatusEntity;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.repository.ClientDetailsRepository;
//import com.hrms.entity.ProjectTaskEntity;
//import com.hrms.entity.SavingTimeSheet;
//import com.hrms.entity.Task;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.IRequestForLeaveRepository;
import com.hrms.repository.ProjectDetailsRepository;
//import com.hrms.repository.EmployeeWorkStatusRepo;
import com.hrms.repository.SaveTimeSheetRepo;
import com.hrms.repository.TaskDeatailsRepository;
import com.hrms.response.bean.CommonTimeSheetbean;
import com.hrms.response.bean.DateResponseTimeSheet;
import com.hrms.response.bean.ProjectListResponse;
import com.hrms.response.bean.ProjectResponse;
import com.hrms.response.bean.TSResponseEmployeeName;
import com.hrms.response.bean.TSResponseObj;
import com.hrms.response.bean.TimeSheetResponse;
import com.hrms.response.bean.TimeSheetsMonthlyBean;
import com.hrms.service.TimeSheetDetails;
import com.hrms.util.CurrentWeekDates;
import com.hrms.util.Timesheet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimeSheetDetailsImpl implements TimeSheetDetails {

	@Autowired
	private EntityBeanResponse entityBeanResponse;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SaveTimeSheetRepo saveTimeSheetRepo;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private ProjectDetailsRepository pojectDetailsRepository;
	@Autowired
	private TaskDeatailsRepository taskDetailsRepository;
	@Autowired
	private HolidayCalenderRepository holidayCalenderRepository;

	@Autowired
	private TimeSheetResponse timeSheetResponse;
	@Autowired
	private IRequestForLeaveRepository iRequestForLeaveRepository;

	public TimeSheetResponse saveTimeSheett(List<SaveTimeSheet> savetimesheetList) {
		log.info("entered into saveTimeSheett  method of HrmsEmpTimeSheetService class");
		for(SaveTimeSheet savetimesheet : savetimesheetList) {
			LocalDate date1 = iRequestForLeaveRepository.findByStartDate(LocalDate.now(),savetimesheet.getEmp().getEmpId());
			LocalDate date = holidayCalenderRepository.findByDate1(LocalDate.now());
			String leave = iRequestForLeaveRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
			try {
				if (date == null && date1 == null && leave != savetimesheet.getEmp().getEmpId()) {

					int cal = Integer.parseInt(savetimesheet.getFri_hours())
							+ Integer.parseInt(savetimesheet.getMon_hours())
							+ Integer.parseInt(savetimesheet.getSat_hours())
							+ Integer.parseInt(savetimesheet.getThurs_hours())
							+ Integer.parseInt(savetimesheet.getSun_hours())
							+ Integer.parseInt(savetimesheet.getTue_hours())
							+ Integer.parseInt(savetimesheet.getWed_hours());
					savetimesheet.setTotalWeekHours(String.valueOf(cal));
					savetimesheet.setStatus("pending");
					savetimesheet.setIsActive(0 < 1);
					EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
					ClientDetailsEntity client = this.clientDetailsRepository.findById(savetimesheet.getClient().getId())
							.get();
					ProjectDetailsEntity proj = this.pojectDetailsRepository
							.findByProjectId(savetimesheet.getProj().getProjectId());
					TaskDetailsEntity task = this.taskDetailsRepository.findById(savetimesheet.getTask().getTaskid()).get();
					int taskN=task.getTaskid();
					String taskName = this.taskDetailsRepository.findByTaskName(taskN);
					savetimesheet.setTask(task);
					savetimesheet.setProj(proj);
					savetimesheet.setClient(client);
					savetimesheet.setEmp(emp1);
					savetimesheet.setCreatedBy(1);
					savetimesheet.setTaskName(taskName);
					savetimesheet.setCreated_Date(LocalDateTime.now());
					SaveTimeSheet result = this.saveTimeSheetRepo.save(savetimesheet);
					timeSheetResponse.setMsg("successfully added data ");
					timeSheetResponse.setStatus(true);


				} else {
					timeSheetResponse.setMsg("today is leave/holiday you what to enter timesheetDetails than click yes/no");
					//

					if (date == null || date1 == LocalDate.now() || date == LocalDate.now()
							|| date1 == null && savetimesheet.getEmp().getEmpId() == leave) {

						if (savetimesheet.getRequest().equalsIgnoreCase("yes")) {

							int cal = Integer.parseInt(savetimesheet.getFri_hours())
									+ Integer.parseInt(savetimesheet.getMon_hours())
									+ Integer.parseInt(savetimesheet.getSat_hours())
									+ Integer.parseInt(savetimesheet.getThurs_hours())
									+ Integer.parseInt(savetimesheet.getSun_hours())
									+ Integer.parseInt(savetimesheet.getTue_hours())
									+ Integer.parseInt(savetimesheet.getWed_hours());
							savetimesheet.setTotalWeekHours(String.valueOf(cal));
							savetimesheet.setStatus("pending");
							EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
							ClientDetailsEntity client = this.clientDetailsRepository
									.findById(savetimesheet.getClient().getId()).get();
							ProjectDetailsEntity proj = this.pojectDetailsRepository
									.findByProjectId(savetimesheet.getProj().getProjectId());
							TaskDetailsEntity task = this.taskDetailsRepository
									.findById(savetimesheet.getTask().getTaskid()).get();
							savetimesheet.setTask(task);
							savetimesheet.setProj(proj);
							savetimesheet.setClient(client);
							savetimesheet.setEmp(emp1);
							savetimesheet.setCreatedBy(1);
							savetimesheet.setCreated_Date(LocalDateTime.now());
							this.saveTimeSheetRepo.save(savetimesheet);
							timeSheetResponse.setMsg("successfully added data in data base");
							timeSheetResponse.setStatus(true);
						}

						else {

							timeSheetResponse.setMsg("Today is holliday enjoy your day");
							return timeSheetResponse;
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return timeSheetResponse;
	}
	public void deleteTimeSheet(int id) {
		this.saveTimeSheetRepo.deleteById(id);
	}

	public SaveTimeSheet getTimeSheetDetail(int id) {
		return this.saveTimeSheetRepo.findById(id).get();
	}

	public void updateTimeSheet(SaveTimeSheet savetimesheet, int id)

	{
		log.info("entered into updateTimeSheet  method of HrmsEmpTimeSheetService class");
		int cal = Integer.parseInt(savetimesheet.getFri_hours()) + Integer.parseInt(savetimesheet.getMon_hours())
		+ Integer.parseInt(savetimesheet.getSat_hours()) + Integer.parseInt(savetimesheet.getThurs_hours())
		+ Integer.parseInt(savetimesheet.getSun_hours()) + Integer.parseInt(savetimesheet.getTue_hours())
		+ Integer.parseInt(savetimesheet.getWed_hours());
		savetimesheet.setTotalWeekHours(String.valueOf(cal));
		savetimesheet.setStatus("pending");
		EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
		int creat = this.saveTimeSheetRepo.findByCretedBy(savetimesheet.getEmp().getEmpId(), id);
		int modified = this.saveTimeSheetRepo.findmodiBy(savetimesheet.getEmp().getEmpId(), id);
		ClientDetailsEntity client = this.clientDetailsRepository.findById(savetimesheet.getClient().getId()).get();
		ProjectDetailsEntity proj = this.pojectDetailsRepository
				.findByProjectId(savetimesheet.getProj().getProjectId());
		TaskDetailsEntity task = this.taskDetailsRepository.findById(savetimesheet.getTask().getTaskid()).get();
		savetimesheet.setTask(task);
		savetimesheet.setProj(proj);
		savetimesheet.setClient(client);
		savetimesheet.setEmp(emp1);
		savetimesheet.setModifiedBy(creat + modified);
		savetimesheet.setModifiedDate(LocalDateTime.now());
		savetimesheet.setId(id);

		this.saveTimeSheetRepo.save(savetimesheet);

	}

	public List<SaveTimeSheet> getTimeSheetDetails(int month, int year, int calweek, int id) {

		return this.saveTimeSheetRepo.GetAllDetails(month, year, calweek, id);

		// return k;
	}

	public List<Object[]> getTimeSheetDetailsByMonth(int month, String empid, int year) {

		return this.saveTimeSheetRepo.getDetailsByMon(month, empid, year);

	}

	public CurrentWeekDatesResponse getWeekDates(LocalDate date) {
		HashMap<?, ?> DateHM = new CurrentWeekDates().getDateDetails(date);
		CurrentWeekDatesResponse currentWeekDatesResponse = new CurrentWeekDatesResponse();
		currentWeekDatesResponse.setCurrentWeek(DateHM.get("currentWeek").toString());
		currentWeekDatesResponse.setNextWeek(DateHM.get("nextWeek").toString());
		currentWeekDatesResponse.setPreviousWeek(DateHM.get("previousWeek").toString());
		currentWeekDatesResponse.setMonDate((String) DateHM.get("monday"));
		currentWeekDatesResponse.setTueDate((String) DateHM.get("tuesday"));
		currentWeekDatesResponse.setWedDate((String) DateHM.get("wednesday"));
		currentWeekDatesResponse.setThuDate((String) DateHM.get("thursday"));
		currentWeekDatesResponse.setFriDate((String) DateHM.get("friday"));
		currentWeekDatesResponse.setSatDate((String) DateHM.get("saturday"));
		currentWeekDatesResponse.setSunDate((String) DateHM.get("sunday"));
		Calendar calendar = new GregorianCalendar();
		Date date1 = new Date();
		calendar.setTime(date1);
		currentWeekDatesResponse.setCalweek(calendar.get(Calendar.WEEK_OF_YEAR));
		currentWeekDatesResponse.setYear(Integer.parseInt((String) DateHM.get("year")));
		currentWeekDatesResponse.setMonth(Integer.parseInt((String) DateHM.get("month")));
		currentWeekDatesResponse.setMonth(Integer.parseInt((String) DateHM.get("month")));
		currentWeekDatesResponse.setMonthlyWeek(calendar.get(Calendar.WEEK_OF_MONTH));
		return currentWeekDatesResponse;
	}

	public DateResponseTimeSheet dateResponse(int year, int calWeek) {
		log.info("entered into dateResponse Method of TimeSheetDetails Service..");
		DateResponseInTimeSheet dateRes = new Timesheet().getDateByYearAndCalWeek(year, calWeek);
		DateResponseTimeSheet res = new DateResponseTimeSheet();
		if (dateRes != null) {
			res.setStatus(true);
			res.setMessage("Date Retrived SuccessFul");
			res.setDateResp(dateRes);
		} else {
			res.setStatus(false);
			res.setMessage("Date Retrived UnSuccessFul");
		}
		return res;

	}

	public CommonTimeSheetbean getEmployeesWhoDidNotAccessTimeSheet(int year, int month) {
		CommonTimeSheetbean response = new CommonTimeSheetbean();
		List<TimeSheetsMonthlyBean> beanList = new ArrayList<>();
		Set<String> objList = new HashSet<>();
		//		List<String> empIdList = new ArrayList<>();
		Set<String> empTSet = new HashSet<>();
		try {
			List<String> empTimeList = saveTimeSheetRepo.getSaveTimeSheetempid(year, month);

			List<EmployeeDetails> empList = employeeRepository.findAll();
			for (EmployeeDetails emp : empList) {
				empTSet.add(emp.getEmpId());
			}

			for(String emp : empTSet) {
				boolean duplicate = false;
				for(String empT : empTimeList) {
					
					if(emp.equalsIgnoreCase(empT)) {
						duplicate = true;
						break;
					}
				}
			
				if(!duplicate) {
					if (year != 0 && month != 0) {
						objList.add(employeeRepository.getuserID(employeeRepository.findEmpIdId(emp)));
				}
					  else  if(year != 0 && month == 0) {
						objList.add(employeeRepository.getuserID(employeeRepository.findEmpIdId(emp)));
					}
				}
			}

			if (objList != null) {
				for (String obj : objList) {
					TimeSheetsMonthlyBean timeSheet = new TimeSheetsMonthlyBean();
					Integer i = Integer.parseInt(obj);
					timeSheet.setUserId(i);
					timeSheet.setEmployeeId(employeeRepository.getEmployeeId(i));// saveTimeSheetRepo.getTimeSheetempid(year, month,id)
					timeSheet.setEmployeeName(employeeRepository.findByFirstNameEmp(i));
					timeSheet.setMonTotalHours(0);
					timeSheet.setTueTotalHours(0);
					timeSheet.setWedTotalHours(0);
					timeSheet.setThursTotalHours(0);
					timeSheet.setFriTotalHours(0);
					timeSheet.setSatTotalHours(0);
					timeSheet.setSunTotalHours(0);
					timeSheet.setMonth(month);
					timeSheet.setYear(year);

					Integer repManId = employeeRepository.getByRepId(i);
					timeSheet.setReportingManagerName(employeeRepository.empName(repManId, i));
					beanList.add(timeSheet);

				}
			}




			if (beanList.size() > 0 && beanList != null) {
				response.setMessage("Employees Who didnot Access Timesheet Has been Fetched Successfully");
				response.setStatus(true);
				response.setList(beanList);
			} else {
				response.setMessage("Employee Details Who Didnot Access Timesheet hasnot been Fetched");
				response.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	public List<EmployeeDetails> getEmpByReportingId(Integer repId) {

		return this.employeeRepository.getDetailByRepId(repId);
	}
	
	
	
	public TimeSheetResponse getEmplTimeSheetDetailsByReportingManagerId(int repId, String status) {
		log.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");
		List<EmployeeDetails> empUserList =employeeRepository.getDetailByRepId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
//		if (empUserList != null && empUserList.size() != 0) {
//			for (EmployeeDetails empEntity : empUserList) {
//				ArrayList<SaveTimeSheet> listofMonthlyTSDetails = saveTimeSheetRepo
//						.getLeaveStatusByUserId(empEntity.getUserId(), status);
//				String employeeName = null;
//				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
//					tsEmp = new TSResponseEmployeeName();
//					employeeName = empEntity.getEmployeeName();
//					tsEmp.setEmployeeName(employeeName);
//					tsEmp.setStartDate(listofMonthlyTSDetails.get(0).getMon_date());
//					tsEmp.setEndDate(listofMonthlyTSDetails.get(0).getSun_Date());
//					tsEmp.setTotalHours(listofMonthlyTSDetails.get(0).getTotalWeekHours());
//					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
//					tsEmp.setStatus(status);
//					tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
//					listOfDetails.add(tsEmp);
//				}
//			}
//		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}
//		return Response.ok().entity(tsResp).build();
//	}

		return null;
	}
	public ProjectListResponse getProjectList() {
		ProjectListResponse response = new ProjectListResponse();
	 ProjectResponse listOfProjectResponse=new ProjectResponse();
	 List<Integer> id1=new ArrayList<>();
	 List<String> Pr=new ArrayList<>();
		List<ProjectDetailsEntity> ListProject =pojectDetailsRepository.findAll();
	    
	for (ProjectDetailsEntity emp : ListProject) {
			listOfProjectResponse.setProjectId(emp.getProjectId());
			listOfProjectResponse.setProjectName(emp.getProjectName());	
			}
//		 List<ProjectResponse> listOfProjectResponse=null;
		if (listOfProjectResponse != null) {
			response.setListOfProjectResponse(listOfProjectResponse);
			response.setStatus(true);
			response.setMessage("Project Retrived Successful");
			return response;
		} else {
			response.setStatus(false);
			response.setMessage("Project Retrived UnSuccessful");
			return response;
		}
	
	}
}

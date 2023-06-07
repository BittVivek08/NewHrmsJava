package com.hrms.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.entity.HolidayCalenderEntity;
import com.hrms.entity.RequestForLeave;
import com.hrms.repository.AttendanceRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.IRequestForLeaveRepository;
import com.hrms.service.EmployeeAttendanceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private HolidayCalenderRepository holidayRepo;

	@Autowired
	private EmployeeAttendancebean eab;

	@Autowired
	private IRequestForLeaveRepository leaveRepository;

	@Override
	public boolean checkIfCheckedInToday(String empId) {
		log.info("attendance check for today bussiness logic method");
		List<EmployeeAttendance> employeeAttendance = attendanceRepo.findByEmpId(empId);

		if (employeeAttendance.size() <= 0) {
			return false;
		} else {
			LocalDate checkInTime = employeeAttendance.get(employeeAttendance.size() - 1).getDate();
			if (checkInTime != null && checkInTime.isEqual(LocalDate.now())) {
				eab.setStatus(true);
				return true;
			}
		}
		return false;
	}

	@Override
	public EmployeeAttendancebean saveCheckInTime(String empId, String ipAddress, String workFrom) {
		log.info("attendance checkin businesslogic method");
		EmployeeAttendancebean attendancebean = new EmployeeAttendancebean();

		if (findHolidayDetails()) {
			if (findWeekends()) {
				if (getEmployeeOnLeaveToday(empId)) {
					if (!checkIfCheckedInToday(empId)) {
						EmployeeAttendance employeeAttendance = new EmployeeAttendance();
						employeeAttendance.setCheckInTime(LocalTime.now());
						employeeAttendance.setDate(LocalDate.now());
						employeeAttendance.setEmpId(empId);
						employeeAttendance.setIpAddress(ipAddress);
						employeeAttendance.setWorkFrom(workFrom);
						attendanceRepo.save(employeeAttendance);

						if (employeeAttendance != null) {
							employeeAttendance.setStatus("present");
							attendanceRepo.save(employeeAttendance);

							attendancebean.setMsg("Employee checked in successfully");
							attendancebean.setStatus(true);
						}
					} else {
						attendancebean.setMsg("Employee has already checked in today");
						attendancebean.setStatus(false);
					}
				} else {

					attendancebean.setMsg("Employee is on leave today");
					attendancebean.setStatus(false);
				}
			} else {
				attendancebean.setMsg("Attendance not allowed on weekends....!");
				attendancebean.setStatus(true);
			}
		} else {
			attendancebean.setMsg("Today is Holiday..!,Employee cannot Check-In");
			attendancebean.setStatus(true);
		}
		return attendancebean;
	}

	@Override
	public void saveCheckOutTime(String empId) {
		log.info("attendance checkout business logic method");
		List<EmployeeAttendance> employeeAttendanceList = attendanceRepo.findByEmpId(empId);

		if (!employeeAttendanceList.isEmpty()) {

			EmployeeAttendance employeeAttendance = employeeAttendanceList.get(employeeAttendanceList.size() - 1);
			employeeAttendance.setCheckOutTime(LocalTime.now());
			employeeAttendance.setDate(LocalDate.now());
			employeeAttendance.setWorkFrom(employeeAttendance.getWorkFrom());

			// Calculate the working hours
			LocalTime checkInTime = employeeAttendance.getCheckInTime();
			LocalTime checkOutTime = employeeAttendance.getCheckOutTime();

			Duration workingTime = Duration.between(checkInTime, checkOutTime);

			long hours = workingTime.toHours();
			long minutes = workingTime.toMinutes();
			long seconds = workingTime.getSeconds();

			String result = String.format("%02d:%02d:%02d", hours, minutes, seconds);

			employeeAttendance.setTotalWorkingHours(result);
			attendanceRepo.save(employeeAttendance);
		}
	}

	@Override
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(String empId, String startDate, String endDate) {
		log.info("get the attendance details weekly business logic method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			LocalDate date1 = sdf.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = sdf.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			return attendanceRepo.findByEmpIdAndDateBetween(empId, date1, date2);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean findHolidayDetails() {
		log.info("attendance check today holiday or not business logic method");
		List<HolidayCalenderEntity> holidayList = holidayRepo.findAll();
		LocalDate today = LocalDate.now();
		try {
			for (HolidayCalenderEntity holiday : holidayList) {

				LocalDate holidaydate = holiday.getDate();

				if (holidaydate.getDayOfMonth() == today.getDayOfMonth()
						&& holidaydate.getMonthValue() == today.getMonthValue()) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		return true;
	}

	@Override
	public boolean findWeekends() {
		log.info("attendance check days is weekdays or not business logic method");
		LocalDate today = LocalDate.now();
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {

			return false;

		} else {
			// code to mark attendance
			return true;
		}

	}

	@Override
	public List<HolidayCalenderEntity> findHolidaysByDateRange(String startDate, String endDate) {
		log.info("get holidays in a week checkout business logic method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			LocalDate date1 = sdf.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = sdf.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			return holidayRepo.findByDateBetween(date1, date2);

		} catch (Exception e) {

			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<String> getWeekendsBetweenDates(String startDateRequest, String endDateRequest) {
		log.info("get weekdays business logic method");
		List<String> weekends = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(startDateRequest, formatter);
		LocalDate endDate = LocalDate.parse(endDateRequest, formatter);

		while (startDate.isBefore(endDate)) {
			if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				weekends.add(startDate.getDayOfWeek().toString());
			}
			startDate = startDate.plus(1, ChronoUnit.DAYS);
		}
		return weekends;
	}

	@Override
	public List<RequestForLeave> getLeaveRecords(String startDateRequest, String endDateRequest) {
		log.info("employee leaves getting by weekly business logic method");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(startDateRequest, formatter);
		LocalDate endDate = LocalDate.parse(endDateRequest, formatter);
		return leaveRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
	}

	@Override
	public boolean getEmployeeOnLeaveToday(String empid) {
		log.info("employee is on leave no attendance business logic method");
		Optional<RequestForLeave> employee = leaveRepository.findByEmpid(empid);
		if (employee.isPresent()) {
			LocalDate today = LocalDate.now();
			LocalDate leaveStartDate = employee.get().getStartDate();
			LocalDate leaveEndDate = employee.get().getEndDate();
//            if (leaveStartDate != null && leaveEndDate != null) {
//                return today.isEqual(leaveStartDate) || (today.isAfter(leaveStartDate) && today.isBefore(leaveEndDate));
//            }
			if (today.isEqual(leaveStartDate) || (today.isAfter(leaveStartDate) && today.isBefore(leaveEndDate))) {
				return false;
			}
		}
		return true;

	}

	@Override
	public EmployeeAttendancebean saveCheckInTimeForcely(String empId, String ipAddress, String workFrom) {
		log.info("attendance checkin forcely business logic method");
		EmployeeAttendancebean attendancebean = new EmployeeAttendancebean();
		if (!checkIfCheckedInToday(empId)) {
			EmployeeAttendance employeeAttendance = new EmployeeAttendance();
			employeeAttendance.setCheckInTime(LocalTime.now());
			employeeAttendance.setDate(LocalDate.now());
			employeeAttendance.setEmpId(empId);
			employeeAttendance.setIpAddress(ipAddress);
			employeeAttendance.setWorkFrom(workFrom);
			attendanceRepo.save(employeeAttendance);

			if (employeeAttendance != null) {
				employeeAttendance.setStatus("present");
				attendanceRepo.save(employeeAttendance);

				attendancebean.setMsg("Employee checked in successfully");
				attendancebean.setStatus(true);
			}
		} else {
			attendancebean.setMsg("Employee has already checked in today");
			attendancebean.setStatus(false);
		}
		return attendancebean;
	}

	// @Override
	// public EmployeeAttendancebean saveAttendanceDetails(EmployeeAttendance
	// employeeattend) {
	//
	// EmployeeAttendance ss = attendanceRepo.save(employeeattend);
	// if(ss !=null ) {
	// eab.setMsg("Employee attendance details saved Successfully");
	// eab.setStatus(true);
	// }else {
	// eab.setMsg("failed !");
	// eab.setStatus(false);
	// }
	// return eab;
	// }

	// @Override
	// public EmployeeAttendance findByEmpId(int empId) {
	//
	// Optional<EmployeeAttendance> empatt = attendanceRepo.findById(empId);
	//
	// if(empatt.isPresent()) {
	// return empatt.get();
	// }
	// else {
	//
	// eab.setStatus(false);
	// System.out.println("invalid details");
	// }
	//
	// return null ;
	// }

}

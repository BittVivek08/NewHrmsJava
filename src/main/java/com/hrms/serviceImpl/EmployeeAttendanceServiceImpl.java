package com.hrms.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.entity.HolidayCalendarEntity;
import com.hrms.repository.AttendanceRepository;
import com.hrms.repository.HolidayRepository;
import com.hrms.service.EmployeeAttendanceService;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private HolidayRepository holidayRepo;

	@Autowired
	private EmployeeAttendancebean eab;

	@Override
	public boolean checkIfCheckedInToday(String empId) {

		List<EmployeeAttendance> employeeAttendance = attendanceRepo.findByEmpId(empId);

		if ( employeeAttendance.size() <= 0 ) {
			return false;
		}
		else {
			LocalDate checkInTime = employeeAttendance.get(employeeAttendance.size()-1).getDate();
			if (checkInTime != null && checkInTime.isEqual(LocalDate.now())) {
				eab.setStatus(true);
				return true;
			}
		}
		return false;
	}

	@Override
<<<<<<< HEAD
	public void saveCheckInTime(String empId, String ipAddress, String workFrom) {

		
		EmployeeAttendancebean attendancebean= new EmployeeAttendancebean();
		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		//		EmployeeAttendance employeeAttendance = attendanceRepo.findById(empId).orElse(null);
		employeeAttendance.setCheckInTime(LocalTime.now());
		employeeAttendance.setDate(LocalDate.now());
		employeeAttendance.setEmpId(empId);
		employeeAttendance.setIpAddress(ipAddress);
		employeeAttendance.setWorkFrom(workFrom);
		attendanceRepo.save(employeeAttendance);
=======
	public EmployeeAttendancebean saveCheckInTime(String empId, String ipAddress, String workFrom) {

		EmployeeAttendancebean attendancebean= new EmployeeAttendancebean();
>>>>>>> 7900334bed9587264593b285fceed763962e3224

		if(findHolidayDetails()) {
			if(findWeekends()) {
			if(!checkIfCheckedInToday(empId)) {
				EmployeeAttendance employeeAttendance = new EmployeeAttendance();
				employeeAttendance.setCheckInTime(LocalTime.now());
				employeeAttendance.setDate(LocalDate.now());
				employeeAttendance.setEmpId(empId);
				employeeAttendance.setIpAddress(ipAddress);
				employeeAttendance.setWorkFrom(workFrom);
				attendanceRepo.save(employeeAttendance);

				if(employeeAttendance != null) {
					employeeAttendance.setStatus("present");
					attendanceRepo.save(employeeAttendance);

					attendancebean.setMsg("Employee checked in successfully");
					attendancebean.setStatus(true);
				}
			}
			else {
				attendancebean.setMsg("Employee has already checked in today");
				attendancebean.setStatus(false);
			}
			}else {
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

		List<EmployeeAttendance> employeeAttendanceList = attendanceRepo.findByEmpId(empId);

		EmployeeAttendance employeeAttendance = employeeAttendanceList.get(employeeAttendanceList.size()-1);
		employeeAttendance.setCheckOutTime(LocalTime.now());
		employeeAttendance.setDate(LocalDate.now());
		employeeAttendance.setWorkFrom(employeeAttendance.getWorkFrom());
		attendanceRepo.save(employeeAttendance);

	}

	@Override
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(String empId, String startDate, String endDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			LocalDate date1 = sdf.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = sdf.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			return attendanceRepo.findByEmpIdAndDateBetween(empId, date1, date2);
		}
		catch(Exception e)
		{
			return null;	
		}
	}

	@Override
	public boolean findHolidayDetails() {

		List<HolidayCalendarEntity> holidayList =  holidayRepo.findAll();
		LocalDate today = LocalDate.now();
		try {
			for (HolidayCalendarEntity holiday : holidayList) {

				LocalDate holidaydate = holiday.getDate2();

				if (holidaydate.getDayOfMonth() == today.getDayOfMonth()
						&& holidaydate.getMonthValue() == today.getMonthValue()) {
					return false;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return true;
		}

		return true;
	}

	@Override
	public boolean findWeekends() {
		LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
        	
        	return false;
        	
        } else {
            // code to mark attendance
        	return true;
        }
		
	}

	

	//	@Override
	//	public HolidayCalendarEntity getHolidays (LocalDate date)
	//	{
	//		LocalDate currentDate = LocalDate.now();
	//		HolidayCalendarEntity holidays = holidayRepo.findAllByDate(currentDate);
	//		return null;
	//		
	//
	////		EmployeeAttendance findByCalenderEntity = attendanceRepo.findByCalenderEntity(date);
	////		
	////		if(findByCalenderEntity.getCalenderEntity().getDate2()==LocalDate.now())
	////		{
	////			System.out.println("today is holiday ");
	////		}
	////		
	//		
	//	}
	//	
	//	@Override
	//	public HolidayCalendarEntity getHolidays(LocalDate date) {
	//		
	//	    List<HolidayCalendarEntity> holidays = holidayRepo.findAllByDate(date);
	//	    return holidays.isEmpty() ? null : holidays.get(0);
	//
	//	}









	//	@Override
	//	public EmployeeAttendancebean saveAttendanceDetails(EmployeeAttendance employeeattend) {
	//
	//		EmployeeAttendance ss = attendanceRepo.save(employeeattend);
	//		if(ss !=null ) {
	//			eab.setMsg("Employee attendance details  saved Successfully");
	//			eab.setStatus(true);
	//		}else {
	//			eab.setMsg("failed !");
	//			eab.setStatus(false);
	//		}
	//		return eab;
	//	}

	//	@Override
	//	public EmployeeAttendance findByEmpId(int empId) {
	//
	//		Optional<EmployeeAttendance> empatt = attendanceRepo.findById(empId);
	//
	//		if(empatt.isPresent()) {
	//			return empatt.get();
	//		}
	//		else {
	//
	//			eab.setStatus(false);
	//			System.out.println("invalid details");
	//		}
	//
	//		return null ;
	//	}

}



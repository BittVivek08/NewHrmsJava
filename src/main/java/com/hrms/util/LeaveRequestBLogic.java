package com.hrms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.repository.EmployeeLeaveDetailsRepository;


@Component
public class LeaveRequestBLogic {
	
     @Autowired
	private EmployeeLeaveDetailsRepository leaveDetailsRepo;
	
	
	public void updateEmployeeLeaves(String leaveType, String emp_id, float days, String operation, String leaveStatus) {
		try {
			if (operation.equalsIgnoreCase("save")) {
				if (leaveType.equalsIgnoreCase("Casual")) {
           leaveDetailsRepo.updateUsedCasualLeavesByUserIdAndCurrentYear( days, emp_id);
				}
				else if (leaveType.equalsIgnoreCase("Sick")) {
					leaveDetailsRepo.updateUsedSickLeavesByUserIdAndCurrentYear(days, emp_id);
				}
			} else if (operation.equalsIgnoreCase("update") && !leaveStatus.equalsIgnoreCase("Approved")) {
				if (leaveType.equalsIgnoreCase("Casual Leave"))
					leaveDetailsRepo.updateUsedCasualLeaves(days, emp_id);
				else if (leaveType.equalsIgnoreCase("Sick Leave"))
					leaveDetailsRepo.updateUsedSickLeaves(days, emp_id);
			}
			
			else {
				System.out.println("failed to apply");
			}
		} catch (Exception e) {
			e.printStackTrace();
		
	}

	}
}

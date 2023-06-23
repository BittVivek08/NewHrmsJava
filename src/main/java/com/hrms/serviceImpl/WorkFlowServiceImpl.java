package com.hrms.serviceImpl;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.entity.WorkFlow;
import com.hrms.repository.EmployeeLeaveRequestSummaryRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.SaveTimeSheetRepo;
import com.hrms.repository.WorkFlowMgntRepository;
import com.hrms.repository.WorkFlowRepository;
import com.hrms.request.bean.WorkFlowUpdateReqBean;
import com.hrms.response.bean.leaveReuestUpdateResponseBean;
import com.hrms.service.WorkFlowService;
import com.hrms.util.LeaveRequestBLogic;




@Service
public class WorkFlowServiceImpl implements WorkFlowService{

	@Autowired
	private EmployeeLeaveRequestSummaryRepository leaveReqSummery;
	
	@Autowired
	private WorkFlowRepository workFlowRepo;
	
	@Autowired
	private WorkFlowMgntRepository workFlowMgntRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private LeaveRequestBLogic leaveBlogic;
	
	@Autowired
	private SaveTimeSheetRepo timeSheetRepo;
	
	
	@Override
	public List<WorkFlow> getAllRequests() {
		
		return workFlowRepo.findAll();
		
	}


	@Override
	public List<WorkFlow> getData(String  empid, String rmId, String status) {
		
		
	  if (empid !=null && rmId !=null && status !=null) {
			return workFlowRepo.findByEmpidAndApprovalManagerIdAndStatus(empid,rmId,status);

     }
	 else if (empid != null && rmId ==null && status ==null ) {
	            return workFlowRepo.findByEmpid(empid);
	            
	        }else if (empid==null  && rmId != null && status != null ) {
	            return workFlowRepo.findByApprovalManagerIdAndStatus(rmId, status);
	            
	        } else if (empid==null  && rmId != null && status != null) {
	            return workFlowRepo.findByStatus(status);
	            
	        }else if (empid !=null && rmId == null && status != null) {
				return workFlowRepo.findByEmpidAndStatus(empid ,status);
				
			} else {
	            return workFlowRepo.findAll();
	        }
		
	}

	@Override
	public leaveReuestUpdateResponseBean updateReqStatus(WorkFlowUpdateReqBean workreq) {
		
		leaveReuestUpdateResponseBean rs = new leaveReuestUpdateResponseBean();
		Instant timestamp= Instant.now();
		int count= workFlowRepo.getApprovelCount(workreq.getReqId())+1;
		int mLevel= workFlowMgntRepo.getManagerLeavel(workreq.getFeature());
		int countp= workFlowRepo . getApprovelCountforparallel(workreq.getReqId());
		
		EmployeeDetails empDetails = employeeRepo.findByEmpId(workreq.getApprovalManagerId());
		
		WorkFlow wFlow=this.workFlowRepo.findByEmpidAndReqidAndFeatureAndApprovalManagerId(
				workreq.getEmpid(),workreq.getReqId(), workreq.getFeature(), workreq.getApprovalManagerId());
		
		//01 
		if (wFlow==null) {
			rs.setMessage("request not found");
			rs.setStatus(false);
		} //01
		
		//02
		else	if (wFlow.getStatus().equals("pending") && wFlow.getApprovalManagerId().equalsIgnoreCase(workreq.getApprovalManagerId()) 
				&& workFlowMgntRepo.getType(workreq.getFeature()).equalsIgnoreCase("hierarchical")) {  // 01
			
			wFlow.setStatus(workreq.getStatus());
			wFlow.setApproverComment(workreq.getComment());
			wFlow.setModifiedDate(timestamp);
			wFlow.setModifiedBy(wFlow.getApprovalManagerId());
			
			workFlowRepo.save(wFlow);
			rs.setMessage("updated");
			rs.setStatus(true);
			
			//nestedIf
			if(workreq.getStatus().equalsIgnoreCase("approved") && employeeRepo.getReportingManagerId(workreq.getApprovalManagerId())!= null
						){
			 WorkFlow workFlow = new WorkFlow();
				
				workFlow=new WorkFlow();
				workFlow.setEmpid(workreq.getEmpid());
				workFlow.setFeature(workreq.getFeature());
				workFlow.setStatus("pending");
				workFlow.setApprovalManagerId(employeeRepo.getReportingManagerId(workreq.getApprovalManagerId()));
				workFlow.setCreatedDate(timestamp);
				workFlow.setCreatedBy(workreq.getEmpid());
				workFlow.setReqid(wFlow.getReqid());
				
				  if(count != mLevel && workreq.getFeature().equalsIgnoreCase("leave")) {
				leaveBlogic.workFlowInsetion(workFlow,"leave",false);
				  }
				  else if(count == mLevel && workreq.getFeature().equalsIgnoreCase("leave")) {
					  EmployeeLeaveRequestSummaryEntity summery =	leaveReqSummery.findById(workreq.getReqId());
						summery.setLeaveStatus("Approved");
						leaveReqSummery.save(summery);
				}
				  else if(count != mLevel && workreq.getFeature().equalsIgnoreCase("timesheet")) {
					  leaveBlogic.workFlowInsetion(workFlow,"timesheet",false);
				  }
				  else{
					  SaveTimeSheet sheet =	timeSheetRepo.findById(workreq.getReqId());
						sheet.setStatus("approved");
					     timeSheetRepo.save(sheet);
				  }
			}
			
			// i have to write here
			else if(workreq.getStatus().equalsIgnoreCase("approved") && employeeRepo.getReportingManagerId(workreq.getApprovalManagerId())!= null
					&& workreq.getFeature().equalsIgnoreCase("timesheet")){
			 WorkFlow workFlow = new WorkFlow();
				
				workFlow=new WorkFlow();
				workFlow.setEmpid(workreq.getEmpid());
				workFlow.setFeature(workreq.getFeature());
				workFlow.setStatus("pending");
				workFlow.setApprovalManagerId(empDetails.getReportingManagerId());
				workFlow.setCreatedDate(timestamp);
				workFlow.setCreatedBy(workreq.getEmpid());
				workFlow.setReqid(wFlow.getReqid());
				leaveBlogic.workFlowInsetion(workFlow,"timesheet",false);
			}
			
			else if(workreq.getStatus().equalsIgnoreCase("rejected") && workreq.getFeature().equalsIgnoreCase("leave")) {
			EmployeeLeaveRequestSummaryEntity summery =	leaveReqSummery.findById(workreq.getReqId());
			summery.setLeaveStatus("rejected");
			leaveReqSummery.save(summery);
			
			}
			
			else if(workreq.getStatus().equalsIgnoreCase("rejected") && workreq.getFeature().equalsIgnoreCase("timesheet")) {
				SaveTimeSheet sheet =	timeSheetRepo.findById(workreq.getReqId());
				sheet.setStatus("rejected");
			     timeSheetRepo.save(sheet);
			     
			}
			else if(employeeRepo.getReportingManagerId(workreq.getApprovalManagerId())== null && workreq.getFeature().equalsIgnoreCase("leave")
					&&  workreq.getStatus().equalsIgnoreCase("approved")) {
				EmployeeLeaveRequestSummaryEntity summery =	leaveReqSummery.findById(workreq.getReqId());
				summery.setLeaveStatus("Approved");
				leaveReqSummery.save(summery);
			}
			else {
				SaveTimeSheet sheet =	timeSheetRepo.findById(workreq.getReqId());
				sheet.setStatus("approved");
			     timeSheetRepo.save(sheet);
			}
			
		} //02
		//03
		else if (wFlow.getStatus().equals("pending") && wFlow.getApprovalManagerId().equalsIgnoreCase(workreq.getApprovalManagerId()) 
				&& workFlowMgntRepo.getType(workreq.getFeature()).equalsIgnoreCase("parallel")) {  // 01
			
			wFlow.setStatus(workreq.getStatus());
			wFlow.setApproverComment(workreq.getComment());
			wFlow.setModifiedDate(timestamp);
			wFlow.setModifiedBy(wFlow.getApprovalManagerId());
			
			workFlowRepo.save(wFlow);
			rs.setMessage("updated");
			rs.setStatus(true);
			
			if(workreq.getStatus().equalsIgnoreCase("approved")) {
			if(count==countp && workreq.getFeature().equalsIgnoreCase("leave")) {
				 EmployeeLeaveRequestSummaryEntity summery =	leaveReqSummery.findById(workreq.getReqId());
					summery.setLeaveStatus(workreq.getStatus());
					leaveReqSummery.save(summery);
			}
			else if(count==countp && workreq.getFeature().equalsIgnoreCase("timesheet")) {
				SaveTimeSheet sheet =	timeSheetRepo.findById(workreq.getReqId());
				sheet.setStatus(workreq.getStatus());
			     timeSheetRepo.save(sheet);
			}
			
			else {
				System.out.println("nothing");
			}
			}
			else {
				if(workreq.getFeature().equalsIgnoreCase("leave")){
					
					EmployeeLeaveRequestSummaryEntity summery =	leaveReqSummery.findById(workreq.getReqId());
					summery.setLeaveStatus(workreq.getStatus());
					leaveReqSummery.save(summery);
				}
				else {
					SaveTimeSheet sheet =	timeSheetRepo.findById(workreq.getReqId());
					sheet.setStatus(workreq.getStatus());
				     timeSheetRepo.save(sheet);
				}
			}
			
		}//03
		
		//04
		else if (wFlow.getStatus().equals("Pending")){
			wFlow.setStatus(workreq.getStatus());
			workFlowRepo.save(wFlow);
			SaveTimeSheet sheet =	timeSheetRepo.findById(workreq.getReqId());
			sheet.setStatus(workreq.getStatus());
		     timeSheetRepo.save(sheet);
		     rs.setMessage("Updated successfully");
				rs.setStatus(true);
		}//04
		
		else if(wFlow.getStatus().equalsIgnoreCase("approved")) {
			rs.setMessage("Already updated");
			rs.setStatus(false);
		}
		else {
			rs.setMessage("Not Able to Update");
			rs.setStatus(false);
		}
		
		
		return rs;
	}

	
}

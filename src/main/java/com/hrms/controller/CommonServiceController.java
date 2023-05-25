package com.hrms.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.CommonResponseBean;

import com.hrms.entity.GenderEntity;
import com.hrms.entity.JobTitlesEntity;
import com.hrms.entity.LanguageEntity;
import com.hrms.entity.MaritalStatusEntity;
import com.hrms.entity.NationalityEntity;

import com.hrms.entity.EmpRole;

import com.hrms.entity.SalaryAccountClassTypeEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.request.bean.JobTitleBean;
import com.hrms.request.bean.PersonalGenderBean;
import com.hrms.request.bean.PersonalMaritalStatusBean;
import com.hrms.request.bean.PersonalNationalityBean;
import com.hrms.request.bean.SalaryAccountClassTypeRequestBean;
import com.hrms.request.bean.SalaryCurrencyRequestBean;
import com.hrms.request.bean.personalLanguageBean;
import com.hrms.response.bean.ListOfPositionsResponseBean;
import com.hrms.service.EmployeeDetailsService;
import com.hrms.service.HrmsCommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/common")
public class CommonServiceController {

	@Autowired
	private HrmsCommonService hrmsCommomService;

	@PostMapping("/addCurrency")
	public CommonResponseBean saveSalaryCurrency(@RequestBody SalaryCurrencyRequestBean salaryCurrencyReqBean) {
		log.info("Saving Salary Currency Details");
		return hrmsCommomService.saveSalaryCurrency(salaryCurrencyReqBean);
	}

	@GetMapping("/getAllCurrency")
	public ResponseEntity<List<SalaryCurrencyEntity>> getAllCurrencyDetails() {
		log.info("Fetching all Currency Details");
		List<SalaryCurrencyEntity> allSalaryCurrency = hrmsCommomService.getAllSalaryCurrency();
		return new ResponseEntity<>(allSalaryCurrency, HttpStatus.OK);
	}

	@PostMapping("/addAccountType")
	public CommonResponseBean saveSalaryAccountType(
			@RequestBody SalaryAccountClassTypeRequestBean salaryAccountTypeReqBean) {
		log.info("Saving salary Account Type Method");
		return hrmsCommomService.saveSalaryAccountType(salaryAccountTypeReqBean);
	}

	@GetMapping("/getAllAccountType")
	public ResponseEntity<List<SalaryAccountClassTypeEntity>> getAllAccountType() {
		log.info("Fetching All Account Type");
		List<SalaryAccountClassTypeEntity> allAccountType = hrmsCommomService.getAllAccountType();
		return new ResponseEntity<>(allAccountType, HttpStatus.OK);
	}

	@PostMapping("/saveJobtitle")
	public CommonResponseBean saveJobTitle(@RequestBody JobTitleBean bean) {
		log.info("saving jobTitle api");
		return hrmsCommomService.saveJobTitle(bean);

	}

	@GetMapping("/getjobtitle/{id}")
	public Optional<JobTitlesEntity> getJobTitleById(@PathVariable int id) {
		log.info("get by id jobTitle api");
		return hrmsCommomService.getById(id);
	}

	@GetMapping("/getallJobTitle")
	public List<JobTitlesEntity> getAllJobTitles() {
		log.info("get all jobTitle api");
		return hrmsCommomService.getAllDetails();
	}

	@DeleteMapping("/deletebyid/{id}")
	public CommonResponseBean deleteById(@PathVariable int id) {
		log.info("delete by id jobTitle api");
		return hrmsCommomService.deleteById(id);
	}

	@PutMapping("/updatebyid/{id}")
	public CommonResponseBean updatedById(@PathVariable int id, @RequestBody JobTitleBean bean) {
		log.info("updated by id jobTitle api");
		return hrmsCommomService.updateByid(id, bean);
	}

	@PostMapping("/savegender")
	public CommonResponseBean saveGender(@RequestBody PersonalGenderBean genderBean) {
		log.info("save gender api");
		return hrmsCommomService.saveGenderDetails(genderBean);
	}

	@GetMapping("/getdetail/{id}")
	public Optional<GenderEntity> getByGenderId(@PathVariable int id) {
		log.info("get by id gender api");
		return hrmsCommomService.getByGenderId(id);
	}

	@GetMapping("/getallGenders")
	public List<GenderEntity> getAllGenders() {
		log.info("get all gender api");
		return hrmsCommomService.getAllGenders();
	}

	@DeleteMapping("/deletedetail/{id}")
	public CommonResponseBean deleteByGenderId(@PathVariable int id) {
		log.info("delete by id gender api");
		return hrmsCommomService.deleteByGenderId(id);
	}

	@PutMapping("/updatedetails/{id}")
	public CommonResponseBean updateByGenderId(@PathVariable int id, @RequestBody PersonalGenderBean genderBean) {
		log.info("updated by id gender api");
		return hrmsCommomService.updateByGenderId(id, genderBean);
	}

	@PostMapping("/savemaritalstatus")
	public CommonResponseBean saveMarital(@RequestBody PersonalMaritalStatusBean maritalBean) {
		log.info("save maritalstatus api");
		return hrmsCommomService.saveMaritialDetails(maritalBean);
	}

	@GetMapping("/getmaritaldetail/{id}")
	public Optional<MaritalStatusEntity> getByMaritalId(@PathVariable int id) {
		log.info("get by id maritalstatus api");
		return hrmsCommomService.getByMaritalId(id);
	}

	@GetMapping("/getallmaritalstatus")
	public List<MaritalStatusEntity> getAllMaritalStus() {
		log.info("get all maritalstatus api");
		return hrmsCommomService.getAllMaritalStatus();
	}

	@DeleteMapping("/deletemaritaldetail/{id}")
	public CommonResponseBean deleteByMaritalId(@PathVariable int id) {
		log.info("delete by id maritalstatus api");
		return hrmsCommomService.deleteByMaritalId(id);
	}

	@PutMapping("/updatemaritaldetails/{id}")
	public CommonResponseBean updateByMaritalId(@PathVariable int id,
			@RequestBody PersonalMaritalStatusBean maritalBean) {
		log.info("updated by id maritalstatus api");
		return hrmsCommomService.updateByMaritalId(id, maritalBean);
	}

	@PostMapping("/savenationality")
	public CommonResponseBean saveNationality(@RequestBody PersonalNationalityBean bean) {
		log.info("saving Nationality api");
		return hrmsCommomService.saveNationality(bean);

	}

	@GetMapping("/getnationality/{id}")
	public Optional<NationalityEntity> getNationality(@PathVariable int id) {
		log.info("get by id nationality api");
		return hrmsCommomService.getByNationalityId(id);
	}

	@GetMapping("/getallnationality")
	public List<NationalityEntity> getAllNationality() {
		log.info("get all nationality api");
		return hrmsCommomService.getAllNationality();
	}

	@DeleteMapping("/deletenationality/{id}")
	public CommonResponseBean deleteNationality(@PathVariable int id) {
		log.info("delete by id naionality api");
		return hrmsCommomService.deleteByNatioalityId(id);
	}

	@PutMapping("/updatenationality/{id}")
	public CommonResponseBean updatedNationality(@PathVariable int id, @RequestBody PersonalNationalityBean bean) {
		log.info("updated by id naionality api");
		return hrmsCommomService.updateByNationaliyId(id, bean);
	}

	@GetMapping("/getAllEmpRole")
	public ResponseEntity<List<EmpRole>> getAllEmpRole() {
		log.info("Fetching All Emp Role");
		List<EmpRole> allEmpRole = hrmsCommomService.getAllEmpRole();
		return new ResponseEntity<>(allEmpRole, HttpStatus.OK);
	}

	@GetMapping("/getEmpRoleById/{id}")
	public ResponseEntity<EmpRole> getEmpRoleById(@PathVariable Integer id) {
		log.info("Fetching Emp Role By Id");
		EmpRole empRoleById = hrmsCommomService.getEmpRoleById(id);
		return new ResponseEntity<>(empRoleById, HttpStatus.OK);
	}

	@PostMapping("/savelanguage")
	public CommonResponseBean saveLanguage(@RequestBody personalLanguageBean bean) {
		log.info("saving Language api");
		return hrmsCommomService.saveLanguageDetails(bean);

	}

	@GetMapping("/getlanguage/{id}")
	public Optional<LanguageEntity> getlanguage(@PathVariable int id) {
		log.info("get by Language id api");
		return hrmsCommomService.getBylanguageId(id);
	}

	@GetMapping("/getalllanguage")
	public List<LanguageEntity> getAlllanguages() {
		log.info("get all Language api");
		return hrmsCommomService.getAlllanguages();
	}

	@DeleteMapping("/deletelanguage/{id}")
	public CommonResponseBean deletelaguage(@PathVariable int id) {
		log.info("delete by Language id api");
		return hrmsCommomService.deleteBylanguageId(id);
	}

	@PutMapping("/updatelanguage/{id}")
	public CommonResponseBean updatedlanguage(@PathVariable int id, @RequestBody personalLanguageBean bean) {
		log.info("updated by Language id api");
		return hrmsCommomService.updateBylanguageId(id, bean);
	}

	@GetMapping("/HrManagerList")
	public CommonResponseBean hrManagerList(@QueryParam(value = "businessunitId") int businessunitId) {
		log.info("Entered into hrManagerList()");
		return hrmsCommomService.fetchHRmanager(businessunitId);
	}

	@GetMapping("/IMMManagerList")
	public CommonResponseBean superImmManagerList(@QueryParam(value = "businessunitId") int businessunitId) {
		log.info("Entered into superImmManagerList()");
		return hrmsCommomService.fetchIMMmanager(businessunitId);
	}

	@GetMapping("/AllReportingManagerList")
	public CommonResponseBean allReportingManagerList(@QueryParam(value = "empRoleId") int empRoleId,
			@QueryParam(value = "businessunitId") int businessunitId,
			@QueryParam(value = "departmentId") int departmentId) {
		log.info("Entered into allReportingManagerList()");
		return hrmsCommomService.fetchReportingManagerList(empRoleId, businessunitId, departmentId);
	}

	@GetMapping("/getPositionList")
	public ListOfPositionsResponseBean getPositionsList(@QueryParam(value = "jobTitleId") int jobTitleId) {
		log.info("entered into getPositionsList service class method..");
		return hrmsCommomService.listOfPositions(jobTitleId);
	}

	@GetMapping("/getReportingManagerList")
	public CommonResponseBean fetchReportManagerBasedOnRoleIdAndDu(@QueryParam(value = "empRoleId") int empRoleId,
			@QueryParam(value = "departmentId") int departmentId) {
		log.info("Entered into fetching report manager based on roleid and departmrntid");
		return hrmsCommomService.fetchReporingListOnEmpRoleIdDu(empRoleId, departmentId);
	}
}

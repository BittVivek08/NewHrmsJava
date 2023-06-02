package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.ClientsResponseBean;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.service.ClientDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Client")
@Slf4j
public class CilentDeatilsController {
	
	@Autowired
	private ClientDetailsService ClientService;
	
	 //oldHrms
	//SaveClient
	@PostMapping("/saveClient")
	public EntityBeanResponseCommon saveClientDetails(@RequestBody ClientDetailsEntity entity) {
		this.log.info("Entered save client details method in controlller");
		
		EntityBeanResponseCommon saveClientDetails = this.ClientService.saveClientDetails(entity);
		this.log.info("successfully savee client details method in controlller");
		
		return saveClientDetails;
		
	}
	
	 //oldHrms
	//GetAllClients
	@GetMapping("/GetAllClients")
	public ResponseEntity<ClientsResponseBean> getallClients(){
		this.log.info("Entered fetch all  client details method in controlller");
		
		ClientsResponseBean allClients = this.ClientService.getAllClients();
		
		this.log.info("successfully fetch all  client details method in controlller");
		return new ResponseEntity<ClientsResponseBean>(allClients,HttpStatus.OK);
		
		
		
	}
	
	//oldHrms
	//GetSingleClientByClientId
	
	@GetMapping("/GetSingleClientByClientId/{clientId}")
	public ResponseEntity<ClientDetailsEntity> getSingleClientByClientId(@PathVariable("clientId") int clientId) {
		this.log.info("Entered fetch single   client details method in controlller");
		
		ClientDetailsEntity clientByClientId = this.ClientService.getClientByClientId(clientId);
		
		this.log.info("successfully fetch single  client details method in controlller");
		return new ResponseEntity<ClientDetailsEntity>(clientByClientId,HttpStatus.OK);
			
	}
	
	
	
	 //oldHrms
	//delete
	@DeleteMapping("/deleteClient/{clientid}")
	public EntityBeanResponseCommon deleteClient(@PathVariable("clientid") int id) {
		
		this.log.info("Entered delete single client method in controlller");
		
		EntityBeanResponseCommon deletedClient = this.ClientService.deletedClient(id);
		
		this.log.info("successfully  delete single client method in controlller");
		
		
		return deletedClient;
		
		
	}
	
	 //oldHrms
	//updateById
	@PutMapping("/putClient/{cid}")
	public ClientDetailsEntity updateCliDetails(@PathVariable("cid") int id,@RequestBody ClientDetailsEntity entity) {
		
		this.log.info("Entered update client method in controlller");
		
		ClientDetailsEntity updateClientById = this.ClientService.updateClientById(id, entity);
		
		this.log.info("successfully  update client method in controlller");
		
		
		return updateClientById;
		
	}
	
}

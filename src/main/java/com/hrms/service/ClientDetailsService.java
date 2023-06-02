package com.hrms.service;

import org.springframework.http.ResponseEntity;

import com.hrms.beans.ClientsResponseBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.ClientDetailsEntity;

public interface ClientDetailsService {
	
	 //oldHrms
	public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity);
	
	 //oldHrms
	public ClientsResponseBean getAllClients();
		
	 //oldHrms
	public ClientDetailsEntity getClientByClientId(int clientId);
	
	 //oldHrms
	public 	EntityBeanResponseCommon deletedClient(int id);
	
	 //oldHrms
	public ClientDetailsEntity updateClientById(int cliId,ClientDetailsEntity entity);
	

}

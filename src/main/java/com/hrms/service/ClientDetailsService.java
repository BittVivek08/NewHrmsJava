package com.hrms.service;

import org.springframework.http.ResponseEntity;

import com.hrms.beans.ClientsResponseBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.ClientDetailsEntity;

public interface ClientDetailsService {
	
	
	public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity);
	
	public ClientsResponseBean getAllClients();
	
	//public ClientDetailsEntity getSingleClientrByid(int clientId);
	
	
	public ClientDetailsEntity getClientByClientId(int clientId);
	
	public EntityBeanResponseCommon updateClientDetails(ClientDetailsEntity entity);
	
	public 	EntityBeanResponseCommon deletedClient(int id);
	
	
	public ClientDetailsEntity updateClientById(int cliId,ClientDetailsEntity entity);
	
	
	
	
	
	//public ClientDetailsEntity updateClientDetails(int clientId,ClientDetailsEntity enityt);
	
	
	
	//public ClientDetailsEntity updateClientDetrails(int  id,ClientDetailsEntity entity);
	//public EntityBeanResponse updateClient(ClientDetailsEntity updateEntity);
	
	

}

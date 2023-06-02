package com.hrms.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.ClientsResponseBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.repository.ClientDetailsRepository;
import com.hrms.service.ClientDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDetailsRepository clientRepo;

	@Autowired
	private EntityBeanResponseCommon bean;

	@Autowired
	private ClientsResponseBean clientBean;
	
	 //oldHrms
	// saveClient
	@Override
	public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity) {
		this.log.info("Entered save client details method in service");
		
		entity.setCreatedBy(0);
		entity.setCreatedDate(LocalDateTime.now());
		entity.setIs_active((byte) 1);
		entity.setModifiedBy(1);
		entity.setModifiedDate(LocalDateTime.now());
		ClientDetailsEntity clientDeatils = this.clientRepo.save(entity);
		this.log.info("successfully save client details method in service");
		

		if (clientDeatils != null) {

			bean.setMsg("Successfully Client  Details Saved");
			bean.setStatus(true);

		} else {

			bean.setMsg("Client Details not saved");
			bean.setStatus(false);
			this.log.info("failed to  save client details method in service");
		}

		return bean;
	}
	
	
	 //oldHrms	
//    	
//    //getAllClients
//	@Override
	public ClientsResponseBean getAllClients() {
		
		this.log.info("Entered fetch alll  client details method in service");

		List<ClientDetailsEntity> findAll = this.clientRepo.findAll();
		if (!findAll.isEmpty()) {
			clientBean.setMessage("clients details fetched Successfully");
			clientBean.setStatus(true);
			clientBean.setClientslist(findAll);
			this.log.info("successfully fetched alll  client details method in service");
		} else {

			clientBean.setMessage("Failed Retrive Clients Details");
			clientBean.setStatus(false);
			this.log.info("failed fetch alll  client details method in service");
		}

		return clientBean;
	}


// //oldHrms
	
//	 //GetSingleClientByClientID
//	@Override
	public ClientDetailsEntity getClientByClientId(int clientId) {
		this.log.info("Entered  fetch   client details by client id method in service");
		
		//ClientDetailsEntity findByClientid = this.clientRepo.findByClientid(clientId);
		
		Optional<ClientDetailsEntity> client = this.clientRepo.findById(clientId);
		
		this.log.info("successfully  fetched   client details by client id method in service");
		
		
		return client.get();
	}


	 //oldHrms
//	@Override
	public EntityBeanResponseCommon deletedClient(int id) {
		
		this.log.info("Entered delete client by  client id method in service");
		
		//ClientDetailsEntity findByClientid = this.clientRepo.findByClientid(id);
		
		Optional<ClientDetailsEntity> client = this.clientRepo.findById(id);
		
		this.clientRepo.delete(client.get());
		this.log.info("Entered delete client by  client id method in service");
		bean.setMsg("successfully deleted client  id : "+id);
		bean.setStatus(true);
		return bean;
	}

// //oldHrms
//  //updateByID
//	@Override
	public ClientDetailsEntity updateClientById(int cliId, ClientDetailsEntity entity) {
		
		this.log.info("Entered update  client by  client id method in service");
		
		
		//ClientDetailsEntity Client = this.clientRepo.findByClientid(cliId);
		Optional<ClientDetailsEntity> client = this.clientRepo.findById(cliId);
		
		if(client.isPresent()) {
			
			client.get().setClientName(entity.getClientName());
			client.get().setAddress(entity.getAddress());
			client.get().setCountryId(entity.getCountryId());
			client.get().setCreatedBy(1);
			client.get().setCreatedDate(LocalDateTime.now());
			client.get().setEmail(entity.getEmail());
			client.get().setFax(entity.getFax());
            client.get().setIs_active((byte) 1);
            client.get().setModifiedBy(1);
            client.get().setModifiedDate(LocalDateTime.now());
            client.get().setPhoneNo(entity.getPhoneNo());
            client.get().setPoc(entity.getPoc());
            client.get().setStateId(entity.getStateId());           
           // ClientDetailsEntity save = this.clientRepo.save(client.orElseThrow());
           
		
		}
		
		this.log.info("succesfully updated  client method in service");
		return clientRepo.save(client.get());
	}

}

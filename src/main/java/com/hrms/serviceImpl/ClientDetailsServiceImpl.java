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

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDetailsRepository clientRepo;

	@Autowired
	private EntityBeanResponseCommon bean;

	@Autowired
	private ClientsResponseBean clientBean;

//	@Override
//	public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	 //oldHrms
	// saveClient
	@Override
	public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity) {
		
		entity.setCreatedBy(0);
		entity.setCreatedDate(LocalDateTime.now());
		entity.setIs_active((byte) 1);
		entity.setModifiedBy(1);
		entity.setModifiedDate(LocalDateTime.now());
		ClientDetailsEntity clientDeatils = this.clientRepo.save(entity);

		if (clientDeatils != null) {

			bean.setMsg("Successfully Client  Details Saved");
			bean.setStatus(true);

		} else {

			bean.setMsg("Client Details not saved");
			bean.setStatus(false);
		}

		return bean;
	}
	
	
	 //oldHrms	
//    	
//    //getAllClients
//	@Override
	public ClientsResponseBean getAllClients() {

		List<ClientDetailsEntity> findAll = this.clientRepo.findAll();
		if (!findAll.isEmpty()) {
			clientBean.setMessage("clients details fetched Successfully");
			clientBean.setStatus(true);
			clientBean.setClientslist(findAll);
		} else {

			clientBean.setMessage("Failed Retrive Clients Details");
			clientBean.setStatus(false);

		}

		return clientBean;
	}


// //oldHrms
	
//	 //GetSingleClientByClientID
//	@Override
	public ClientDetailsEntity getClientByClientId(int clientId) {
		//ClientDetailsEntity findByClientid = this.clientRepo.findByClientid(clientId);
		Optional<ClientDetailsEntity> client = this.clientRepo.findById(clientId);
		
		return client.get();
	}


     //oldHrms
	//update
//	@Override
//	public EntityBeanResponseCommon updateClientDetails(ClientDetailsEntity entity) {
//		ClientDetailsEntity save = this.clientRepo.save(entity);
//		if(save!=null) {
//			
//			bean.setMsg("successfully Updated id of client:"+save.getId());
//			bean.setStatus(true);
//			
//
//		}else {
//			bean.setMsg("Not updated");
//			bean.setStatus(false);
//		}
//		
//		
//		return bean;
//	}



	 //oldHrms
//	@Override
	public EntityBeanResponseCommon deletedClient(int id) {
		//ClientDetailsEntity findByClientid = this.clientRepo.findByClientid(id);
		
		Optional<ClientDetailsEntity> client = this.clientRepo.findById(id);
		
		this.clientRepo.delete(client.get());
		bean.setMsg("successfully deleted client  id : "+id);
		bean.setStatus(true);
		return bean;
	}

// //oldHrms
//
//  //updateByID
//	@Override
	public ClientDetailsEntity updateClientById(int cliId, ClientDetailsEntity entity) {
		
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
            
		
		
		//Client.setAddress(entity.getAddress());
		//Client.setClientname(entity.getClientname());
		//Client.setCountryname(entity.getCountryname());
		//Client.setEmail(entity.getEmail());
		//Client.setPhonenum(entity.getPhonenum());
		//Client.setStatename(entity.getStatename());
		//Client.setIs_active(entity.getIs_active());
		//Client.setId(entity.getId());
		
		//ClientDetailsEntity save = this.clientRepo.save(Client);
		
		}
		
		return clientRepo.save(client.get());
	}


	



  
	

	

	


	/*
	 * @Override public ClientDetailsEntity updateClientDetails(int clientId,
	 * ClientDetailsEntity enityt) {
	 * 
	 * ClientDetailsEntity findByClientid =
	 * this.clientRepo.findByClientid(clientId);
	 * findByClientid.setClientname(enityt.getClientname());
	 * findByClientid.setAddress(enityt.getAddress());
	 * findByClientid.setCountryname(enityt.getCountryname());
	 * findByClientid.setEmail(enityt.getEmail());
	 * findByClientid.setIs_active(enityt.getIs_active());
	 * findByClientid.setPhonenum(enityt.getPhonenum());
	 * findByClientid.setStatename(enityt.getStatename());
	 * findByClientid.setId(enityt.getClientid());
	 * 
	 * ClientDetailsEntity save = this.clientRepo.save(findByClientid);
	 * 
	 * 
	 * //ClientDetailsEntity save = this.clientRepo.save(enityt);
	 * 
	 * return save; }
	 */


   //UpdateClientDetails
	/*
	 * @Override public ClientDetailsEntity updateClientDetrails(int id,
	 * ClientDetailsEntity entity) {
	 * 
	 * ClientDetailsEntity updateEntity = this.clientRepo.findByClientid(id);
	 * 
	 * updateEntity.setClientname(entity.getClientname());
	 * updateEntity.setAddress(entity.getAddress());
	 * updateEntity.setCountryname(entity.getCountryname());
	 * updateEntity.setEmail(entity.getEmail());
	 * updateEntity.setIs_active(entity.getIs_active());
	 * updateEntity.setPhonenum(entity.getPhonenum());
	 * updateEntity.setStatename(entity.getStatename());
	 * //updateEntity.setClientid(entity.getId());
	 * 
	 * ClientDetailsEntity save = this.clientRepo.save(updateEntity);
	 * 
	 * 
	 * 
	 * return save; }
	 */
	
	

    //UpdateClientDetails

	/*
	 * @Override public EntityBeanResponse updateClient(ClientDetailsEntity
	 * updateEntity) {
	 * 
	 * ClientDetailsEntity findByClientid =
	 * this.clientRepo.findByClientid(updateEntity.getClientid());
	 * 
	 * ClientDetailsEntity ClientEntity=new ClientDetailsEntity();
	 * 
	 * ClientEntity.setClientname(findByClientid.getClientname());
	 * ClientEntity.setCountryname(findByClientid.getCountryname());
	 * ClientEntity.setAddress(findByClientid.getAddress());
	 * ClientEntity.setEmail(findByClientid.getEmail());
	 * ClientEntity.setIs_active(findByClientid.getIs_active());
	 * ClientEntity.setPhonenum(findByClientid.getPhonenum());
	 * ClientEntity.setStatename(findByClientid.getStatename());
	 * ClientEntity.setClientid(findByClientid.getClientid());
	 * 
	 * ClientDetailsEntity save = this.clientRepo.save(ClientEntity); if(save!=null)
	 * { bean.setMsg("Successfully Client Details Updated"); bean.setStatus(true);
	 * }else { bean.setMsg("Failed to update the Client Details");
	 * bean.setStatus(false); }
	 * 
	 * 
	 * return bean; }
	 */


  
    //GetSingleClient
	//@Override
	//public ClientDetailsEntity getSingleClientrByid(int clientId) {
	//	Optional<ClientDetailsEntity> findById = this.clientRepo.findById(clientId);
		
		//return findById.orElseThrow();
	//}
	
	
	
	//........................................Old-code......................................
	
	 //oldHrms
		// saveClient
//		@Override
//		public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity) {
	//
//			ClientDetailsEntity clientDeatils = this.clientRepo.save(entity);
	//
//			if (clientDeatils != null) {
	//
//				bean.setMsg("Successfully Client  Details Saved");
//				bean.setStatus(true);
	//
//			} else {
	//
//				bean.setMsg("Client Details not saved");
//				bean.setStatus(false);
//			}
	//
//			return bean;
//		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

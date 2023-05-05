package com.hrms.serviceImpl;

import java.util.List;

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

	// saveClient
	@Override
	public EntityBeanResponseCommon saveClientDetails(ClientDetailsEntity entity) {

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
	
	
	
    	
    //getAllClients
	@Override
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



	
	 //GetSingleClientByClientID
	@Override
	public ClientDetailsEntity getClientByClientId(int clientId) {
		ClientDetailsEntity findByClientid = this.clientRepo.findByClientid(clientId);
		
		return findByClientid;
	}



	//update
	@Override
	public EntityBeanResponseCommon updateClientDetails(ClientDetailsEntity entity) {
		ClientDetailsEntity save = this.clientRepo.save(entity);
		
		if(save!=null) {
			bean.setMsg("successfully Updated id of client:"+save.getClientid());
			bean.setStatus(true);
			

		}else {
			bean.setMsg("Not updated");
			bean.setStatus(true);
		}
		
		
		return bean;
	}




	@Override
	public EntityBeanResponseCommon deletedClient(int id) {
		ClientDetailsEntity findByClientid = this.clientRepo.findByClientid(id);
		
		this.clientRepo.delete(findByClientid);
		bean.setMsg("successfully cilent deleted");
		bean.setStatus(true);
		return bean;
	}



  //updateByID
	@Override
	public ClientDetailsEntity updateClientById(int cliId, ClientDetailsEntity entity) {
		
		ClientDetailsEntity Client = this.clientRepo.findByClientid(cliId);
		Client.setAddress(entity.getAddress());
		Client.setClientname(entity.getClientname());
		Client.setCountryname(entity.getCountryname());
		Client.setEmail(entity.getEmail());
		Client.setPhonenum(entity.getPhonenum());
		Client.setStatename(entity.getStatename());
		Client.setIs_active(entity.getIs_active());
		Client.setId(entity.getId());
		
		ClientDetailsEntity save = this.clientRepo.save(Client);
		
		return save;
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
	

}

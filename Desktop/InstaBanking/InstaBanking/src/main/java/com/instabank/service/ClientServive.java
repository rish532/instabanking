package com.instabank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instabank.exception.DuplicateClientException;
import com.instabank.model.InstaClient;
import com.instabank.repository.ClientRepository;

@Service
public class ClientServive {
	 @Autowired
	ClientRepository clientRepo;
	public InstaClient createClient(InstaClient instaClient) throws DuplicateClientException {
		// TODO Auto-generated method stub
		return clientRepo.createClient(instaClient);
		
	}

	public Optional<InstaClient> findByClientByidentityId(Long identity_number) {
		// TODO Auto-generated method stub
		return clientRepo.findByClientByidentityId(identity_number);
	}

	public InstaClient updateClientByidentityId(Long identity_number, InstaClient instaClient) throws DuplicateClientException {
		// TODO Auto-generated method stub
		return clientRepo.updateClientByidentityId(identity_number,instaClient);
	}

	public void deleteClient(Long identity_number) {
		// TODO Auto-generated method stub
		clientRepo.deleteClient(identity_number);
	}

	public InstaClient findClientBymobile(Long mobile_number) {
		// TODO Auto-generated method stub
		return clientRepo.findClientBymobile(mobile_number);
	}

	public InstaClient findClientByfirstName(String firstName) {
		// TODO Auto-generated method stub
		return clientRepo.findClientByfirstName(firstName);
	}

}

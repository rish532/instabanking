package com.instabank.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instabank.exception.ClientNotFoundException;
import com.instabank.exception.DuplicateClientException;
import com.instabank.model.InstaClient;
import com.instabank.service.ClientServive;

@RestController
@RequestMapping("/instabank/home")
public class ClientController {

	 @Autowired
	 ClientServive clientService;
	 
	 @RequestMapping(value="/create", method=RequestMethod.POST)
	 public ResponseEntity<String> createClient(@Valid @RequestBody InstaClient instaClient) throws DuplicateClientException
	 {
		 clientService.createClient(instaClient);
		 return new ResponseEntity < String > ("Response from post", HttpStatus.OK);
	 }
	 
	 @GetMapping("/getclientbyidentityid/{id}")
	    public ResponseEntity<Optional<InstaClient>> getClientByidentityId(@PathVariable(value = "id") Long identity_number)
	      throws ClientNotFoundException {
		 Optional<InstaClient> client1 = (Optional<InstaClient>) clientService.findByClientByidentityId(identity_number);
	           if(client1==null)
	        	   throw new ClientNotFoundException("Client not found for this id :: " + identity_number);
	        return ResponseEntity.ok().body(client1);
	    }
	 
	 @GetMapping("/getclientbymobile/{num}")
	    public ResponseEntity<InstaClient> getClientBymobile(@PathVariable(value = "num") Long mobile_number)
	      throws ClientNotFoundException {
		 InstaClient client1 = (InstaClient) clientService.findClientBymobile(mobile_number);
	           if(client1==null)
	        	   throw new ClientNotFoundException("Client not found for this mobile :: " + mobile_number);
	        return ResponseEntity.ok().body(client1);
	    }
	 @GetMapping("/getclientbyfirstname/{name}")
	    public ResponseEntity<InstaClient> getClientByfirstname(@PathVariable(value = "name") String firstName)
	      throws ClientNotFoundException {
		 InstaClient client1 = (InstaClient) clientService.findClientByfirstName(firstName);
	           if(client1==null)
	        	   throw new ClientNotFoundException("Client not found for this firstname :: " + firstName);
	        return ResponseEntity.ok().body(client1);
	    } 
	 
	 @PutMapping("/updateclient/{id}")
	    public void updateClientByidentityId(@PathVariable(value = "id") Long identity_number,
	     @Valid @RequestBody InstaClient instaClient) throws ClientNotFoundException, DuplicateClientException {
		 InstaClient client = (InstaClient) clientService.updateClientByidentityId(identity_number,instaClient);
				 if(client==null)
		        	   throw new ClientNotFoundException("Client not found for this id :: " + identity_number);
	        	    }

	    @DeleteMapping("/deleteclient/{id}")
	    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long identity_number)
	      throws ClientNotFoundException {
	    	Optional<InstaClient> client = (Optional<InstaClient>) clientService.findByClientByidentityId(identity_number);
	    	if(client==null)
	        	   throw new ClientNotFoundException("Client not found for this id :: " + identity_number);
	    	clientService.deleteClient(identity_number);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	 
}

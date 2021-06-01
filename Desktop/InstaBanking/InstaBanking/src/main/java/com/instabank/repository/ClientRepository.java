package com.instabank.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instabank.exception.ClientNotFoundException;
import com.instabank.exception.DuplicateClientException;
import com.instabank.model.InstaClient;

@Repository
public class ClientRepository {
	List<InstaClient> ll=new ArrayList<InstaClient>();

	@Autowired
	public InstaClient createClient(InstaClient instaClient) throws DuplicateClientException {
		// TODO Auto-generated method stub
		if(!ll.isEmpty())
		{
			for(InstaClient il:ll)
			{
				if(instaClient.getMobile_Number()==il.getMobile_Number() || instaClient.getIdentity_Number()==il.getIdentity_Number())
				{
					throw new DuplicateClientException("duplicate client ,already registered"); 
				}
			}
		}
		ll.add(instaClient);
		return instaClient;
	}

	public Optional<InstaClient> findByClientByidentityId(Long identity_number) {
		// TODO Auto-generated method stub
		for(InstaClient cl:ll)
		{
			if(identity_number.equals(cl.getIdentity_Number()))
			{
				return Optional.of(cl);
			}
			continue;		
		}
		return null;
	}

	public InstaClient updateClientByidentityId(Long identity_number, InstaClient instaClient) throws DuplicateClientException {
		
		// TODO Auto-generated method stub
		for(InstaClient clint:ll)
		{
			if(identity_number.equals(clint.getIdentity_Number()))
			{

	     clint.setFirstName(instaClient.getFirstName());
	     clint.setLastName(instaClient.getLastName());
	     if(clint.getMobile_Number()!=(clint.getIdentity_Number()))
	     {
	    	 for(InstaClient checkdup:ll)
	    	 {
	    		 if(clint.getMobile_Number()==checkdup.getMobile_Number())
	    			 throw new DuplicateClientException("duplicate client ,already registered");
	    	 }
	     }
	     clint.setMobile_Number(instaClient.getMobile_Number());
	     clint.setPhysical_Address(instaClient.getPhysical_Address());
	     return clint;
			}
			
	}
	return null;
	}

	public void deleteClient(Long identity_number) {
		// TODO Auto-generated method stub
		Iterator<InstaClient> itr=ll.iterator();
		while(itr.hasNext())
		{
			InstaClient clnt=(InstaClient) itr.next();
			if(clnt.getIdentity_Number()==(identity_number))
			{
				itr.remove();
			}
		}
	}

	public InstaClient findClientBymobile(Long mobile_number) {
		// TODO Auto-generated method stub
		for(InstaClient cl:ll)
		{
			if(mobile_number.equals(cl.getMobile_Number()))
			{
				return cl;
			}
			continue;		
		}
		return null;
	}

	public InstaClient findClientByfirstName(String firstName) {
		// TODO Auto-generated method stub
		for(InstaClient cl:ll)
		{
			if(firstName.equals(cl.getFirstName()))
			{
				return cl;
			}
			continue;		
		}
		return null;
	}	

}

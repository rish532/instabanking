package com.instabank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.instabank.InstaBanking.InstaBankingApplication;
import com.instabank.exception.ClientNotFoundException;
import com.instabank.exception.DuplicateClientException;
import com.instabank.model.InstaClient;
import com.instabank.repository.ClientRepository;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;


@SpringBootTest(classes = InstaBankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientServiveTest {
	
	@Mock
	private ClientRepository clientRepository;
	@InjectMocks
	private ClientServive clientServive;
	
	@Test
	public void testCreateClient() throws DuplicateClientException {
		InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");
        when(clientRepository.createClient(ArgumentMatchers.any(InstaClient.class))).thenReturn(client);
        InstaClient clientcreated=clientServive.createClient(client);
        assertThat(clientcreated).isSameAs(client);
        verify(clientRepository).createClient(client);
        
	}

	@Test
	void testFindByClientByidentityId() throws ClientNotFoundException{
		InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");
		when(clientRepository.findByClientByidentityId(client.getIdentity_Number())).thenReturn(Optional.of(client));
		Optional<InstaClient> expected = clientServive.findByClientByidentityId(client.getIdentity_Number());
		assertEquals(expected.get(), client);
		verify(clientRepository).findByClientByidentityId(client.getIdentity_Number());
	}
	
	@Test
	void testFindClientBymobile() throws ClientNotFoundException{
		InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");
		given(clientRepository.findClientBymobile(client.getMobile_Number())).willReturn(client);
		InstaClient expected = clientServive.findClientBymobile(client.getMobile_Number());
		assertEquals(expected, client);
		verify(clientRepository).findClientBymobile(client.getMobile_Number());
	}
	
	@Test
	void testFindClientByfirstName() throws ClientNotFoundException{
		InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");
		given(clientRepository.findClientByfirstName(client.getFirstName())).willReturn(client);
		InstaClient expected = clientServive.findClientByfirstName(client.getFirstName());
		assertEquals(expected, client);
		verify(clientRepository).findClientByfirstName(client.getFirstName());
	}
	
	@Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
		InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");

        when(clientRepository.findByClientByidentityId(client.getIdentity_Number())).thenReturn(Optional.of(client));

        clientServive.deleteClient(client.getIdentity_Number());
        verify(clientRepository).deleteClient(client.getIdentity_Number());
    }

	@Test
    public void whenGivenId_shouldUpdateUser_ifFound() throws DuplicateClientException {
		InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");
		InstaClient newclient = new InstaClient("rishabh","shukla",1234567890234l,8302803047l, "maharajganj");

       given(clientRepository.findByClientByidentityId(client.getIdentity_Number())).willReturn(Optional.of(client));
       clientServive.updateClientByidentityId(client.getIdentity_Number(), newclient);

        verify(clientRepository).updateClientByidentityId(client.getIdentity_Number(), newclient);
//        verify(clientRepository).findByClientByidentityId(client.getIdentity_Number());
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws DuplicateClientException {
    	InstaClient client = new InstaClient("rish","shukla",1234567890234l,8302803047l, "maharajganj");
    	InstaClient newclient = new InstaClient("rishabh","shukla",1234567890243l,8302803047l, "maharajganj");

        given(clientRepository.findByClientByidentityId(anyLong())).willReturn(Optional.ofNullable(null));
        clientServive.updateClientByidentityId(client.getIdentity_Number(), newclient);
    }
//
//	@Test
//	void testUpdateClientByidentityId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteClient() {
//		fail("Not yet implemented");
//	}

}

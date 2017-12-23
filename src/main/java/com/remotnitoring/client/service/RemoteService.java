package com.remotnitoring.client.service;

import javax.xml.ws.ServiceMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.remotnitoring.client.model.AuthenticateRequest;
import com.remotnitoring.client.model.AuthenticateResponse;
import com.remotnitoring.client.restclient.PingPublicRestClient;
import com.remotnitoring.client.restclient.PingSecureRestClient;

@Service
public class RemoteService {
	private final Logger log = LoggerFactory.getLogger(RemoteService.class);
	
	private PingPublicRestClient pingRestClient;
	
	private PingSecureRestClient pingSecureRestClient;
	
	private String idToken = null;
	
	@Value("${remotnitoring.server.user}")
	private String user;

	@Value("${remotnitoring.server.pwd}")
	private String pwd;
	
	public RemoteService(PingPublicRestClient pingRestClient, PingSecureRestClient pingSecureRestClient) {
		super();
		this.pingRestClient = pingRestClient;
		this.pingSecureRestClient = pingSecureRestClient;
	}

	
	private void genereteIdToket() {
		AuthenticateRequest aRequest = new AuthenticateRequest();
		aRequest.setUsername(user);
		aRequest.setPassword(pwd); 
		
		AuthenticateResponse aResponse = pingRestClient.authenticate(aRequest);
		log.info("idToken - {} ",aResponse.getIdToken());
		
		this.idToken=aResponse.getIdToken();
	}
	
	private String generateBearerHeader () {
		return "Bearer " + idToken;
	}
	
    @Scheduled(fixedRate = 60000)  //1m
	public void call () {
		
		log.info("Init");
		
		String s = pingRestClient.getHealth().getStatus();
		log.info("Status server - {} ", s);
		
		ResponseEntity<Void> response = pingSecureRestClient.ping();
		log.info(printMsgPingDone(response) );

	}
	
    private String printMsgPingDone (ResponseEntity<Void> response) {
		return "Ping done: " + response.getHeaders().getFirst("x-remotnitoringapp-ping");
    	
    }
}

package com.remotnitoring.client.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.remotnitoring.client.model.AuthenticateRequest;
import com.remotnitoring.client.model.AuthenticateResponse;
import com.remotnitoring.client.service.RemoteService;

import feign.RequestInterceptor;
import feign.RequestTemplate;


public class HttpSecureFeignConfiguration {

	private final Logger log = LoggerFactory.getLogger(HttpSecureFeignConfiguration.class);

	@Value("${remotnitoring.server.user}")
	private String user;

	@Value("${remotnitoring.server.pwd}")
	private String pwd;
	
	PingPublicRestClient pingPublicRestClient = null;
		
	public HttpSecureFeignConfiguration (PingPublicRestClient pingPublicRestClient) {
		this.pingPublicRestClient = pingPublicRestClient;
	}
	
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
	    return new RequestInterceptor() {

			@Override
			public void apply(RequestTemplate requestTemplate) {
				
				log.debug("Request: {} - {} ", requestTemplate.request().url() , requestTemplate.method());
				
				String headerValue = genereteIdToket();				
				requestTemplate.header("Authorization", headerValue);
			};
			
			private String genereteIdToket() {
				AuthenticateRequest aRequest = new AuthenticateRequest();
				aRequest.setUsername(user);
				aRequest.setPassword(pwd); 
				
				AuthenticateResponse aResponse = pingPublicRestClient.authenticate(aRequest);
				log.debug("idToken - {} ",aResponse.getIdToken());
				
				return "Bearer " +  aResponse.getIdToken();
			}

	    };	    
	}
		
}

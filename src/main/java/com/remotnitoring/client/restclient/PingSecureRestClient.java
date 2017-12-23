package com.remotnitoring.client.restclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.remotnitoring.client.model.AuthenticateRequest;
import com.remotnitoring.client.model.AuthenticateResponse;
import com.remotnitoring.client.model.HealthResponse;

@FeignClient(name = "pingSecure", url = "${remotnitoring.server.url}", configuration=HttpSecureFeignConfiguration.class)
public interface PingSecureRestClient {
	
    
    @PostMapping("/api/monitor/heartbeats/ping")
    ResponseEntity<Void> ping ();
    
    
}

package com.remotnitoring.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticateResponse {

    @JsonProperty("id_token")
	private String idToken;

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	
	
	
}

package com.droidablebee.springboot.rest.domain;

import javax.validation.constraints.NotNull;

public class InfosAuth {

    @NotNull
    private String userName;
    
    @NotNull
    private String password;
    
	public InfosAuth() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
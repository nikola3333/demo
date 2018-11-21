package com.isa.entity;

import javax.persistence.Entity;

@Entity
public class RestauranManager extends User{

	private boolean confirmedRegistration;
	public RestauranManager() {
		super();
	}

	public RestauranManager(boolean confirmedRegistration) {
		super();
		this.confirmedRegistration = confirmedRegistration;
	}

	public boolean isConfirmedRegistration() {
		return confirmedRegistration;
	}

	public void setConfirmedRegistration(boolean confirmedRegistration) {
		this.confirmedRegistration = confirmedRegistration;
	}

}

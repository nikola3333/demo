package com.isa.entity;

import javax.persistence.Entity;

@Entity
public class Guest extends User {

	private boolean confirmedRegistration;
	//private Set<Guest> friends = new HashSet<Guest>();
	public Guest() {
		super();
	}

	public Guest(boolean confirmedRegistration) {
		super();
		this.confirmedRegistration = confirmedRegistration;
	}

	public boolean isConfirmedRegistration() {
		return confirmedRegistration;
	}

	public void setConfirmedRegistration(boolean confirmedRegistration) {
		this.confirmedRegistration = confirmedRegistration;
		//this.friends = new HashSet<Guest>();
	}

	
	
	

}

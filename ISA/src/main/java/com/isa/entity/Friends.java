package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Friends {

	private Long id;
	private Guest requestSender;
	private Guest requestResponder;
	private boolean confirmedFriendship;
	private Long version;
	public Friends() {
		super();
	}
	public Friends(Guest requestSender, Guest requestResponder, boolean confirmedFriendship) {
		super();
		this.requestSender = requestSender;
		this.requestResponder = requestResponder;
		this.confirmedFriendship = confirmedFriendship;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "request_sender", nullable = false)
	public Guest getRequestSender() {
		return requestSender;
	}
	public void setRequestSender(Guest requestSender) {
		this.requestSender = requestSender;
	}
	@ManyToOne
	@JoinColumn(name = "reguest_responder",nullable = false)
	public Guest getRequestResponder() {
		return requestResponder;
	}
	public void setRequestResponder(Guest requestResponder) {
		this.requestResponder = requestResponder;
	}
	@Column(name = "confirmed_friendship")
	public boolean isConfirmedFriendship() {
		return confirmedFriendship;
	}
	public void setConfirmedFriendship(boolean confirmedFriendship) {
		this.confirmedFriendship = confirmedFriendship;
	}
	
	@Version
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	
	
}

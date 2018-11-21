package com.isa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {

	private Long id;
	private Restaurant restaurant;
	private List<Guest> guests;
	private Date date;
	private Date stay;
	private List<Guest> invitedFriends;
	private Integer seatNum;
	private List<GuestOrder> orders;
	public Reservation() {
		super();
	}


	public Reservation(Restaurant restaurant, Date date, Date stay,Integer seatNum) {
		super();
		this.restaurant = restaurant;
		this.date = date;
		this.stay = stay;
		this.seatNum = seatNum;
	}


	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="RESTAURANT_ID")
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@ManyToMany
	@JoinTable(name = "RESERVATION_GUEST",
		joinColumns = 
			@JoinColumn(name = "RESERVATION_ID"),
		inverseJoinColumns =
			@JoinColumn(name = "GUEST_ID")
			)
	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStay() {
		return stay;
	}

	public void setStay(Date stay) {
		this.stay = stay;
	}

	@ManyToMany
	@JoinTable(name = "INVITED_FRIENDS",
		joinColumns = 
			@JoinColumn(name = "RESERVATION_ID"),
		inverseJoinColumns =
			@JoinColumn(name = "GUEST_ID")
			)
	public List<Guest> getInvitedFriends() {
		return invitedFriends;
	}

	public void setInvitedFriends(List<Guest> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}


	public Integer getSeatNum() {
		return seatNum;
	}


	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}


	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "RESERVATION_ORDERS",
			joinColumns = @JoinColumn(name = "RESERVATION_ID"),
			inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	public List<GuestOrder> getOrders() {
		return orders;
	}


	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
	}

	
	

}

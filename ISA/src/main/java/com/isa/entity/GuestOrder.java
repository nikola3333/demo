package com.isa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class GuestOrder {

	Long id;
	List<Foodstuffs> foodstuffs;
	List<Drink> drinks;
	Guest guest;
	//Boolean status; 
	
	public GuestOrder() {
		super();
	}

	
	
/*	public GuestOrder(Long id, Guest guest, Boolean status) {
		super();
		this.id = id;
		this.guest = guest;
		this.status = status;
	}*/



	public GuestOrder(Guest guest) {
		super();
		this.guest = guest;
		this.foodstuffs = new ArrayList<Foodstuffs>();
		this.drinks = new ArrayList<Drink>();
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany
	@JoinTable(name = "ORDER_FOOD",
	joinColumns = 
		@JoinColumn(name = "ORDER_ID"),
	inverseJoinColumns =
		@JoinColumn(name = "FOODSTUFFS_ID")
		)
	public List<Foodstuffs> getFoodstuffs() {
		return foodstuffs;
	}

	public void setFoodstuffs(List<Foodstuffs> foodstuffs) {
		this.foodstuffs = foodstuffs;
	}

	@ManyToMany
	@JoinTable(name = "ORDER_DRINKS",
	joinColumns = 
		@JoinColumn(name = "ORDER_ID"),
	inverseJoinColumns =
		@JoinColumn(name = "DRINK_ID")
		)
	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

	@ManyToOne
	@JoinColumn(name = "GUEST_ID")
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

/*	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}*/

	
	
	
	
}

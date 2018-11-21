package com.isa.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Order {

	Long id;
	List<Foodstuffs> foodstuffs;
	List<Drink> drinks;
	Guest guest;
	
	public Order() {
		super();
	}

	public Order(Guest guest) {
		super();
		this.guest = guest;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
		@JoinColumn(name = "FOOD_ID")
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

	
	
	
	
}

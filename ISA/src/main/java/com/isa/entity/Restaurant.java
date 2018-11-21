package com.isa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Restaurant {

	private Long id;
	private String name;
	private String typeOfRestaurant;
	private List<Region> regions;
	private List<Foodstuffs> menu;
	private List<Drink> drinks;
	public Restaurant() {
		super();
	}



	public Restaurant(Long id, String typeOfRestaurant,String name) {
		super();
		this.id = id;
		this.typeOfRestaurant = typeOfRestaurant;
		this.name = name;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_ID")
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "RESTAURANT_REGIONS",
			joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
			inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	
	public String getTypeOfRestaurant() {
		return typeOfRestaurant;
	}

	public void setTypeOfRestaurant(String typeOfRestaurant) {
		this.typeOfRestaurant = typeOfRestaurant;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "MENU",
			joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
			inverseJoinColumns = @JoinColumn(name = "FOOD_ID"))	
	public List<Foodstuffs> getMenu() {
		return menu;
	}

	public void setMenu(List<Foodstuffs> menu) {
		this.menu = menu;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "RESTAURANT_DRINKS",
			joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
			inverseJoinColumns = @JoinColumn(name = "DRINK_ID"))
	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}
	
	
}

package com.isa.service;

import java.util.List;

import com.isa.entity.Foodstuffs;


public interface FoodstuffsService {

	List<Foodstuffs> findAll();
	
	Foodstuffs save(Foodstuffs food);

	Foodstuffs findOne(Long id);

	void delete(Long id);
}

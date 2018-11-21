package com.isa.service;

import java.util.List;

import com.isa.entity.Drink;



public interface DrinkService {

	List<Drink> findAll();

	Drink save(Drink drink);

	Drink findOne(Long id);

	void delete(Long id);
}

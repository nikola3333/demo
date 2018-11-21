package com.isa.service;

import java.util.List;

import org.assertj.core.util.Lists;

import com.isa.entity.Drink;
import com.isa.repository.DrinkRepository;



public class DrinkServiceImpl implements DrinkService {

	private DrinkRepository dr;
	@Override
	public List<Drink> findAll() {
	
		return Lists.newArrayList(dr.findAll());
	}

	@Override
	public Drink save(Drink drink) {
		// TODO Auto-generated method stub
		return dr.save(drink);
	}

	@Override
	public Drink findOne(Long id) {
		// TODO Auto-generated method stub
		return dr.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dr.delete(id);
	}

}

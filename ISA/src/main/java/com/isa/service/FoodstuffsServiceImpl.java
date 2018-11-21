package com.isa.service;

import java.util.List;

import org.assertj.core.util.Lists;

import com.isa.entity.Foodstuffs;
import com.isa.repository.FoodStuffsRepository;

public class FoodstuffsServiceImpl implements FoodstuffsService {

	private FoodStuffsRepository fsr;

	@Override
	public List<Foodstuffs> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(fsr.findAll());
		
	}

	@Override
	public Foodstuffs save(Foodstuffs food) {
		// TODO Auto-generated method stub
		return fsr.save(food);
	}

	@Override
	public Foodstuffs findOne(Long id) {
		// TODO Auto-generated method stub
		return fsr.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		fsr.delete(id);
	}
}

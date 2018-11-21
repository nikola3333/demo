package com.isa.service;

import java.util.List;

import com.isa.entity.Restaurant;

public interface RestaurantService {

	public List<Restaurant> findAllSortedBy(String sortCriteria);
	public Restaurant findOne(Long id);
	public Restaurant save(Restaurant r);
	public List<Restaurant> findByCondition(String condition,String sortCriteria);
	public void delete(Restaurant r);
	
	
}

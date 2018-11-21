package com.isa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.Restaurant;
import com.isa.repository.RestaurantRepository;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> findAllSortedBy(String sortCriteria) {
		// TODO Auto-generated method stub
		if(sortCriteria.equals("Type"))
			return (List<Restaurant>) restaurantRepository.findAllSortedByT();
		return (List<Restaurant>) restaurantRepository.findAllSortedByN();
	
	}

	@Override
	public Restaurant findOne(Long id) {
		// TODO Auto-generated method stub
		return restaurantRepository.findOne(id);
	}

	@Override
	public Restaurant save(Restaurant r) {
		// TODO Auto-generated method stub
		return restaurantRepository.save(r);
	}

	@Override
	public void delete(Restaurant r) {
		// TODO Auto-generated method stub
		restaurantRepository.delete(r);
	}

	@Override
	public List<Restaurant> findByCondition(String condition,String sortCriteria) {
		if(sortCriteria.equals("Type"))
			return restaurantRepository.findByConditionT(condition+"%");
		return restaurantRepository.findByConditionN(condition+"%");
	}
	
	
}

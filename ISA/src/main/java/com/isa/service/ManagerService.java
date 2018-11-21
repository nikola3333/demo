package com.isa.service;

import java.util.List;

import com.isa.entity.RestauranManager;

public interface ManagerService {

    public List<RestauranManager> findAll();
	
	public RestauranManager findByEmail(String email);
	
	public RestauranManager findOne(Long id);
	
	public RestauranManager save(RestauranManager guest) ;
	
	public void delete(RestauranManager guest);
	
	public RestauranManager update(RestauranManager guest);

	public void confirmRegistration(Long id);
}

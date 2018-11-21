package com.isa.service;

import java.util.List;

import com.isa.entity.Cook;

public interface CookService {

	public List<Cook> findAll();
	
	public Cook findByEmail(String email);
	
	public Cook findOne(Long id);
	
	public Cook save(Cook c) ;
	
	public void delete(Cook c);
	
	public Cook update(Cook c);
}

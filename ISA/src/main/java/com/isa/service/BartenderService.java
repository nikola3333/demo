package com.isa.service;

import java.util.List;

import com.isa.entity.Bartender;

public interface BartenderService {

	public List<Bartender> findAll();
	
	public Bartender findByEmail(String email);
	
	public Bartender findOne(Long id);
	
	public Bartender save(Bartender b) ;
	
	public void delete(Bartender b);
	
	public Bartender update(Bartender b);
}

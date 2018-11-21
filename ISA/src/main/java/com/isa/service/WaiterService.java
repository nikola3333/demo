package com.isa.service;

import java.util.List;

import com.isa.entity.Waiter;

public interface WaiterService {

	public List<Waiter> findAll();
	
	public Waiter findByEmail(String email);
	
	public Waiter findOne(Long id);
	
	public Waiter save(Waiter w) ;
	
	public void delete(Waiter w);
	
	public Waiter update(Waiter w);
}

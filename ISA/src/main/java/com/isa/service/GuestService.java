package com.isa.service;

import java.util.List;

import com.isa.entity.Guest;


public interface GuestService {

	public List<Guest> findAll();
	
	public Guest findByEmail(String email);
	
	public Guest findOne(Long id);
	
	public Guest save(Guest guest) ;
	
	public void delete(Guest guest);
	
	public Guest update(Guest guest);

	public void confirmRegistration(Long id);

	public List<Guest> findNoneFriends(Long id);
	
	public List<Guest> findNoneFriends(Long id,String condition);
}

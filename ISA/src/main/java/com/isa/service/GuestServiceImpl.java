package com.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Guest;
import com.isa.repository.GuestRepository;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;

	@Override
	public List<Guest> findAll() {

		return (List<Guest>) guestRepository.findAll();
	}

	@Override
	public Guest findByEmail(String email) {
		// TODO Auto-generated method stub

		return guestRepository.findByEmail(email);
	}

	@Override
	public Guest save(Guest guest) {
		return guestRepository.save(guest);
	}

	@Override
	public void delete(Guest guest) {
		guestRepository.delete(guest.getId());
	}

	@Override
	public Guest findOne(Long id) {
		// TODO Auto-generated method stub
		return guestRepository.findOne(id);
	}

	@Override
	public void confirmRegistration(Long id) {
		Guest g = guestRepository.findOne(id);
		g.setConfirmedRegistration(true);
		guestRepository.save(g);
	}

	@Override
	public Guest update(Guest guest) {
		Guest g = guestRepository.findByEmail(guest.getEmail());
		g.setPassword(guest.getPassword());
		return guestRepository.save(g);
	}

	@Override
	public List<Guest> findNoneFriends(Long id) {
		// TODO Auto-generated method stub
		ArrayList<Guest> noneFriends = new ArrayList<Guest>(guestRepository.findNoneFriends(id));
		Guest loggedUser = null;
		for(Guest g : noneFriends){
			if(g.getId().equals(id)){
				loggedUser = g;
			}
		}
		noneFriends.remove(loggedUser);
		return noneFriends;
	}

	@Override
	public List<Guest> findNoneFriends(Long id,String condition) {
		ArrayList<Guest> noneFriends = null;
		String splitted [] ;
		splitted = condition.split(" ");
		if(splitted.length == 2){
			noneFriends = new ArrayList<Guest>(guestRepository.findNoneFriends( splitted[0]+"%",splitted[1] + "%",id));
		}else{
			noneFriends = new ArrayList<Guest>(guestRepository.findNoneFriends(id, condition+"%"));
		}
		Guest loggedUser = null;
		for(Guest g : noneFriends){
			if(g.getId().equals(id)){
				loggedUser = g;
			}
		}
		noneFriends.remove(loggedUser);
		return noneFriends;
	}
	
}

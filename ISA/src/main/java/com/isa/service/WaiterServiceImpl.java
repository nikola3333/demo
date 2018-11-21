package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Waiter;
import com.isa.repository.WaiterRepository;

@Service
@Transactional
public class WaiterServiceImpl implements WaiterService {

	@Autowired
	private WaiterRepository waiterRepository; 
	@Override
	public List<Waiter> findAll() {
		// TODO Auto-generated method stub
		
		return (List<Waiter>) waiterRepository.findAll();
	}

	@Override
	public Waiter findByEmail(String email) {
		// TODO Auto-generated method stub
		return waiterRepository.findByEmail(email);
	}

	@Override
	public Waiter findOne(Long id) {
		// TODO Auto-generated method stub
		
		return waiterRepository.findOne(id);
	}

	@Override
	public Waiter save(Waiter w) {
		// TODO Auto-generated method stub
		return waiterRepository.save(w);
	}

	@Override
	public void delete(Waiter w) {
		// TODO Auto-generated method stub
		waiterRepository.delete(w.getId());
	}

	@Override
	public Waiter update(Waiter w) {
		// TODO Auto-generated method stub
		Waiter www = waiterRepository.findByEmail(w.getEmail());
		www.setPassword(w.getPassword());
		return waiterRepository.save(www);
		
	}

}

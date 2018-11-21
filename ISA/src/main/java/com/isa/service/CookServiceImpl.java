package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Cook;

import com.isa.repository.CookRepository;
@Service
@Transactional
public class CookServiceImpl implements CookService {
	@Autowired
	private CookRepository cookRepository; 
	@Override
	public List<Cook> findAll() {
		// TODO Auto-generated method stub
		
		return (List<Cook>) cookRepository.findAll();
	}

	@Override
	public Cook findByEmail(String email) {
		// TODO Auto-generated method stub
		return cookRepository.findByEmail(email);
	}

	@Override
	public Cook findOne(Long id) {
		// TODO Auto-generated method stub
		
		return cookRepository.findOne(id);
	}

	@Override
	public Cook save(Cook w) {
		// TODO Auto-generated method stub
		return cookRepository.save(w);
	}

	@Override
	public void delete(Cook w) {
		// TODO Auto-generated method stub
		cookRepository.delete(w.getId());
	}

	@Override
	public Cook update(Cook w) {
		// TODO Auto-generated method stub
		Cook www = cookRepository.findByEmail(w.getEmail());
		www.setPassword(w.getPassword());
		return cookRepository.save(www);
		
	}

}

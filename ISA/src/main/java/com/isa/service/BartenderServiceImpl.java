package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Bartender;

import com.isa.repository.BartenderRepository;

@Service
@Transactional
public class BartenderServiceImpl implements BartenderService {

	@Autowired
	private BartenderRepository bartenderRepository;
	
	@Override
	public List<Bartender> findAll() {
		// TODO Auto-generated method stub
		return (List<Bartender>) bartenderRepository.findAll();
	}

	@Override
	public Bartender findByEmail(String email) {
		// TODO Auto-generated method stub
		return bartenderRepository.findByEmail(email);
	}

	@Override
	public Bartender findOne(Long id) {
		// TODO Auto-generated method stub
		return bartenderRepository.findOne(id);
	}

	@Override
	public Bartender save(Bartender b) {
		// TODO Auto-generated method stub
		return bartenderRepository.save(b);
	}

	@Override
	public void delete(Bartender b) {
		// TODO Auto-generated method stub
		bartenderRepository.delete(b.getId());
	}

	@Override
	public Bartender update(Bartender b) {
		// TODO Auto-generated method stub
		Bartender bbb=bartenderRepository.findByEmail(b.getEmail());
		bbb.setPassword(b.getPassword());
		return bartenderRepository.save(bbb);
	}

}

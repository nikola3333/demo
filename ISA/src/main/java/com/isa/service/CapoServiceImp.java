package com.isa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.CapoDiTuttiCapi;
import com.isa.repository.CapoRepository;


@Service
@Transactional
public class CapoServiceImp implements CapoService
{

	private CapoRepository repository;
	
	@Autowired
	public CapoServiceImp(final CapoRepository repo) {
		repository = repo;
	}
	
	@Override
	public List<CapoDiTuttiCapi> findAll() {
		return (List<CapoDiTuttiCapi>) repository.findAll();
		
	}

	@Override
	public CapoDiTuttiCapi findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public CapoDiTuttiCapi findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public CapoDiTuttiCapi save(CapoDiTuttiCapi capo) {
		return repository.save(capo);
	}

	@Override
	public void delete(CapoDiTuttiCapi capo) {
		repository.delete(capo);
	}

}

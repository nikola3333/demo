package com.isa.service;

import java.util.List;

import com.isa.entity.CapoDiTuttiCapi;

public interface CapoService {

	List<CapoDiTuttiCapi> findAll();
	
	public CapoDiTuttiCapi findByEmail(String email);
	
	public CapoDiTuttiCapi findById(Long id);
	
	public CapoDiTuttiCapi save(CapoDiTuttiCapi guest);
	
	public void delete(CapoDiTuttiCapi guest);
}

package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.isa.entity.SystemManager;
import com.isa.repository.SystemManagerRepository;

@Service
@Transactional
public class SysManagerServiceImpl implements SysManagerService {

	@Autowired
	private SystemManagerRepository SmRep;
	@Override
	public SystemManager findByEmail(String email) {
		return SmRep.findByEmail(email);
	}

	@Override
	public SystemManager findOne(Long id) {
		// TODO Auto-generated method stub
		return SmRep.findOne(id);
	}

	@Override
	public SystemManager save(SystemManager sm) {		
		return SmRep.save(sm);
	}

	@Override
	public void delete(SystemManager sm) {
		// TODO Auto-generated method stub
		SmRep.delete(sm.getId());
	}

	@Override
	public SystemManager update(SystemManager sm) {
		SystemManager a = SmRep.findByEmail(sm.getEmail());
		a.setPassword(sm.getPassword());
		return SmRep.save(a);
	}

	@Override
	public List<SystemManager> findAll() {		
		return (List<SystemManager>) SmRep.findAll();
	}
	
	

}

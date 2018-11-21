package com.isa.service;

import java.util.List;

import com.isa.entity.SystemManager;

public interface SysManagerService {

	
	public SystemManager findByEmail(String email);
	
	public SystemManager findOne(Long id);
	
	public SystemManager save(SystemManager sm) ;
	
	public void delete(SystemManager sm);
	
	public SystemManager update(SystemManager sm);

	public List<SystemManager> findAll();

}

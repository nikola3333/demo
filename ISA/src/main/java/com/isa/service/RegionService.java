package com.isa.service;

import java.util.List;

import com.isa.entity.Region;

public interface RegionService {

	public List<Region> findAll();
	public Region findOne(Long id);
	public Region save(Region r);
	public void delete(Region r);
	public Region update(Region r);
	
}

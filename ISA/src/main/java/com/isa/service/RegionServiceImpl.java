package com.isa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.Region;
import com.isa.repository.RegionRepository;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{
	
	@Autowired
	private RegionRepository regionRepository;

	@Override
	public List<Region> findAll() {
		
		return (List<Region>) regionRepository.findAll();
	}

	@Override
	public Region findOne(Long id) {
		// TODO Auto-generated method stub
		return regionRepository.findOne(id);
	}

	@Override
	public Region save(Region r) {
		// TODO Auto-generated method stub
		return regionRepository.save(r);
	}

	@Override
	public void delete(Region r) {
		regionRepository.delete(r);
	}

	@Override
	public Region update(Region r) {
		// TODO Auto-generated method stub
		return regionRepository.save(r);
	}

}

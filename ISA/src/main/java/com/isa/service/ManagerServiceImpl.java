package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.isa.entity.RestauranManager;
import com.isa.repository.RestaurantManagerRepository;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private RestaurantManagerRepository managerRepository;

	@Override
	public List<RestauranManager> findAll() {

		return (List<RestauranManager>) managerRepository.findAll();
	}

	@Override
	public RestauranManager findByEmail(String email) {
		return managerRepository.findByEmail(email);
	}

	@Override
	public RestauranManager save(RestauranManager guest) {
		return managerRepository.save(guest);
	}

	@Override
	public void delete(RestauranManager guest) {
		managerRepository.delete(guest.getId());
	}
	@Override
	public RestauranManager findOne(Long id) {
		return managerRepository.findOne(id);
	}
	@Override
	public void confirmRegistration(Long id) {
		RestauranManager g = managerRepository.findOne(id);
		g.setConfirmedRegistration(true);
		managerRepository.save(g);
	}
	@Override
	public RestauranManager update(RestauranManager guest) {
		RestauranManager g = managerRepository.findByEmail(guest.getEmail());
		g.setPassword(guest.getPassword());
		return managerRepository.save(g);
	}
}

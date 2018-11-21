package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Guest;
import com.isa.entity.RestauranManager;
import com.isa.entity.User;
import com.isa.exceptions.AccountActivationException;
import com.isa.exceptions.BadCredentialException;
import com.isa.exceptions.UserNotFoundException;
import com.isa.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserByEmail(String email, String password) {
		
		if(userRepository.findByEmail(email) == null) throw new UserNotFoundException(email);
		if(!userRepository.findByEmail(email).getPassword().equals(password)) throw new BadCredentialException();
			if(userRepository.findByEmail(email).getRole().getName().toLowerCase().equals("guest") ){
				Guest guest = (Guest) userRepository.findByEmail(email);
				if(!guest.isConfirmedRegistration()) throw new AccountActivationException();
			}
			if(userRepository.findByEmail(email).getRole().getName().toLowerCase().equals("restauran_manager") ){
				RestauranManager guest = (RestauranManager) userRepository.findByEmail(email);
				if(!guest.isConfirmedRegistration()) throw new AccountActivationException();
			}
		return userRepository.findByEmail(email);
		
	}
	
}

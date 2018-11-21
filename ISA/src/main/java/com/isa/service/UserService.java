package com.isa.service;

import com.isa.entity.User;

public interface UserService {

	public User findUserByEmail(String email, String password);
}

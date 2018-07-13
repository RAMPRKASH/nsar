package com.nova.nsar.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.nsar.repository.jpa.UserJpaRepository;
import com.nova.nsar.repository.jpa.entity.UserEntity;
import com.nova.nsar.service.UserService;
import com.nova.nsar.web.bean.UserForm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserJpaRepository userRepo;

	@Override
	public UserEntity findUserById(Long id) {

		Optional<UserEntity> user = userRepo.findById(id);
		return user.orElse(null);
	}

	@Override
	public Boolean addUser(UserForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(UserForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean registerUser(UserForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean authenticateUser(UserForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

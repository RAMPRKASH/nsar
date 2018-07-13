package com.nova.nsar.service;

import com.nova.nsar.repository.jpa.entity.UserEntity;
import com.nova.nsar.web.bean.UserForm;

public interface UserService {

	UserEntity findUserById(Long id);
	
	Boolean addUser(UserForm form);
	
	Boolean updateUser(UserForm form);

	Boolean registerUser(UserForm form);
	
	Boolean authenticateUser(UserForm form);
	
}

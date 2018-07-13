package com.nova.nsar.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nova.nsar.service.impl.UserServiceImpl;
import com.nova.nsar.web.bean.UserForm;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/add/form")
	public void addUser(@RequestBody UserForm form) {
	}

	@PostMapping("/update/form")
	public void updateUser(@RequestBody UserForm form) {
	}

	@GetMapping("/id/{userId}")
	public void getUser(@PathVariable long userId) {
		System.out.println("UserController | get User" + userId);
	}
}
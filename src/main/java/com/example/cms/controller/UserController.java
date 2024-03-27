package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestDTOs.UserRequest;
import com.example.cms.responseDTOs.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest request){
		return service.saveUser(request);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<ResponseStructure<UserResponse>> loginUser(@RequestBody UserRequest request){
		return service.loginUser(request);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		return service.deleteUser(userId);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable int userId){
		return service.findUser(userId);
	}
	
}

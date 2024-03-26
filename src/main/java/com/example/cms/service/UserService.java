package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestDTOs.UserRequest;
import com.example.cms.responseDTOs.UserResponse;
import com.example.cms.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest request);

	ResponseEntity<ResponseStructure<UserResponse>> loginUser(UserRequest request);

}

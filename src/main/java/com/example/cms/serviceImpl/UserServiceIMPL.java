package com.example.cms.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.User;
import com.example.cms.exception.EmailAlreadyExistsException;
import com.example.cms.exception.InvalidEmailException;
import com.example.cms.exception.InvalidPasswordException;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestDTOs.UserRequest;
import com.example.cms.responseDTOs.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.util.ResponseStructure;

@Service
public class UserServiceIMPL implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ResponseStructure<UserResponse> structure;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest request) {

		if (repository.existsByEmail(request.getEmail())) {
	        throw new EmailAlreadyExistsException("Email is already registered.");
	    }

	    User user = repository.save(maptoUser(request));
		
		return ResponseEntity.ok(structure
				.setStatus(HttpStatus.OK.value())
				.setMessage("User Registration Is Successfully!!")
				.setData(mapToUserResponse(user)));
	}
	
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> loginUser(UserRequest request) {
		
		 Optional<User> optionalUser = repository.findByEmail(request.getEmail());

		    if (optionalUser.isPresent()) {
		        User user = optionalUser.get();
		        if (request.getPassword().equals(user.getPassword())) {
		            return ResponseEntity.ok(structure
		                    .setStatus(HttpStatus.OK.value())
		                    .setMessage("Login successful")
		                    .setData(mapToUserResponse(user)));
		        } else {
		            throw new InvalidPasswordException("Invalid password");
		        }
		    } else {
		        throw new InvalidEmailException("Invalid email");
		    }
//		return null;
	}
	
	
	private User maptoUser(UserRequest request) {
		return User.builder()
				.email(request.getEmail())
				.password(encoder.encode(request.getPassword()))
				.username(request.getEmail().split("@")[0])
				.build();

	}	
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.build();
	}


}

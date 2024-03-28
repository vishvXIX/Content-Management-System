package com.example.cms.requestDTOs;

import java.util.List;

import com.example.cms.entity.User;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {

	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Only Contains Alphabets.")
	private String title;
	private String[] topics;
	private String about;
	
	private List<User> users;
	
}

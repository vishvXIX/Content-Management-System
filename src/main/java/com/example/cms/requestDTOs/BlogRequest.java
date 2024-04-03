package com.example.cms.requestDTOs;

import com.example.cms.entity.User;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogRequest {

	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Only Contains Alphabets.")
	private String title;
	private String[] topics;
	private String about;
	
	private User users;
	
}

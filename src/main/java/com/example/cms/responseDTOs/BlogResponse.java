package com.example.cms.responseDTOs;

import java.util.List;

import com.example.cms.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogResponse {

	private int blogId;
	private String title;
	private String[] topics;
	private String about;
	
	private List<User> users;
	
}

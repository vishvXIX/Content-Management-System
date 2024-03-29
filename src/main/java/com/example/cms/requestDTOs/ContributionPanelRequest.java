package com.example.cms.requestDTOs;

import java.util.List;

import com.example.cms.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContributionPanelRequest {
	
	private List<User> users;
	
}

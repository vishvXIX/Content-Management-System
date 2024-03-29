package com.example.cms.responseDTOs;

import java.util.List;

import com.example.cms.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContributionPanelResponse {

	private int panelId;
	private List<User> users;
	
}

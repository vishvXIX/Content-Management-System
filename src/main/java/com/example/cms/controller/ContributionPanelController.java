package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.responseDTOs.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.util.ResponseStructure;

@RestController
public class ContributionPanelController {

	@Autowired
	private ContributionPanelService service;
	
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(@PathVariable int userId, @PathVariable int panelId){
		return service.addContributor(userId,panelId);
	}

	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(@PathVariable int userId, @PathVariable int panelId){
		return service.deleteContributor(userId,panelId);
	}
	
}

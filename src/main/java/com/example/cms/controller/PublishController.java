package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestDTOs.PublishRequest;
import com.example.cms.responseDTOs.PublishResponse;
import com.example.cms.service.PublishService;
import com.example.cms.util.ResponseStructure;

@RestController
public class PublishController {

	@Autowired
	private PublishService service;
	
	@PostMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<ResponseStructure<PublishResponse>> createPublish(@PathVariable int postId, @RequestBody PublishRequest request){
		return service.createPublish(postId,request);
	}
	
}

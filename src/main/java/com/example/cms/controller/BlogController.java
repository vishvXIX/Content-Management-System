package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.cms.requestDTOs.BlogRequest;
import com.example.cms.responseDTOs.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class BlogController {

	@Autowired
	private BlogService service;
	
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> saveBlog(@PathVariable int userId, @RequestBody @Valid BlogRequest request){
		return service.saveBlog(userId,request);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlog(@PathVariable int blogId){
		return service.findBlog(blogId);
	}
	
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(@PathVariable int blogId, @RequestBody BlogRequest request){
		return service.updateBlog(blogId,request);
	}
	
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<Boolean> checkTitle(@PathVariable String title){
		return service.checkTitle(title);
	}
	
}

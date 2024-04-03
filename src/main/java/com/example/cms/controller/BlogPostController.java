package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestDTOs.BlogPostRequest;
import com.example.cms.responseDTOs.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.util.ResponseStructure;

@RestController
public class BlogPostController {

	@Autowired
	private BlogPostService service;
	
	@PostMapping("/blogs/{blogId}/blog-posts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPost(@PathVariable int blogId, @RequestBody BlogPostRequest request){
		return service.createBlogPost(blogId,request);
	}
	
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> UpdateBlogPost(@PathVariable int postId, @RequestBody BlogPostRequest request){
		return service.UpdateBlogPost(postId,request);
	}
	
	@DeleteMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(@PathVariable int postId){
		return service.deleteBlogPost(postId);
	}
	
	@GetMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> findBlogPost(@PathVariable int postId){
		return service.findBlogPost(postId);
	}
	
}

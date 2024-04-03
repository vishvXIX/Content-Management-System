package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestDTOs.BlogPostRequest;
import com.example.cms.responseDTOs.BlogPostResponse;
import com.example.cms.util.ResponseStructure;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPost(int blogId, BlogPostRequest request);

	ResponseEntity<ResponseStructure<BlogPostResponse>> UpdateBlogPost(int postId, BlogPostRequest request);

	ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> findBlogPost(int postId);

}

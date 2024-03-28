package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestDTOs.BlogRequest;
import com.example.cms.responseDTOs.BlogResponse;
import com.example.cms.util.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> saveBlog(int userId, BlogRequest request);

	ResponseEntity<ResponseStructure<BlogResponse>> findBlog(int blogId);

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId, BlogRequest request);

	ResponseEntity<Boolean> checkTitle(String title);

}

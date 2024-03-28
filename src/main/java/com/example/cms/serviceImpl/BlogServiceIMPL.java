package com.example.cms.serviceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blog;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TitleNotAvailableException;
import com.example.cms.exception.TopicsNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestDTOs.BlogRequest;
import com.example.cms.responseDTOs.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.util.ResponseStructure;

@Service
public class BlogServiceIMPL implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseStructure<BlogResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> saveBlog(int userId, BlogRequest request) {

		return userRepository.findById(userId).map(u ->{

			if(blogRepository.existsByTitle(request.getTitle())) {
				throw new TitleNotAvailableException("Failed to create blog");
			}

			if(request.getTopics().length<1) {
				throw new TopicsNotSpecifiedException("Failed to create blog");
			}

			Blog blog =mapToBlog(request);
			blog.setUsers(Arrays.asList(u));
			blogRepository.save(blog);

			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog Saved Successfully!!")
					.setData(mapToBlogResponse(blog)));

		}) 
				.orElseThrow(()-> new UserNotFoundByIdException("User Not Found By Id."));

	}	

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlog(int blogId) {

		return blogRepository.findById(blogId).map(b -> {

			return ResponseEntity.ok(structure
					.setStatus(HttpStatus.OK.value())
					.setMessage("Blogs Fatched Successfully!!.")
					.setData(mapToBlogResponse(b)));

		})
				.orElseThrow(()-> new BlogNotFoundByIdException("Blog Not Found By Id."));

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId,BlogRequest request) {

		return blogRepository.findById(blogId).map(existingBlog -> {
			Blog updatedBlog = mapToBlog(request);
			updatedBlog.setBlogId(blogId);
			blogRepository.save(updatedBlog);

			return ResponseEntity.ok(structure
					.setStatus(HttpStatus.OK.value())
					.setMessage("Blogs Updated Successfully!!.")
					.setData(mapToBlogResponse(updatedBlog)));

		})
				.orElseThrow(()-> new BlogNotFoundByIdException("Blog Not Found By Id."));
				
	}
	
	@Override
	public ResponseEntity<Boolean> checkTitle(String title) {
		
		   boolean blogExists = blogRepository.existsByTitle(title);
	        return ResponseEntity.ok(blogExists);
		
	}



	private Blog mapToBlog(BlogRequest request) {

		return Blog.builder()
				.title(request.getTitle())
				.topics(request.getTopics())
				.about(request.getAbout())
				.build();

	}

	private BlogResponse mapToBlogResponse(Blog blog) {

		return BlogResponse.builder()
				.blogId(blog.getBlogId())
				.title(blog.getTitle())
				.topics(blog.getTopics())
				.about(blog.getAbout())
				.users(blog.getUsers())
				.build();

	}


}

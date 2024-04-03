package com.example.cms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.ContributionPanelNotFoundException;
import com.example.cms.exception.TitleNotAvailableException;
import com.example.cms.exception.TopicsNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContribustionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestDTOs.BlogPostRequest;
import com.example.cms.responseDTOs.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.util.ResponseStructure;

@Service
public class BlogPostServiceIMPL implements BlogPostService {

	@Autowired
	private BlogPostRepository blogPostRepository;
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private ContribustionPanelRepository contribustionPanelRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ResponseStructure<BlogPostResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPost(int blogId, BlogPostRequest request) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(user -> {

			return blogRepository.findById(blogId).map(blog -> {

				if(!blog.getUsers().getEmail().equals(email) && !contribustionPanelRepository.existsByPanelIdAndUsers(blog.getContributionPanel().getPanelId(),user))
					throw new UserNotFoundByIdException("user not found.");
				if(request.getTitle()==null){
					throw new TitleNotAvailableException("Failed to create blogPost");
				}
				if(request.getSummary() == null) {
					throw new TopicsNotSpecifiedException("Failed to create blogPost");
				}
				BlogPost post = blogPostRepository.save(mapToBlogPost(request));

				return ResponseEntity.ok(structure
						.setStatus(HttpStatus.OK.value())
						.setMessage("BlogPost Saved Successfully!!")
						.setData(mapToBlogPostResponse(post)));

			}).orElseThrow(()-> new BlogNotFoundByIdException("Blog Not Found."));
			
		}).orElseThrow(() -> new UserNotFoundByIdException("User not found."));

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> UpdateBlogPost(int postId, BlogPostRequest request) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(user -> {

			return blogRepository.findById(postId).map(blog -> {

				if(!blog.getUsers().getEmail().equals(email) && ! contribustionPanelRepository.existsByPanelIdAndUsers(blog.getContributionPanel().getPanelId(),user))
					throw new UserNotFoundByIdException("");
				return blogPostRepository.findById(postId).map(post -> {
					BlogPost updatedBlogPost = mapToBlogPost(request);
					updatedBlogPost.setPostId(postId);
					blogPostRepository.save(updatedBlogPost);
					return ResponseEntity.ok(structure
							.setStatus(HttpStatus.OK.value())
							.setMessage("BlogPost Updated Successfully.")
							.setData(mapToBlogPostResponse(updatedBlogPost)));
				}).orElseThrow(() -> new BlogPostNotFoundByIdException("BlogPost not found by Id."));
			}).orElseThrow(() -> new ContributionPanelNotFoundException("Contribution Panel not found."));
		}).get();
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return blogPostRepository.findById(postId).map(post -> {

			return userRepository.findByEmail(email).map(user -> {

				if(!user.getEmail().equals(email))
					throw new UserNotFoundByIdException("User not found.");
				blogPostRepository.delete(post);

				return ResponseEntity.ok(structure
						.setStatus(HttpStatus.OK.value())
						.setMessage("Blog Post deleted Successfully!!")
						.setData(mapToBlogPostResponse(post)));

			}).orElseThrow(()-> new UserNotFoundByIdException("User not found"));
			
		}).orElseThrow(()->new BlogPostNotFoundByIdException("BlogPost Not Found by Id"));

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> findBlogPost(int postId) {
	    return blogPostRepository.findByPostIdAndPostType(postId, PostType.PUBLISHED)
	            .map(post -> {
	            	return ResponseEntity.ok(structure
	            			.setStatus(HttpStatus.FOUND.value())
	            			.setMessage("Data found successfully.")
	            			.setData(mapToBlogPostResponse(post)));
	            }).orElseThrow(()-> new BlogPostNotFoundByIdException("Blog post not found."));
	}


	private BlogPost mapToBlogPost(BlogPostRequest request) {
		return BlogPost.builder()
				.title(request.getTitle())
				.subTitle(request.getSubTitle())
				.postType(PostType.DRAFT)
				.summary(request.getSummary())
				.build();		

	}

	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
	    return BlogPostResponse.builder()
	            .postId(blogPost.getPostId())
	            .title(blogPost.getTitle())
	            .postType(blogPost.getPostType())
	            .subTitle(blogPost.getSubTitle())
	            .summary(blogPost.getSummary())
	            .blog(blogPost.getBlog())
//	            .publishResponse(blogPost.getPublish())
	            .build();
	}

	
//	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
//	    PublishResponse publishResponse = convertToPublishResponse(blogPost.getPublish());
//
//	    return BlogPostResponse.builder()
//	            .postId(blogPost.getPostId())
//	            .title(blogPost.getTitle())
//	            .postType(blogPost.getPostType())
//	            .subTitle(blogPost.getSubTitle())
//	            .summary(blogPost.getSummary())
//	            .blog(blogPost.getBlog())
//	            .publishResponse(publishResponse)
//	            .build();
//	}
//
//	private PublishResponse convertToPublishResponse(Publish publish) {
//	   return PublishResponse.builder()
//	    
//	    .seoTitle(publish.getSeoTitle())
//	    .seoDescription(publish.getSeoDescription())
//	    .seoTags(publish.getSeoTags())
//	    .build();
//	   
//	}





}

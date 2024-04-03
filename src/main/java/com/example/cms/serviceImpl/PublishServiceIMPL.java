package com.example.cms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Publish;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.PublishRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestDTOs.PublishRequest;
import com.example.cms.responseDTOs.PublishResponse;
import com.example.cms.service.PublishService;
import com.example.cms.util.ResponseStructure;

@Service
public class PublishServiceIMPL implements PublishService {

	@Autowired
	private PublishRepository publishRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogPostRepository blogPostRepository;

	@Autowired
	private ResponseStructure<PublishResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<PublishResponse>> createPublish(int postId, PublishRequest request) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(user -> {
			return blogPostRepository.findById(postId).map(post -> {

//				if(post.getPostType().PUBLISHED.equals(PostType.PUBLISHED)) {
//					throw new IllagalAccessRequestException("Failed to publish");
//				}
//				if (!user.getEmail().equals(email)) {
//					throw new UserNotFoundByIdException("User not found.");
//				}
//				if (request.getSeoTitle() == null) {
//					throw new IllagalAccessRequestException("Failed to publish");
//				}

				Publish publish = publishRepository.save(mapToPublish(request));

				return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
						.setMessage("Published Successfully")
						.setData(mapToPublishResponse(publish)));
				
			}).orElseThrow(() -> new BlogPostNotFoundByIdException("Blog post not found."));
			
		}).orElseThrow(() -> new UserNotFoundByIdException("User not found."));
	}


	private Publish mapToPublish(PublishRequest request) {

		return Publish.builder()
				.seoTitle(request.getSeoTitle())
				.seoDescription(request.getSeoDescription())
				.seoTags(request.getSeoTags())
				.build();
		
	}

	private PublishResponse mapToPublishResponse(Publish publish) {

		return PublishResponse.builder()
				.publishId(publish.getPublishId())
				.seoTitle(publish.getSeoTitle())
				.seoDescription(publish.getSeoDescription())
				.seoTags(publish.getSeoTags())
				.build();
	}

}

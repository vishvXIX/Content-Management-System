package com.example.cms.responseDTOs;

import com.example.cms.entity.Blog;
import com.example.cms.enums.PostType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlogPostResponse {

	private int postId;
	private String title;
	private String subTitle;
	private PostType postType;
	private String summary;
	
	private Blog blog;
	private PublishResponse publishResponse;
	
}

package com.example.cms.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishRequest {

	private String seoTitle;
	private String seoDescription;
	private String[] seoTags;
	
}

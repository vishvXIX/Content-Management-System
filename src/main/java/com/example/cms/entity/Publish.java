package com.example.cms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Publish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publishId;
	private String seoTitle;
	private String seoDescription;
	private String[] seoTags;
	
	@OneToOne(mappedBy = "publish")
	private BlogPost blogPost;
	
	@OneToOne
	private Schedule schedule;
	
}

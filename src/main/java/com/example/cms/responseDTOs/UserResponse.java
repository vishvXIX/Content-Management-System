package com.example.cms.responseDTOs;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

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
public class UserResponse {

	private int userId;
	private String username;
	private String email;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	
}

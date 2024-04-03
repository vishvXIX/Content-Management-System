package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestDTOs.PublishRequest;
import com.example.cms.responseDTOs.PublishResponse;
import com.example.cms.util.ResponseStructure;

public interface PublishService {

	ResponseEntity<ResponseStructure<PublishResponse>> createPublish(int postId, PublishRequest request);

}

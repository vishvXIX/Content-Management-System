package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.responseDTOs.ContributionPanelResponse;
import com.example.cms.util.ResponseStructure;

public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId);

	ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(int userId, int panelId);

}

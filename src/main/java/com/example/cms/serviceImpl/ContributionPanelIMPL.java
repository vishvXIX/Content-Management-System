package com.example.cms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.exception.IllagalAccessRequestException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContribustionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.responseDTOs.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.util.ResponseStructure;

@Service
public class ContributionPanelIMPL implements ContributionPanelService {

	@Autowired
	private ContribustionPanelRepository contribustionPanelRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ResponseStructure<ContributionPanelResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByEmail(email).map(owner -> {	
			return contribustionPanelRepository.findById(panelId).map(panel -> {
//				if(blogRepository.existsByUsersAndContributionPanel(owner,panel)) {
//					throw new IllagalAccessRequestException("failed to add contributor");
//				}
				return userRepository.findById(userId).map(contributor -> {
					panel.getUsers().add(contributor);
					contribustionPanelRepository.save(panel);

					return ResponseEntity.ok(structure
							.setStatus(HttpStatus.OK.value())
							.setMessage("Contributor added Successfully!!.")
							.setData(mapToContributeREsponse(panel)));

				}).orElseThrow(()-> new UserNotFoundByIdException("User Not Found By Id."));
			}).orElseThrow(()-> new UserNotFoundByIdException("User Not Found By Id."));
		}).get();
	}
	
	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(int userId, int panelId) {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();

	    return userRepository.findByEmail(email).map(owner -> {
	        return contribustionPanelRepository.findById(panelId).map(panel -> {
	            if (!blogRepository.existsByUsersAndContributionPanel(owner, panel)) {
	                throw new IllagalAccessRequestException("Only the blog owner can remove contributors.");
	            }
	            return userRepository.findById(userId).map(contributor -> {
	                if (panel.getUsers().contains(contributor)) {
	                    panel.getUsers().remove(contributor);
	                    contribustionPanelRepository.save(panel);
	                    return ResponseEntity.ok(structure
	                            .setStatus(HttpStatus.OK.value())
	                            .setMessage("User removed from contribution panel successfully.")
	                            .setData(mapToContributeREsponse(panel)));
	                } else {
	                    throw new UserNotFoundByIdException("User is not a contributor to this panel.");
	                }
	            }).orElseThrow(() -> new UserNotFoundByIdException("User not found by ID."));
	        }).orElseThrow(() -> new IllagalAccessRequestException("Contribution panel not found by ID."));
	    }).orElseThrow(() -> new UserNotFoundByIdException("Owner not found for the authenticated user."));
	}

	
	private ContributionPanelResponse mapToContributeREsponse(ContributionPanel contributionPanel) {
		return ContributionPanelResponse.builder()
				.users(contributionPanel.getUsers())
				.build();
	}

	

}

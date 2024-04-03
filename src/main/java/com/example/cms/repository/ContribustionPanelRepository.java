package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.entity.Blog;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;

@Repository
public interface ContribustionPanelRepository extends JpaRepository<ContributionPanel, Integer> {

	Optional<Blog> existsByUsers(User owner);

	Optional<Blog> findByUsers(User owner);

	boolean existsByPanelIdAndUsers(int panelId, User user);

}

package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.entity.ContributionPanel;

@Repository
public interface ContribustionPanelRepository extends JpaRepository<ContributionPanel, Integer> {

}

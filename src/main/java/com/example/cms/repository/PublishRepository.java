package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.entity.Publish;

@Repository
public interface PublishRepository extends JpaRepository<Publish, Integer> {

}

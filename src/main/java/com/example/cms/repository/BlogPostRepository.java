package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

	boolean existsByTitle(String title);

	Optional<BlogPost> findByPostIdAndPostType(int postId, PostType published);

}

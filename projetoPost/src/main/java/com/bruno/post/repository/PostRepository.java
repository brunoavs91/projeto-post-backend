package com.bruno.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.post.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}

package com.bruno.post.service;

import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.dto.PostDTO;

public interface PostService {

	/**
	 * inserindo Post
	 * @param postDTO
	 * @param multipartFile
	 */
	public void insert(PostDTO postDTO, MultipartFile file);
}

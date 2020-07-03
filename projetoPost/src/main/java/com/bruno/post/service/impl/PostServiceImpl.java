package com.bruno.post.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.domain.Post;
import com.bruno.post.dto.PostDTO;
import com.bruno.post.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	
//	public Post convertFromPost (PostDTO postDTO) {
//		Post post = new Post();
//		post.setId(postDTO.getId());
//		post.setComentario(postDTO.getComentario());
//		post.setImagem(postDTO.getImagem());
//		
//		return null;
//	}
	
	public void saveImage (MultipartFile multipartFile) {
		try {
			byte[] bytes = multipartFile.getBytes();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}

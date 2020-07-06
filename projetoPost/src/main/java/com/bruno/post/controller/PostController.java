package com.bruno.post.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.dto.PostDTO;
import com.bruno.post.service.ImageService;
import com.bruno.post.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/posts")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "post") String post) throws IOException {


		PostDTO postDTO = new ObjectMapper().readValue(post, PostDTO.class);
		
//	byte[] imagem=imageService.converterArquivoUpload(file);

	postService.insert(postDTO,file);
		return ResponseEntity.ok().build();
	}

	
}

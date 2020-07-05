package com.bruno.post.controller;

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

@RestController
@RequestMapping(value="/posts")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
	byte[] imagem=imageService.converterArquivoUpload(file);
	PostDTO post = new PostDTO();
	post.setComentario("foto teste");
//	post.setImagem(imagem);
	post.setEmail("brunoav91@gmail.com");

//	postService.insert(post, file);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestBody PostDTO postDTIO) {

		postService.insert(postDTIO);
		return ResponseEntity.ok().build();
	}
	
}

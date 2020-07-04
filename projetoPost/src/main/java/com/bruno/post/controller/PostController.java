package com.bruno.post.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostController {

	@Autowired
	PostService PostService;
	
	@RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {

		URI uri = clienteService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}

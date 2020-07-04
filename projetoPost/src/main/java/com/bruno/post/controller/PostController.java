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
import com.bruno.post.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file,
			@RequestBody PostDTO postDTO) {

		postService.insert(postDTO, file);
		return ResponseEntity.ok().build();
	}
}

package com.bruno.post.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String email;
	
	private List<PostDTO> postsDTO;
}

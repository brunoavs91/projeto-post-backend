package com.bruno.post.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String comentario;
	
	private String imagem;
	
	private Long idUsuario;
	
	private String email;
}

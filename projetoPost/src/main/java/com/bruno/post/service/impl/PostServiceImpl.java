package com.bruno.post.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.domain.Post;
import com.bruno.post.dto.PostDTO;
import com.bruno.post.exception.AuthorizationException;
import com.bruno.post.repository.UsuarioRepository;
import com.bruno.post.security.UserSS;
import com.bruno.post.service.ImageService;
import com.bruno.post.service.PostService;
import com.bruno.post.service.UsuarioService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UsuarioService usuarioService;

	

	public void insert(PostDTO postDTO, MultipartFile multipartFile) {
		
		
	}
	
	public void uploadImagemPost(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
	
	byte[] byteImagem =	imageService.converterArquivoUpload(multipartFile);
	
	//implementar o restante
	
	}
	
	public Post fromPost(PostDTO postDTO) {
		Post post = new Post();
		post.setId(postDTO.getId());
		post.setComentario(postDTO.getComentario());
		post.setImagem(postDTO.getImagem());

		post.setUsuario(usuarioService.findById(postDTO.getId()));

		return post;
	}
	
	public PostDTO fromDTO(Post post) {
		PostDTO postDTO = new PostDTO();

		postDTO.setId(post.getId());
		postDTO.setComentario(post.getComentario());
		postDTO.setIdUsuario(post.getUsuario() != null ? post.getUsuario().getId() : null);

		return postDTO;

	}

}

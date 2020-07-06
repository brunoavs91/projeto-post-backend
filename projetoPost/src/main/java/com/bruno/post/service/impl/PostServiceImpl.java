package com.bruno.post.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.post.domain.Post;
import com.bruno.post.domain.Usuario;
import com.bruno.post.dto.PostDTO;
import com.bruno.post.exception.AuthorizationException;
import com.bruno.post.exception.DataIntegrityException;
import com.bruno.post.exception.FileException;
import com.bruno.post.exception.ObjectNotFoundException;
import com.bruno.post.repository.PostRepository;
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
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void insert(PostDTO postDTO, MultipartFile file) {
		
		String extensao = FilenameUtils.getExtension(file.getOriginalFilename());
		postDTO.setFileName(file.getOriginalFilename());

		BufferedImage img = ImageServiceImpl.getJpgImageFromFile(file);
		postDTO.setImagem(ImageServiceImpl.getInputStream(img, extensao));
		

		Post post = fromPost(postDTO);

		postRepository.save(post);

	}

	public List<PostDTO> getAll() {
		List<Post> posts = postRepository.findAll();
		if (posts.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum post foi encontrado");
		}

		return posts.stream().map(p -> fromDTO(p)).collect(Collectors.toList());
	}

	private Usuario buscarUsuarioByPost(String email) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		if (user.getUsername().equals(email)) {
			return usuarioService.findByEmail(email);
		}
		throw new DataIntegrityException("Usuario logado nao e o dono do Post");
	}
	
	public byte[] uploadImagemPost(MultipartFile multipartFile) {

		return imageService.converterArquivoUpload(multipartFile);

		// implementar o restante

	}
	
	public Post fromPost(PostDTO postDTO) {
		Post post = new Post();
		post.setId(postDTO.getId());
		post.setComentario(postDTO.getComentario());
		post.setImagem(post.getImagem());
		post.setFileName(postDTO.getFileName());
		post.setUsuario(buscarUsuarioByPost(postDTO.getEmail()));

		return post;
	}
	
	public PostDTO fromDTO(Post post) {
		PostDTO postDTO = new PostDTO();

		postDTO.setId(post.getId());
		postDTO.setComentario(post.getComentario());
		postDTO.setIdUsuario(post.getUsuario() != null ? post.getUsuario().getId() : null);
		postDTO.setImagem(post.getImagem());
		postDTO.setFileName(post.getFileName());
		return postDTO;

	}

}

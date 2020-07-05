package com.bruno.post.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.post.config.SecurityConfig;
import com.bruno.post.domain.Post;
import com.bruno.post.domain.Usuario;
import com.bruno.post.dto.PostDTO;
import com.bruno.post.dto.UsuarioDTO;
import com.bruno.post.enums.Perfil;
import com.bruno.post.exception.AuthorizationException;
import com.bruno.post.exception.DataIntegrityException;
import com.bruno.post.exception.ObjectNotFoundException;
import com.bruno.post.repository.UsuarioRepository;
import com.bruno.post.security.UserSS;
import com.bruno.post.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private final static Logger LOGGER = Logger.getLogger(UsuarioServiceImpl.class.getName());
	
	@Override
	public void insert(UsuarioDTO usuarioDTO) {
		Usuario usuario = fromUsuario(usuarioDTO);

		if (usuario != null) {
			usuario = usuarioRepository.save(usuario);
			LOGGER.log(Level.INFO, "Usuario criado com sucesso");

		}

	}
	
	@Override
	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();

		if (user == null || !user.hasHole(Perfil.ADMIN) && email.equals(user.getUsername())) {

			throw new AuthorizationException("Acesso negado");
		}

		return usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado:" + user.getId()));

	}


	@Override
	public List<UsuarioDTO> findAll() {
		// dpois converter DTO se precisar
		List<Usuario> listaUsuario = usuarioRepository.findAll();

		if (!listaUsuario.isEmpty()) {
			return listaUsuario.stream().map(u -> fromDTO(u)).collect(Collectors.toList());
		}
		LOGGER.log(Level.INFO, "Nenhum Usuario foi encontrado");
		return Collections.EMPTY_LIST;
	}

	@Override
	public Usuario findById(Long id) {

		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario não foi encontrado"));

		return usuario;
	}
	
	@Override
	public void deleteById(Long id) {

		Usuario usuario = findById(id);

		try {

			usuarioRepository.delete(usuario);
		} catch (DataIntegrityException ex) {
			throw new DataIntegrityException("Não e possivel exclui porque a propiedades  associadas");
		}
	}
	
	@Override
	public UsuarioDTO fromDTO(Usuario usuario) {
		if (usuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNome(usuario.getNome());
			usuarioDTO.setEmail(usuario.getEmail());
			usuarioDTO.setSenha(null);
			usuarioDTO.setPostsDTO(new ArrayList<>());

			if (usuario.getPosts() != null && !usuario.getPosts().isEmpty()) {
				usuario.getPosts().forEach(p -> {

					PostDTO postDTO = new PostDTO();
					postDTO.setId(p.getId());
					postDTO.setComentario(p.getComentario());
					postDTO.setIdUsuario(usuario.getId());
					postDTO.setImagem(null);
					usuarioDTO.getPostsDTO().add(postDTO);
				});
			}
			return usuarioDTO;
		}
		return null;
	}
	
	@Override
	public Usuario fromUsuario(UsuarioDTO usuarioDTO) {

		if (usuarioDTO != null) {

			Usuario usuario = new Usuario();
			usuario.setId(usuarioDTO.getId());
			usuario.setNome(usuarioDTO.getNome());
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setPerfis(new HashSet<>());
			//um mandar numero e pegar o perfil pelo numero
			usuario.addPerfil(Perfil.valueOf(usuarioDTO.getRole().toUpperCase()));
			usuario.setSenha(SecurityConfig.bCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
			usuario.setPosts(new ArrayList<>());

			if (usuarioDTO.getPostsDTO() != null && !usuarioDTO.getPostsDTO().isEmpty()) {

				usuarioDTO.getPostsDTO().forEach(p -> {
					Post post = new Post();
					post.setId(p.getId());
					post.setComentario(p.getComentario());
					post.setImagem(null);
					post.setUsuario(usuario);
					usuario.getPosts().add(post);
				});

			}
			return usuario;
		}
		return null;

	}
}

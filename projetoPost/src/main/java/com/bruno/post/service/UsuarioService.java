package com.bruno.post.service;

import java.util.List;

import com.bruno.post.domain.Usuario;
import com.bruno.post.dto.UsuarioDTO;
import com.bruno.post.repository.UsuarioRepository;

public interface UsuarioService {

	/**
	 * inserir usuario no banco
	 * @param usuarioDTO
	 */
	public void insert(UsuarioDTO usuarioDTO);
	
	/**
	 * Buscar todos usuarios
	 * @return
	 */
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email);
	
	public List<UsuarioDTO> findAll();
	
	/**
	 * Buscar usuario por id
	 * @param id
	 * @return
	 */
	public Usuario findById(Long id);
	
	/**
	 * Deletar usuario por id
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * Converter usuario para DTO
	 * @param usuario
	 * @return
	 */
	public UsuarioDTO fromDTO(Usuario usuario);
	
	/**
	 * converter UsuarioDTO para entity
	 * @param usuarioDTO
	 * @return
	 */
	public Usuario fromUsuario(UsuarioDTO usuarioDTO);
}

package com.bruno.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.post.domain.Usuario;
import com.bruno.post.dto.UsuarioDTO;
import com.bruno.post.service.UsuarioService;



@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);

		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UsuarioDTO usuarioDTO) {

		usuarioService.insert(usuarioDTO);

		return ResponseEntity.ok().build();
	}
	
	

	  
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		usuarioService.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	 
}

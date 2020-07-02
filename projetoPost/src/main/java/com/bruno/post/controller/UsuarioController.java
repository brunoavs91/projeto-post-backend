package com.bruno.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

	
//	@RequestMapping(value="/{id}",method=RequestMethod.GET)
//	public ResponseEntity<Categoria> find(@PathVariable Long id) {
//		Categoria categoria = categoriaService.find(id);
//		
//		return ResponseEntity.ok(categoria);
//	}
}

package com.bruno.post.service.impl;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bruno.post.config.SecurityConfig;

import com.bruno.post.domain.Usuario;
import com.bruno.post.enums.Perfil;
import com.bruno.post.repository.UsuarioRepository;
import com.bruno.post.service.DBService;

@Service
public class DBServiceImpl implements DBService {


	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void instantiateTestDataBase() throws Exception {

		Usuario user = new Usuario();
		user.setId(1L);
		user.setEmail("brunoav91@gmail.com");
		user.setNome("testeNome");
		user.setSenha(SecurityConfig.bCryptPasswordEncoder().encode("123"));
		user.setPerfis(new HashSet<>());
		user.addPerfil(Perfil.ADMIN);
		user.setPosts(new ArrayList<>());

		usuarioRepository.save(user);
	}
}

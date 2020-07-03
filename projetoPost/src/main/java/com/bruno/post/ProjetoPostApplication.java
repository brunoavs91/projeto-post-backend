package com.bruno.post;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.post.domain.Usuario;
import com.bruno.post.enums.Perfil;
import com.bruno.post.repository.UsuarioRepository;

@SpringBootApplication
public class ProjetoPostApplication implements CommandLineRunner {
	
	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 *
	 */
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// so para teste , qndo startar a app 
		
		Usuario user  = new Usuario();
		user.setId(1L);
		user.setEmail("teste@gmail.com");
		user.setNome("testeNome");
		user.setSenha("xx");
		user.setPerfis(new HashSet<>(Perfil.ADMIN.getCod()));
		user.setPosts(new ArrayList<>());
		
		usuarioRepository.save(user);
	}

}

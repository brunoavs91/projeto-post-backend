package com.bruno.post;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bruno.post.domain.Usuario;
import com.bruno.post.enums.Perfil;
import com.bruno.post.repository.UsuarioRepository;

@SpringBootApplication
public class ProjetoPostApplication implements CommandLineRunner {
	
	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 *
	 */
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// so para teste , qndo startar a app 

	}

}

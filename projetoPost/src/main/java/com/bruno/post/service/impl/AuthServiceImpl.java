package com.bruno.post.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bruno.post.domain.Usuario;
import com.bruno.post.exception.ObjectNotFoundException;
import com.bruno.post.repository.UsuarioRepository;
import com.bruno.post.service.AuthService;

public class AuthServiceImpl implements AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Random random = new Random();
	
	@Override
	public void sendNewPassword(String email) {

		Usuario usuario = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ObjectNotFoundException("Email não encontrado"));

		String newPass = newPassWord();
		usuario.setSenha(bCryptPasswordEncoder.encode(newPass));

		usuarioRepository.save(usuario);

	}

	private String newPassWord() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		// gera um digito baseado no unicode
		if (opt == 0) {
			// gerar um numero aletorio de 0 a 9 e somar com 48 codigo do 0
			return (char) (random.nextInt(10) + 48);
		}
		// gera letra maiuscula
		else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);

		} else {// letra minuscula
			return (char) (random.nextInt(26) + 97);
		}

	}
}

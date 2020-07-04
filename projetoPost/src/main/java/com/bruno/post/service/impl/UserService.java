package com.bruno.post.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bruno.post.security.UserSS;

public class UserService {

	//pega usuario autenticado
		public static UserSS authenticated() {
			try {

				return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception ex) {
				return null;
			}
		}
}

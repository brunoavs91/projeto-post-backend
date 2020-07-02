package com.bruno.post.enums;

import java.util.Objects;



public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil t : Perfil.values()) {
			if (Objects.equals(cod, t.getCod())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id invalido" + cod);
	}
}

package com.bruno.post.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERFIL")
@Data
@NoArgsConstructor
public class Perfil {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	@ManyToMany(mappedBy = "perfis")
	private Set<Usuario> usuarios;
}

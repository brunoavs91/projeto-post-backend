package com.bruno.post.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
//	@Column(name = "NOME")
	private String nome;
	
//	@Column(name = "EMAIL")
	private String email;
	
	@JsonIgnore
//	@Column(name = "SENHA")
	private String senha;
	
	@OneToMany(mappedBy = "usuario" , fetch = FetchType.LAZY)
	private List<Post> posts;
	
	@ManyToMany
	@JoinTable(name = "USUARIO_PERFIL_ROLE", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "perfil_id") })
	private Set<Perfil> perfis = new HashSet<>();
	
}

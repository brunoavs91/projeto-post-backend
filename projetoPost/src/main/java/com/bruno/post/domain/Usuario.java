package com.bruno.post.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bruno.post.enums.Perfil;
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
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="USUARIO_PERFIL_ROLE")
	private Set<Integer> perfis = new HashSet<>();
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "USUARIO_PERFIL_ROLE", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "perfil_id") })
//	private Set<Perfil> perfis = new HashSet<>();
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCod());
	}
	
}

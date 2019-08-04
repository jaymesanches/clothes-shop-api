package br.com.clothesshop.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "grupo_usuario", joinColumns = @JoinColumn(name = "id_grupo", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_ususario", referencedColumnName = "id"))
//	@JsonIgnoreProperties("usuarios")
//	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
	
	
}

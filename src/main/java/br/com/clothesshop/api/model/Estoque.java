package br.com.clothesshop.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Estoque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_produto", referencedColumnName = "id")
	@NotNull
	private Produto produto;

	@Column(name = "tamanho_pp")
	private int tamanhoPP;
	@Column(name = "tamanho_p")
	private int tamanhoP;
	@Column(name = "tamanho_m")
	private int tamanhoM;
	@Column(name = "tamanho_g")
	private int tamanhoG;
	@Column(name = "tamanho_gg")
	private int tamanhoGG;
	@Column(name = "tamanho_xxg")
	private int tamanhoXXG;
	@Column(name = "tamanho_u")
	private int tamanhoU;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTamanhoPP() {
		return tamanhoPP;
	}

	public void setTamanhoPP(int tamanhoPP) {
		this.tamanhoPP = tamanhoPP;
	}

	public int getTamanhoP() {
		return tamanhoP;
	}

	public void setTamanhoP(int tamanhoP) {
		this.tamanhoP = tamanhoP;
	}

	public int getTamanhoM() {
		return tamanhoM;
	}

	public void setTamanhoM(int tamanhoM) {
		this.tamanhoM = tamanhoM;
	}

	public int getTamanhoG() {
		return tamanhoG;
	}

	public void setTamanhoG(int tamanhoG) {
		this.tamanhoG = tamanhoG;
	}

	public int getTamanhoGG() {
		return tamanhoGG;
	}

	public void setTamanhoGG(int tamanhoGG) {
		this.tamanhoGG = tamanhoGG;
	}

	public int getTamanhoU() {
		return tamanhoU;
	}

	public void setTamanhoU(int tamanhoU) {
		this.tamanhoU = tamanhoU;
	}

	public int getTamanhoXXG() {
		return tamanhoXXG;
	}

	public void setTamanhoXXG(int tamanhoXXG) {
		this.tamanhoXXG = tamanhoXXG;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}

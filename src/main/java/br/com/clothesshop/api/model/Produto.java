package br.com.clothesshop.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Produto extends Model {
	@NotNull
	private long codigo;

	@NotNull
	private String descricao;
	private String detalhes;
	private String cor;
	private BigDecimal valorCusto;
	private BigDecimal margemLucroPadrao;
	
	@Column(name = "tamanho_pp", columnDefinition = "int default 0")
	private int tamanhoPP;
	@Column(name = "tamanho_p", columnDefinition = "int default 0")
	private int tamanhoP;
	@Column(name = "tamanho_m", columnDefinition = "int default 0")
	private int tamanhoM;
	@Column(name = "tamanho_g", columnDefinition = "int default 0")
	private int tamanhoG;
	@Column(name = "tamanho_gg", columnDefinition = "int default 0")
	private int tamanhoGG;
	@Column(name = "tamanho_xxg", columnDefinition = "int default 0")
	private int tamanhoXXG;
	@Column(name = "tamanho_u", columnDefinition = "int default 0")
	private int tamanhoU;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getMargemLucroPadrao() {
		return margemLucroPadrao;
	}

	public void setMargemLucroPadrao(BigDecimal margemLucroPadrao) {
		this.margemLucroPadrao = margemLucroPadrao;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
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

	public int getTamanhoXXG() {
		return tamanhoXXG;
	}

	public void setTamanhoXXG(int tamanhoXXG) {
		this.tamanhoXXG = tamanhoXXG;
	}

	public int getTamanhoU() {
		return tamanhoU;
	}

	public void setTamanhoU(int tamanhoU) {
		this.tamanhoU = tamanhoU;
	}
}

package com.teste.api.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservasDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String evento;
	private String setor;
	private int usuarioId;
	private int quantidadeIngresso;
	private LocalDateTime dataCriacao;
	private double precoTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	// Getters e Setters

}

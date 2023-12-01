package com.teste.api.model.dto;

import java.io.Serializable;

import com.teste.api.model.entidades.Evento;

public class IngressoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int eventoId;
	private int setorId;
	private int quantidadeIngresso;
	private String nome;
	private double valor;
	private String tipoIngresso;
	private String status;



	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventoId() {
		return eventoId;
	}

	public void setEventoId(int eventoId) {
		this.eventoId = eventoId;
	}

	public int getSetorId() {
		return setorId;
	}

	public void setSetorId(int setorId) {
		this.setorId = setorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipoIngresso() {
		return tipoIngresso;
	}

	public void setTipoIngresso(String tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

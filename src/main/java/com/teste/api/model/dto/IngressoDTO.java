package com.teste.api.model.dto;

import java.io.Serializable;

import com.teste.api.model.entidades.Evento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngressoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private Evento evento;
	
	private String valor;
	
	


	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getEvento() {
		return evento.getNome();
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	



}

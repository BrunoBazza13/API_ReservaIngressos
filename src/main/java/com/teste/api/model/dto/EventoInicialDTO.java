package com.teste.api.model.dto;

import java.io.Serializable;
import java.sql.Date;




public class EventoInicialDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private Date data;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}

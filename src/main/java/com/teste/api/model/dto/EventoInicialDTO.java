package com.teste.api.model.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoInicialDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private Date data;
	


}
package com.teste.api.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCarrinhoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private double valor;

	private double total;


}

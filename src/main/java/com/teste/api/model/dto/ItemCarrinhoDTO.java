package com.teste.api.model.dto;

import java.io.Serializable;

import com.teste.api.model.entidades.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCarrinhoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private double valor;

	private double precoTotal;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

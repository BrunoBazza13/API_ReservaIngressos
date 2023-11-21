package com.teste.api.model.dto;

import java.util.List;

public class CarrinhoDTO {

	private List<ReservaDTO> itensCarrinho;

	public List<ReservaDTO> getItensCarrinho() {
		return itensCarrinho;
	}

	public void setItensCarrinho(List<ReservaDTO> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}


	
}
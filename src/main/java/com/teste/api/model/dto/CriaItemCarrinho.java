package com.teste.api.model.dto;

import com.teste.api.model.entidades.Ingresso;
import com.teste.api.model.entidades.ItemCarrinho;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CriaItemCarrinho {

	private ItemCarrinho reserva;
    private Ingresso ingresso;
	public ItemCarrinho getReserva() {
		return reserva;
	}
	public void setReserva(ItemCarrinho reserva) {
		this.reserva = reserva;
	}
	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
    

    	
	
}

package com.teste.api.model.dto;

import com.teste.api.model.entidades.Ingresso;
import com.teste.api.model.entidades.Reservas;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CriaItemCarrinho {

	private Reservas reserva;
    private Ingresso ingresso;

    public Reservas getReserva() {
		return reserva;
	}
	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}
	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
    

    	
	
}

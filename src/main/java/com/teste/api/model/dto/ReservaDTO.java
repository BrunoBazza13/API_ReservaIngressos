package com.teste.api.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int quantidadeIngresso;
	private List<IngressoDTO> ingressos;

	

	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<IngressoDTO> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<IngressoDTO> ingressos) {
		this.ingressos = ingressos;
	}
	
	

}

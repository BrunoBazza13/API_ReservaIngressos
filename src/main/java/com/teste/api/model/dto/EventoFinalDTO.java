package com.teste.api.model.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoFinalDTO {
  
	
	private String nome;
	private Date data;
	private String descricao;
	private LocalDTO local;
	private byte[] imagem;

	public EventoFinalDTO() {
		super();
		
	}

	
	
	

}

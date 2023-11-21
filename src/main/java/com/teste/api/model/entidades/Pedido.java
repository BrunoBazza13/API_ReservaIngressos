package com.teste.api.model.entidades;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Entity
@Getter
@Setter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "reserva_id")
	private ItemCarrinho reserva; // Um para um com Reserva após o pagamento

	private String status; // Confirmado, Pendente

	private LocalDateTime dataCriacao;

	public Pedido(String status, LocalDateTime dataCriacao, ItemCarrinho reserva) {
		super();
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.reserva = reserva;
	}

}

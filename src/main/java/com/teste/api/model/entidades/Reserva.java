//package com.teste.api.model.entidades;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Random;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Reserva {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	
//	private Usuario usuario;
//	private Evento evento;
//	
//	@ManyToOne
//	@JoinColumn(name = "setor_id")
//	private Setores setor;
//
//	@OneToMany(mappedBy = "reserva")
//	private List<Ingresso> ingressos;
//	private LocalDateTime dataCriacao;
//	private String codigo;
//	
//	
//	public Reserva() {
//		
//	}
//	
//	public String gerarCodigo() {
//	    return codigo = String.format("%010d", new Random().nextInt(1000000000));
//	 }
//	
//
//
//}

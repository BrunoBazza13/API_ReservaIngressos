package com.teste.api.model.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;


@Entity

public class Ingresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setores setor;

	@ManyToMany(mappedBy = "ingresso")
	private Set<Reservas> reservas = new HashSet<Reservas>();

	@NotBlank(message = "Por favor inserir o nome!")
	private String nome;

	// @NotBlank(message = "Por favor inserir o valor do ingresso!")
	private double valor;

	@NotBlank(message = "Por favor o tipo do ingresso!")
	private String tipoIngresso;

	
	private int quantidade;

	private String status;

	public Ingresso() {

	}

	public Ingresso(int id, Evento evento, Setores setor, Set<Reservas> reservas, String nome, double valor,
			String tipoIngresso, int quantidade, String status) {
		super();
		this.id = id;
		this.evento = evento;
		this.setor = setor;
		this.reservas = reservas;
		this.nome = nome;
		this.valor = valor;
		this.tipoIngresso = tipoIngresso;
		this.quantidade = quantidade;
		this.status = status;
	}

	public Set<Reservas> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reservas> reservas) {
		this.reservas = reservas;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Setores getSetor() {
		return setor;
	}

	public void setSetor(Setores setor) {
		this.setor = setor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipoIngresso() {
		return tipoIngresso;
	}

	public void setTipoIngresso(String tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

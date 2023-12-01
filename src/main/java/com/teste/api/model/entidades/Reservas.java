package com.teste.api.model.entidades;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.teste.api.model.dto.IngressoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Reservas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany
	@JoinTable(name = "reserva_ingresso", joinColumns = @JoinColumn(name = "reserva_id"), inverseJoinColumns = @JoinColumn(name = "ingresso_id"))
	private Set<Ingresso> ingresso = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	private int quantidadeIngresso;

	private LocalDateTime dataCriacao;

	private double precoTotal;

	private String evento;

	private String setor;

	public Reservas() {

	}

	public Reservas(int id, Set<Ingresso> ingresso, Usuario usuario, int quantidadeIngresso, LocalDateTime dataCriacao,
			double precoTotal, String evento, String setor) {
		super();
		this.id = id;
		this.ingresso = ingresso;
		this.usuario = usuario;
		this.quantidadeIngresso = quantidadeIngresso;
		this.dataCriacao = dataCriacao;
		this.precoTotal = precoTotal;
		this.evento = evento;
		this.setor = setor;
	}

	public Set<Ingresso> getIngresso() {
		return ingresso;
	}

	public void setIngresso(Set<Ingresso> ingresso) {
		this.ingresso = ingresso;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public double precoTotal(double quantidadeIngresso, double preco) {
		return this.precoTotal = quantidadeIngresso * preco;
	}

	public Reservas(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

}

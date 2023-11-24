package com.teste.api.model.entidades;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
public class Reservas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany
	@JoinTable(name = "reserva_ingresso", joinColumns = @JoinColumn(name = "reserva_id"), inverseJoinColumns = @JoinColumn(name = "ingresso_id"))
	private Set<Ingresso> ingressos = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	private int quantidadeIngresso;

	private LocalDateTime dataCriacao;
	
	private String evento;
	
	private String setor;
	
	private double precoTotal;

	public Reservas() {

	}

	public Reservas(Set<Ingresso> ingressos, Pedido pedido, Usuario usuario, Setores setor, int quantidadeReserva,
			LocalDateTime dataCriacao) {
		super();
		this.ingressos = ingressos;
		this.quantidadeIngresso = quantidadeReserva;
		this.dataCriacao = dataCriacao;
	}

	public boolean contemIngresso(Ingresso ingresso) {
		return this.ingressos.contains(ingresso);
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

	public Set<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
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

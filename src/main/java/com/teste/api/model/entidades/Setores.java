package com.teste.api.model.entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Setores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToMany(mappedBy = "setores")
	private Set<Evento> eventos = new HashSet<>();

	//@NotBlank(message = "Por favor informar a quantidade!")
	private int quantidadePessoas;

	//@NotBlank(message = "Por favor informar o nome do setor!")
	private String nome;

	//@NotBlank(message = "Por favor informar o tipo do setor!")
	private String tipo;

	public Setores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Setores(int id, Set<Evento> eventos, int quantidadePessoas, String nome, String tipo) {

		super();
		this.id = id;
		this.eventos = eventos;
		this.quantidadePessoas = quantidadePessoas;
		this.nome = nome;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	public int getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(int quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

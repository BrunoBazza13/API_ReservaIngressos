package com.teste.api.model.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(mappedBy = "local")
	private Evento evento;

	@NotBlank(message = "Por favor informar o cep!")
	@Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido")
	private String cep;

	@NotBlank(message = "Por favor informar a localização!")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-]+$", message = "Logradouro inválido")
	private String logradouro;

	@NotBlank(message = "Por favor informar o núremo da localização!")
	private String numeroLocal;

	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-]+$", message = "Bairro inválido")
	@NotBlank(message = "Por favor informar o bairro!")
	private String bairro;

	public Local() {
		super();
	}

	public Local(Evento evento, String cep, String logradouro, String numeroLocal, String bairro) {
		super();
		this.evento = evento;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numeroLocal = numeroLocal;
		this.bairro = bairro;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLocal() {
		return numeroLocal;
	}

	public void setNumeroLocal(String numeroLocal) {
		this.numeroLocal = numeroLocal;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}

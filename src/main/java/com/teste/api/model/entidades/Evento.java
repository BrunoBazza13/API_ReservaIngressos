package com.teste.api.model.entidades;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "local_id")
	private Local local;

	@NotBlank(message = "A inserção do nome do evento é obrigatória!")
	private String nomeEvento;

	@OneToMany(mappedBy = "evento")
	private List<Ingresso> ingressos;

	@ManyToMany
	@JoinTable(name = "evento_setor", joinColumns = @JoinColumn(name = "evento_id"), inverseJoinColumns = @JoinColumn(name = "setor_id"))
	private Set<Setores> setores = new HashSet<Setores>();

	@Lob
	private byte[] imagem;

	private String descricao;
	private Date data;
	private String atracao;
	private int totalPessoas;

	public Evento() {

	}

	public Evento(int id, Local local, String nomeEvento, List<Ingresso> ingressos, Set<Setores> setores, byte[] imagem,
			String descricao, Date data, String atracao, int totalPessoas) {
		super();
		this.id = id;
		this.local = local;
		this.nomeEvento = nomeEvento;
		this.ingressos = ingressos;
		this.setores = setores;
		this.imagem = imagem;
		this.descricao = descricao;
		this.data = data;
		this.atracao = atracao;
		this.totalPessoas = totalPessoas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public Set<Setores> getSetores() {
		return setores;
	}

	public void setSetores(Set<Setores> setores) {
		this.setores = setores;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAtracao() {
		return atracao;
	}

	public void setAtracao(String atracao) {
		this.atracao = atracao;
	}

	public int getTotalPessoas() {
		return totalPessoas;
	}

	public void setTotalPessoas(int totalPessoas) {
		this.totalPessoas = totalPessoas;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

}

package com.teste.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.teste.api.model.entidades.ItemCarrinho;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarUsuarioDTO {
	
	
	@OneToMany(mappedBy = "usuario")
    private List<ItemCarrinho> itemCarrinho = new ArrayList<ItemCarrinho>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "A inserção do nome é obrigatória!")
	private String nome;
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
	        flags = Pattern.Flag.CASE_INSENSITIVE, message = "Insira um e-mail válido!")
	@NotBlank(message = "A inserção do e-mail é obrigatória!")
	private String login;
	
	@NotBlank(message =  "A inserção da senha é obrigatória!")
	private String senha;
    
	@CPF(message = "Insira um cpf válido!")
	@NotBlank(message =  "A inserção do CPF é obrigatória!")
	private String cpf;
	
	@NotBlank(message =  "A inserção do telefone é obrigatória!")
	@Pattern(regexp = "^[0-9]{2}[0-9]{9}$", message = "Número de telefone inválido")
	private String telefone;
	
	public CriarUsuarioDTO() {
		super();
	}

	public List<ItemCarrinho> getItemCarrinho() {
		return itemCarrinho;
	}

	public void setItemCarrinho(List<ItemCarrinho> itemCarrinho) {
		this.itemCarrinho = itemCarrinho;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	

}

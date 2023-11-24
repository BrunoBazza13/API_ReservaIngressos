package com.teste.api.model.entidades;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter
@Entity
public class Usuario implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "usuario")
    private List<Reservas> itemCarrinho = new ArrayList<Reservas>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "A inserção do nome é obrigatória!")
	private String nome;
	
	
	@NotBlank(message = "A inserção do e-mail é obrigatória!")
	@org.hibernate.validator.constraints.Email
	private String login;
	
	@NotBlank(message =  "A inserção da senha é obrigatória!")
	private String senha;
    
	@CPF(message = "Insira um cpf válido!")
	@NotBlank(message =  "A inserção do CPF é obrigatória!")
	private String cpf;
	
	@NotBlank(message =  "A inserção do telefone é obrigatória!")
	@Pattern(regexp = "^[0-9]{2}[0-9]{9}$", message = "Número de telefone inválido")
	private String telefone;
	
	public Usuario() {
		super();
		
	}
	public Usuario(int id, String nome, String login, String senha, String cpf, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public String getUsername() {
	   return this.login;
	}

	@Override
	public String getPassword() {
	   return this.senha;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	@Override
	public boolean isEnabled() {
		return false;
	}
	public List<Reservas> getItemCarrinho() {
		return itemCarrinho;
	}
	public void setItemCarrinho(List<Reservas> itemCarrinho) {
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

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

@Entity
public class Usuario implements UserDetails {

	@OneToMany(mappedBy = "usuario")
	private List<Reservas> reservas = new ArrayList<Reservas>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "A inserção do nome é obrigatória!")
	private String nome;

	@NotBlank(message = "A inserção do e-mail é obrigatória!")
	@org.hibernate.validator.constraints.Email
	private String login;

	@NotBlank(message = "A inserção da senha é obrigatória!")
	private String senha;

	@CPF(message = "Insira um cpf válido!")
	@NotBlank(message = "A inserção do CPF é obrigatória!")
	private String cpf;

	@NotBlank(message = "A inserção do telefone é obrigatória!")
	@Pattern(regexp = "^[0-9]{2}[0-9]{9}$", message = "Número de telefone inválido")
	private String telefone;

	public Usuario() {
		super();

	}

	public Usuario(List<Reservas> reservas, int id, String nome, String login, String password, String cpf,
			String telefone) {
		super();
		this.reservas = reservas;
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = password;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public Usuario(String login, String password) {
		this.login = login;
		this.senha = password;

	}

	public List<Reservas> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}

	public static long getSerialversionuid() {
		return getSerialversionuid();
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("USER_ROLE"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

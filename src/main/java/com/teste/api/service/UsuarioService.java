package com.teste.api.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.UsuarioNotFoundException;
import com.teste.api.model.entidades.ItemCarrinho;
import com.teste.api.model.entidades.Usuario;
import com.teste.api.model.repository.ItemCarrinhoRepository;
import com.teste.api.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> listClient() throws RepositoryNotInjectedException {
		if (usuarioRepository == null) {
			throw new RepositoryNotInjectedException("UsuarioRepository não existe!");
		}
		List<Usuario> list = this.usuarioRepository.findAll();
		return list;

	}

	public Usuario criaUsuario(Usuario novoUsuario) {

		Optional<Usuario> existeCPf = Optional.ofNullable(usuarioRepository.findByCpf(novoUsuario.getCpf()));
		Optional<Usuario> existeEmail = Optional.ofNullable(usuarioRepository.findByLogin(novoUsuario.getLogin()));

		if (existeCPf.isPresent() || existeEmail.isPresent()) {
			return null;
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(novoUsuario.getSenha());

		novoUsuario.setSenha(encryptedPassword);

		Usuario usuario = usuarioRepository.save(novoUsuario);

		ItemCarrinho itemCarrinho = new ItemCarrinho(usuario);

		itemCarrinhoRepository.save(itemCarrinho);

		return novoUsuario;

	}

	public boolean loginUsuario(String login, String senha) {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByLogin(login));

		if (usuario.isPresent()) {
			Usuario user = usuario.get();

			if (passwordEncoder.matches(senha, user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public List<ItemCarrinho> obterIdCarrinhoDoUsuario(String login) {
	    Usuario usuario = usuarioRepository.findByLogin(login);
	   
	    if (usuario != null && usuario.getItemCarrinho() != null && !usuario.getItemCarrinho().isEmpty()) {
	       
	   	List<ItemCarrinho> reservas =  usuario.getItemCarrinho();
	   	
	   	return reservas;

	    }
	    return null;
	}


	public Usuario obterUsuarioPorId(Integer id) throws RepositoryNotInjectedException {
		if (usuarioRepository == null) {
			throw new RepositoryNotInjectedException("UsuarioRepository não foi injetado");
		}
		return usuarioRepository.findById(id).orElse(null);
	}

	public Optional<Usuario> atualizaUsuario(Usuario atualizaUsuario) throws RepositoryNotInjectedException {

		if (atualizaUsuario == null) {
			throw new UsuarioNotFoundException("Usuário fornecido é não existe");
		}
		if (usuarioRepository == null) {
			throw new RepositoryNotInjectedException("UsuárioRepository não foi injetado");
		}
		return ServiceUtils.atualizarEntidade(atualizaUsuario.getId(), atualizaUsuario, usuarioRepository);

	}

}

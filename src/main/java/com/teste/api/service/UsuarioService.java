package com.teste.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.UsuarioNotFoundException;
import com.teste.api.model.dto.ReservasDTO;
import com.teste.api.model.entidades.Reservas;
import com.teste.api.model.entidades.Usuario;
//import com.teste.api.model.repository.ItemCarrinhoRepository;
import com.teste.api.model.repository.ReservaRepository;
import com.teste.api.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	public List<Usuario> listClient() throws RepositoryNotInjectedException {
		if (usuarioRepository == null) {
			throw new RepositoryNotInjectedException("UsuarioRepository não existe!");
		}
		List<Usuario> list = this.usuarioRepository.findAll();
		return list;

	}

	public Usuario criaUsuario(Usuario novoUsuario) {

		Optional<Usuario> existeCPf = usuarioRepository.findByCpf(novoUsuario.getCpf());
		Optional<Usuario> existeEmail = Optional.ofNullable(usuarioRepository.findByLogin(novoUsuario.getLogin()));

		if (existeCPf.isPresent() || existeEmail.isPresent()) {
			return null;
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(novoUsuario.getPassword());

		novoUsuario.setSenha(encryptedPassword);

		return usuarioRepository.save(novoUsuario);

	}

//	public boolean loginUsuario(String login, String senha) {
//
//		Usuario usuario = usuarioRepository.findByLogin(login);
//
//		if (usuario != null) {
//
//			if (passwordEncoder.matches(senha, usuario.getPassword())) {
//				return true;
//			}
//		}
//
//		passwordEncoder.matches(senha, usuario.getPassword());
//
//		return false;
//	}

	public List<ReservasDTO> obterReservaDoUsuario(String login) {
		Usuario usuario = usuarioRepository.findByLogin(login);

		if (usuario != null && usuario.getReservas() != null && !usuario.getReservas().isEmpty()) {
			List<Reservas> reservas = usuario.getReservas();

			return reservas.stream().map(reserva -> modelMapper.map(reserva, ReservasDTO.class))
					.collect(Collectors.toList());

		}
		return null;
	}

	public Usuario obterUsuarioPorLogin(String login) throws RepositoryNotInjectedException {
		if (usuarioRepository == null) {
			throw new RepositoryNotInjectedException("UsuarioRepository não foi injetado");
		}
		return usuarioRepository.findByLogin(login);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return usuarioRepository.findByLogin(username);
	}

}

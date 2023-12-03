//package com.teste.api.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.teste.api.model.entidades.Usuario;
//import com.teste.api.model.repository.UsuarioRepository;
//
//@Service
//public class AuthenticationService implements UserDetailsService{
//	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Usuario usuario = usuarioRepository.findByLogin(username);
//		if (usuario == null) {
//            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
//        }
//        return usuario;
//	}
//
//}

package com.teste.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.api.model.dto.ReservasDTO;
import com.teste.api.model.entidades.Ingresso;
import com.teste.api.model.entidades.Reservas;
import com.teste.api.model.entidades.Setores;
import com.teste.api.model.entidades.Usuario;
import com.teste.api.model.repository.ReservaRepository;

import io.jsonwebtoken.lang.Collections;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private IngressoService ingressoService;

	@Autowired
	private SetorService setorService;

	@Autowired
	private ModelMapper modelMapper;

	public ReservaService(ReservaRepository reservaRepository) {
		super();
		this.reservaRepository = reservaRepository;
	}

	public List<ReservasDTO> listaReserva() {
		List<Reservas> reservas = reservaRepository.findAll();
		return reservas.stream().map(reserva -> modelMapper.map(reserva, ReservasDTO.class))
				.collect(Collectors.toList());
	}

	public List<ReservasDTO> retornaReservaDTO(List<Reservas> reservas) {

		//return modelMapper.map(reserva, ReservasDTO.class);
		return reservas.stream().map(reserva -> modelMapper.map(reserva, ReservasDTO.class))
				.collect(Collectors.toList());

	}

	public int totalIngressos(int setorId) {
		return reservaRepository.getTotalQuantidadePorSetor(setorId);
	}

	public boolean estaCheio(Setores setor, Reservas itensCarrinho, Ingresso ingresso) {

		int total = totalIngressos(setor.getId());
		total += itensCarrinho.getIngresso().size();

		while (total > setor.getQuantidadePessoas()) {

			total--; // (rever logica)

			ingresso.setStatus("Indiponivel");
			ingressoService.criaIngresso(ingresso);

			return false;
		}
		return true;
	}

	public List<Reservas> adicionaAosMeusIngressos(Reservas itemCarrinho) {

		Usuario usuario = usuarioService.obterUsuarioPorId(itemCarrinho.getUsuario().getId());

		List<Reservas> reservasCriadas = new ArrayList<>();

		Optional<Setores> setor = null;
		Optional<Ingresso> optionalIngresso = null;


		for (Ingresso ingressoRequest : itemCarrinho.getIngresso()) {
			optionalIngresso = ingressoService.obterIngressoPorId(ingressoRequest.getId());

			Ingresso ingresso = optionalIngresso.get();
			
			int quantidade = ingressoRequest.getQuantidade();

			setor = setorService.obetemSetorPorId(optionalIngresso.get().getSetor().getId());

			if (!estaCheio(setor.get(), itemCarrinho, optionalIngresso.get())) {
				return null;
			}
		

			Reservas novaReserva = new Reservas();
			novaReserva.setIngresso(itemCarrinho.getIngresso());
			novaReserva.setDataCriacao(LocalDateTime.now());
			novaReserva.setQuantidadeIngresso(quantidade);
			novaReserva.precoTotal(ingresso.getValor(), quantidade);
			novaReserva.setEvento(ingresso.getEvento().getNomeEvento());
			novaReserva.setSetor(ingresso.getNome());
			novaReserva.setUsuario(usuario);

			Reservas reservaCriada = reservaRepository.save(novaReserva);
			reservasCriadas.add(reservaCriada);

		}

		return reservasCriadas;

//		Reservas reservaExistente = reservaRepository.findByUsuarioAndIngresso_Id(usuario,
//				optionalIngresso.get().getId());  
//
//		
//		
//		if (reservaExistente != null) {
//
//			int novaQuantidade = reservaExistente.getQuantidadeIngresso() + contador;
//			reservaExistente.setQuantidadeIngresso(novaQuantidade);
//			reservaExistente.setDataCriacao(LocalDateTime.now());
//			reservaExistente.precoTotal(optionalIngresso.get().getValor(), contador);
//
//			return reservaRepository.save(reservaExistente);
//
//		} else {
//		
//			Reservas novaReserva = new Reservas();
//			novaReserva.setIngresso(itemCarrinho.getIngresso());
//			novaReserva.setDataCriacao(LocalDateTime.now());
//			novaReserva.setQuantidadeIngresso(contador);
//			novaReserva.precoTotal(optionalIngresso.get().getValor(), contador);
//			novaReserva.setUsuario(usuario);
//
//			  return   reservaRepository.save(novaReserva);
//		}
//
//	}

	}

}

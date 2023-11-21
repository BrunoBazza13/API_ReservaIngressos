package com.teste.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.api.exception.NomeIngressoSetorInvalidoException;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.dto.ReservaDTO;
import com.teste.api.model.entidades.Ingresso;
import com.teste.api.model.entidades.ItemCarrinho;
import com.teste.api.model.entidades.Setores;
import com.teste.api.model.entidades.Usuario;
import com.teste.api.model.repository.ReservaRepository;

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

	public List<ReservaDTO> listaReserva() {
		List<ItemCarrinho> reservas = reservaRepository.findAll();

		return reservas.stream().map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
				.collect(Collectors.toList());
	}

	public ReservaDTO retornaReservaPorId(int id) {
		ItemCarrinho reserva = reservaRepository.findById(id).get();
		return modelMapper.map(reserva, ReservaDTO.class);
	}

	public int totalIngressos(int setorId) {
		return reservaRepository.getTotalQuantidadePorSetor(setorId);

	}

	public boolean estaCheio(Setores setor, ItemCarrinho itensCarrinho, Ingresso ingresso) throws NomeIngressoSetorInvalidoException, SetorNotFoundException, RepositoryNotInjectedException {

		int total = totalIngressos(setor.getId());

		total += itensCarrinho.getIngressos().size();

		while (total > setor.getQuantidadePessoas()) { // essa logica ela soma o total de reservas feitas por setor e
														// soma mais os novos ingressos
			total--; // logo depois entra em um lup (rever logica)

			ingresso.setStatus("Indiponivel");
			ingressoService.criaIngresso(ingresso);

			return false;
		}

		return true;
	}

	public ItemCarrinho adicionaAosMeusIngressos(ItemCarrinho itemCarrinho) throws RepositoryNotInjectedException, NomeIngressoSetorInvalidoException, SetorNotFoundException {

		Usuario usuario = usuarioService.obterUsuarioPorId(itemCarrinho.getUsuario().getId());

		ItemCarrinho novaReserva = new ItemCarrinho();
		Optional<Setores> setor = null;
		Optional<Ingresso> optionalIngresso = null;
		int contador = 0;

		for (Ingresso ingressoRequest : itemCarrinho.getIngressos()) {
			optionalIngresso = ingressoService.obterIngressoPorId(ingressoRequest.getId());

			setor = setorService.obetemSetorPorId(optionalIngresso.get().getSetor().getId());

			if (!estaCheio(setor.get(), itemCarrinho, optionalIngresso.get())) {
				return null;
			}

			contador++;
		}

		ItemCarrinho reservaExistente = reservaRepository.findByUsuarioAndIngressos_Id(usuario,
				optionalIngresso.get().getId());

		if (reservaExistente != null) {

			int novaQuantidade = reservaExistente.getQuantidadeIngresso() + contador;
			reservaExistente.setQuantidadeIngresso(novaQuantidade);
			reservaExistente.setDataCriacao(LocalDateTime.now());

			System.out.println(optionalIngresso.get().getEvento().getNome());

			return reservaRepository.save(reservaExistente);

		} else {

			novaReserva.setIngressos(itemCarrinho.getIngressos());
			novaReserva.setDataCriacao(LocalDateTime.now());
			novaReserva.setQuantidadeIngresso(contador);
			novaReserva.setUsuario(usuario);

			return reservaRepository.save(novaReserva);
		}

	}

}


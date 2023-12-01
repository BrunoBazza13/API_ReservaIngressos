package com.teste.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.api.exception.NomeIngressoSetorInvalidoException;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.dto.IngressoDTO;
//import com.teste.api.model.dto.ItemCarrinhoDTO;
import com.teste.api.model.dto.ReservasDTO;
import com.teste.api.model.entidades.Reservas;
//import com.teste.api.service.ItemCarrinhoService;
import com.teste.api.service.ReservaService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private HttpSession session;

	@PostMapping
	public ResponseEntity<String> criarItemCarrinho(@RequestBody Reservas reserva) {

		List<Reservas> reservaCriada = reservaService.adicionaAosMeusIngressos(reserva);

		List<ReservasDTO> reservaDTO = reservaService.retornaReservaDTO(reservaCriada);

		List<ReservasDTO> reservasNaSessao = (List<ReservasDTO>) session.getAttribute("carrinho");

		if (reservasNaSessao == null) {
			reservasNaSessao = new ArrayList<>();
		}

		reservasNaSessao.addAll(reservaDTO);

		session.setAttribute("carrinho", reservasNaSessao);

		if (reservaCriada == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ingresso indisponivel");
		} else {
			return ResponseEntity.ok("Reserva efetuada com sucesso");
		}

	}

//	@GetMapping("/buscaPorId/{id}")
//	public ResponseEntity<ReservaDTO> getItensCarrinho(@PathVariable int id) {
//
//		ReservaDTO carrinhos = reservaService.retornaReservaPorId(id);
//
//		if (carrinhos == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return new ResponseEntity<ReservaDTO>(carrinhos, HttpStatus.OK);
//	}

	@GetMapping
	public ResponseEntity<List<ReservasDTO>> getListarItemCarrinho() {
		List<ReservasDTO> reservas = reservaService.listaReserva();

		return new ResponseEntity<List<ReservasDTO>>(reservas, HttpStatus.OK);
	}

}

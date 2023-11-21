package com.teste.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.api.exception.NomeIngressoSetorInvalidoException;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.dto.ReservaDTO;
import com.teste.api.model.entidades.ItemCarrinho;
import com.teste.api.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	
	@PostMapping
	public ResponseEntity<ItemCarrinho> criarItemCarrinho(@Valid @RequestBody ItemCarrinho reserva) throws RepositoryNotInjectedException, NomeIngressoSetorInvalidoException, SetorNotFoundException {

		ItemCarrinho reservaCriada = reservaService.adicionaAosMeusIngressos(reserva);

		if (reservaCriada == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			return ResponseEntity.ok(reservaCriada);
		}

	}
	
	@GetMapping
	public ResponseEntity<List<ReservaDTO>> getListarItemCarrinho() {
		List<ReservaDTO> reservas = reservaService.listaReserva();

		return new ResponseEntity<List<ReservaDTO>>(reservas, HttpStatus.OK);
	}
	
	@GetMapping("/buscaPorId/{id}")
	public ResponseEntity<ReservaDTO> getItemCarrinhoPorId(@PathVariable int id) {
		ReservaDTO reservaDTO = reservaService.retornaReservaPorId(id);

		return new ResponseEntity<ReservaDTO>(reservaDTO, HttpStatus.OK);
	}

    

	
	



}

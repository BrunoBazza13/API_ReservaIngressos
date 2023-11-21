package com.teste.api.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.model.dto.EventoFinalDTO;
import com.teste.api.model.entidades.Evento;
import com.teste.api.service.EventoServiceImpl;


@RestController
@RequestMapping("/api/evento")
public class EventoController {

	@Autowired
	private EventoServiceImpl eventoService;


	@GetMapping("/list")
	public ResponseEntity<List<EventoFinalDTO>> getEvento() {

		List<EventoFinalDTO> eventoDto = eventoService.dadosEvento();

		return new ResponseEntity<List<EventoFinalDTO>>(eventoDto, HttpStatus.OK);
	}

	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<EventoFinalDTO> getEventoPorId(@PathVariable int id) {

		EventoFinalDTO eventoDto = eventoService.buscaPorId(id);
		return new ResponseEntity<EventoFinalDTO>(eventoDto, HttpStatus.OK);
	}

	@PostMapping("/cria")
	public ResponseEntity<String> setEvento(@RequestPart Evento novoEvento, @RequestPart MultipartFile imagem) {
	   try {
	       novoEvento.setImagem(imagem.getBytes());
	   } catch (IOException e) {
	       e.printStackTrace();
	       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem: " + e.getMessage());
	   }
	   Evento eventoAdicionado = eventoService.criarEvento(novoEvento);
	   return ResponseEntity.status(HttpStatus.CREATED).body("Evento criado com sucesso: " + eventoAdicionado);
	}


	@PutMapping("/setor")
	public ResponseEntity<String> setAtualizaEvento(@RequestPart Evento atualizaEvento) throws RepositoryNotInjectedException {

		eventoService.atualizaEvento(atualizaEvento);
		
		if(atualizaEvento == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Evento não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Evento atualizado");

	}

}


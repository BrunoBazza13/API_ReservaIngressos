package com.teste.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.model.dto.LocalDTO;
import com.teste.api.model.entidades.Local;
import com.teste.api.service.LocalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/local")
public class LocalController {
	
	@Autowired
	private LocalService localService;
	
	@PostMapping
	public ResponseEntity<Local> setLocal(@Valid @RequestBody Local novoLocal) {
		Local localAdicionado = localService.adicionaLocal(novoLocal);
		return ResponseEntity.status(HttpStatus.CREATED).body(localAdicionado);
	}
	
	
	@GetMapping("buscaPorId/{id}")
	public ResponseEntity<LocalDTO> getLocalPorID(@PathVariable int id){
		LocalDTO localDTO = localService.buscarLocalPorIdDTO(id);
		
		return new ResponseEntity<LocalDTO>(localDTO,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Local> setAtualizaLocal(@Valid @RequestBody Local atualizaLocal) throws RepositoryNotInjectedException {
		
		 localService.atualizaLocal(atualizaLocal);
		return new ResponseEntity<Local>(atualizaLocal, HttpStatus.OK);
		
	}
	

}

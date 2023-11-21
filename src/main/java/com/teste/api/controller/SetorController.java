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
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.entidades.Setores;
import com.teste.api.service.SetorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/setor")
public class SetorController {

	
	@Autowired
	private SetorService setorService;
	
	@PostMapping
	public ResponseEntity<Setores> adicionarEvento(@Valid @RequestBody Setores novoSetor) throws RepositoryNotInjectedException {
		Setores setorAdicionado = setorService.adicionaSetor(novoSetor);
		return ResponseEntity.status(HttpStatus.CREATED).body(setorAdicionado);

	}
	
	@GetMapping("buscaPorId/{id}")
	public ResponseEntity<Setores> getSetorPorId(@PathVariable int id) throws SetorNotFoundException{
		Setores setorDTO = setorService.obtemSetorPorIdDTO(id);
		
		return new ResponseEntity<Setores>(setorDTO, HttpStatus.OK);	
	}
	
	@PutMapping
	public ResponseEntity<Setores> setAtualizaSetor(@Valid @RequestBody Setores setor) throws SetorNotFoundException, RepositoryNotInjectedException {
		
		setorService.atualizaSetor(setor);
		return new ResponseEntity<Setores>(setor, HttpStatus.OK);
	}
	
	
}

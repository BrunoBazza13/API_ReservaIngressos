package com.teste.api.controller;

import java.util.List;
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
import com.teste.api.exception.IngressoNotFoundException;
import com.teste.api.exception.ModelMapperNotConfiguredException;
import com.teste.api.exception.NomeIngressoSetorInvalidoException;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.ServiceNotInjectedException;
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.dto.IngressoDTO;
import com.teste.api.model.entidades.Ingresso;
import com.teste.api.service.IngressoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ingresso")
public class IngressoController {

	@Autowired
	private IngressoService ingressoService;

	@PostMapping
	public ResponseEntity<String> setIngresso(@Valid @RequestBody Ingresso novoIngresso) throws NomeIngressoSetorInvalidoException, SetorNotFoundException, RepositoryNotInjectedException {
		Ingresso ingressoAdicionado = ingressoService.criaIngresso(novoIngresso);

		if (ingressoAdicionado.equals(null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ingresso indisponível");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Ingresso criado");
	}
	

	@GetMapping
	public ResponseEntity<List<IngressoDTO>> getIngresso() throws RepositoryNotInjectedException, ModelMapperNotConfiguredException, ServiceNotInjectedException {
	 if (ingressoService == null) {
	     throw new ServiceNotInjectedException("IngressoService não foi injetado");
	 }

	 List<IngressoDTO> ingresso = ingressoService.listarIngressos();
	 return new ResponseEntity<List<IngressoDTO>>(ingresso, HttpStatus.OK);
	}
	

	@GetMapping("/buscaPorID/{id}")
	public ResponseEntity<IngressoDTO> getProdutoPorID(@PathVariable int id) throws ServiceNotInjectedException {
	 if (ingressoService == null) {
	    throw new ServiceNotInjectedException("IngressoService não foi injetado");
	 }

	 IngressoDTO ingressoDTO = ingressoService.obterIngressoPorIdDTO(id);
	 
	 if (ingressoDTO == null) {
		 throw new ServiceNotInjectedException("IngressoService não foi injetado");
	 } else {
	    return ResponseEntity.ok(ingressoDTO);
	 }
	}

	@PutMapping
	public ResponseEntity<Ingresso> getAtualizaIngresso(@Valid @RequestBody Ingresso ingresso) throws IngressoNotFoundException, RepositoryNotInjectedException {
		ingressoService.atualizaIngresso(ingresso);
		return new ResponseEntity<Ingresso>(ingresso, HttpStatus.OK);

	}

}

package com.teste.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.api.model.dto.ItemCarrinhoDTO;
import com.teste.api.service.ItemCarrinhoService;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {
	
	@Autowired
	private ItemCarrinhoService itemCarrinhoService;
	
	@GetMapping("/buscaPorId/{id}")
	public ResponseEntity<ItemCarrinhoDTO> getItensCarrinho(@PathVariable int id) {

		ItemCarrinhoDTO carrinhos = itemCarrinhoService.obterCarrinhoPorId2(id);

		if (carrinhos == null) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<ItemCarrinhoDTO>(carrinhos, HttpStatus.OK);

	}

}

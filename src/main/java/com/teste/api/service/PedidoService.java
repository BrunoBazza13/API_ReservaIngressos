package com.teste.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.api.model.entidades.Pedido;
import com.teste.api.model.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Pedido adicionaPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
		return pedido;
	}
	
}

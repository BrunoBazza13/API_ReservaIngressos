package com.teste.api.model.repository;

import org.springframework.data.repository.CrudRepository;


import com.teste.api.model.entidades.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

}

//package com.teste.api.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.teste.api.model.dto.ItemCarrinhoDTO;
//import com.teste.api.model.entidades.Reservas;
//import com.teste.api.model.repository.ItemCarrinhoRepository;
//
//@Service
//public class ItemCarrinhoService {
//
//	@Autowired
//	private ItemCarrinhoRepository itemCarrinhoRepository;
//
//	@Autowired
//	private ModelMapper modelMapper;
//
//	public List<ItemCarrinhoDTO> obtertLista() {
//
//		List<Reservas> itemCarrinhos = this.itemCarrinhoRepository.findAll();
//
//		return itemCarrinhos.stream() // cria um fluxo de carrinho
//				.map(itemCarrinho -> modelMapper.map(itemCarrinho, ItemCarrinhoDTO.class)) // converte cada carrinho
//																							// para um carrinhoDTO
//				.collect(Collectors.toList()); // coleta tds os carrinho em uma lista
//	}
//
//	public ItemCarrinhoDTO obterCarrinhoPorId2(int id) {
//		Reservas carrinho = this.itemCarrinhoRepository.findById(id).orElse(null);
//
//		return modelMapper.map(carrinho, ItemCarrinhoDTO.class);
//
//	}
//
//	public Reservas obterCarrinhoPorId(int long1) {
//		return itemCarrinhoRepository.findById(long1).orElse(null);
//
//	}
//
//	public Reservas adicionaCarrinhho(Reservas carrinho) {
//		return itemCarrinhoRepository.save(carrinho);
//	}
//
//}

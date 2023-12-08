package com.teste.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.teste.api.model.dto.ItemCarrinhoDTO;
import com.teste.api.model.dto.ReservasDTO;
import com.teste.api.model.entidades.Reservas;
//import com.teste.api.service.ItemCarrinhoService;
import com.teste.api.service.ReservaService;
import com.teste.api.service.TokenService;
import com.teste.api.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private HttpSession session;

	@PostMapping
	public ResponseEntity<List<ReservasDTO>> criarReserva(@RequestHeader("Authorization") String token,
			@RequestBody Reservas reserva) {

		List<Reservas> reservaCriada = reservaService.adicionaAosMeusIngressos(reserva, token);

		List<ReservasDTO> reservaDTO = reservaService.retornaReservaDTO(reservaCriada);

//		List<ReservasDTO> reservasNaSessao = (List<ReservasDTO>) session.getAttribute("carrinho");
//
//		if (reservasNaSessao == null) {
//			reservasNaSessao = new ArrayList<>();
//		}
//
//		reservasNaSessao.addAll(reservaDTO);
//
//		session.setAttribute("carrinho", reservasNaSessao);

		if (reservaCriada == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			return ResponseEntity.ok(reservaDTO);
		}

	}

	@GetMapping("/carrinho")
	public ResponseEntity<?> getCarrinho(@RequestHeader("Authorization") String token) {
		// List<ReservasDTO> carrinhos = (List<ReservasDTO>)
		// session.getAttribute("carrinho");

		String loginUsuario = tokenService.validateToken(token);

		List<ReservasDTO> carrinhos = usuarioService.obterReservaDoUsuario(loginUsuario);

		if (carrinhos != null && !carrinhos.isEmpty()) {
			return ResponseEntity.ok(carrinhos);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Não há ingressos reservados");
		}
	}

	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> deleteCarrinho(@RequestParam int id) {

		reservaService.deleteReserva(id);

		return ResponseEntity.status(HttpStatus.OK).body("certo");
	}

//	@GetMapping
//	public ResponseEntity<List<ReservasDTO>> getListarItemCarrinho() {
//		List<ReservasDTO> reservas = reservaService.listaReserva();
//
//		return new ResponseEntity<List<ReservasDTO>>(reservas, HttpStatus.OK);
//	}

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

}

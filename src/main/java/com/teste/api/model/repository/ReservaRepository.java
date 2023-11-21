package com.teste.api.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.api.model.entidades.ItemCarrinho;
import com.teste.api.model.entidades.Usuario;

public interface ReservaRepository extends JpaRepository<ItemCarrinho, Integer> {

//	@Query("SELECT COALESCE(SUM(r.quantidadeReserva), 0) FROM Reserva r WHERE r.setor.id = :setorId") // soma quantidade de ingresso por setor e retorna um valor int
//	int contarReservasPorSetor3(@Param("setorId") int setorId);
	@Query("SELECT COALESCE(SUM(ic.quantidadeIngresso), 0) FROM ItemCarrinho ic " + "JOIN ic.ingressos i "
			+ "JOIN i.setor s " + "WHERE s.id = :setorId")
	int getTotalQuantidadePorSetor(@Param("setorId") int setorId);

	ItemCarrinho findByUsuarioAndIngressos_Id(Usuario usuario, int ingressoId);

}

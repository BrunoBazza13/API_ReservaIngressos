package com.teste.api.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.api.model.entidades.Reservas;
import com.teste.api.model.entidades.Usuario;

public interface ReservaRepository extends JpaRepository<Reservas, Integer> {


	@Query("SELECT COALESCE(SUM(ic.quantidadeIngresso), 0) FROM Reservas ic " + "JOIN ic.ingressos i "
			+ "JOIN i.setor s " + "WHERE s.id = :setorId")
	int getTotalQuantidadePorSetor(@Param("setorId") int setorId);

	Reservas findByUsuarioAndIngressos_Id(Usuario usuario, int ingressoId);

}

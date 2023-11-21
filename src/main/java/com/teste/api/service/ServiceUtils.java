package com.teste.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;

public class ServiceUtils {

	public static <T> Optional<T> atualizarEntidade(int id, T atualizacao, CrudRepository<T, Integer> repository) {
		return obterEntidadePorId(id, repository).map(entidade -> {
			BeanUtils.copyProperties(atualizacao, entidade, "id");
			return Optional.of(repository.save(entidade));
		}).orElse(Optional.empty());
	}

	public static <T> Optional<T> obterEntidadePorId(int id, CrudRepository<T, Integer> repository) {
		return repository.findById(id);
	}

}

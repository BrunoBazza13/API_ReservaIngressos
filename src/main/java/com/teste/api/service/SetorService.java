package com.teste.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.entidades.Setores;
import com.teste.api.model.repository.SetorRepository;

@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unused")
	private ServiceUtils serviceUtils;

	public Setores adicionaSetor(Setores setor) throws RepositoryNotInjectedException {
	  if (setorRepository == null) {
		throw new RepositoryNotInjectedException("SetorRepository não foi injetado");
	    }
		setorRepository.save(setor);
		return setor;
	}

	public Optional<Setores> obetemSetorPorId(int id) throws RepositoryNotInjectedException {
	  if (setorRepository == null) {
		 throw new RepositoryNotInjectedException("SetorRepository não foi injetado");
		}
		return setorRepository.findById(id);
	}

	public Setores obtemSetorPorIdDTO(int id) throws SetorNotFoundException {
	  Optional<Setores> optionalSetor = setorRepository.findById(id);
		   if (!optionalSetor.isPresent()) {
		       throw new SetorNotFoundException("Setor com id " + id + " não encontrado");
		   }
		Setores setor = setorRepository.findById(id).get();
		return modelMapper.map(setor, Setores.class);
	}

	public Optional<Setores> atualizaSetor(Setores setores) throws SetorNotFoundException, RepositoryNotInjectedException {
	   if (setores == null) {
   	     throw new SetorNotFoundException("Setor fornecido é não existe");
   	     } 
   	    if (setorRepository == null) {
   	     throw new RepositoryNotInjectedException("IngressoRepository não foi injetado");
   	 }
		return ServiceUtils.atualizarEntidade(setores.getId(), setores, setorRepository);
			
	}

	
	
	
}

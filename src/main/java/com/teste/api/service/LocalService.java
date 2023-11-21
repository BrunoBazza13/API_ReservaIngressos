package com.teste.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.api.exception.LocalNotFoundException;
import com.teste.api.exception.ModelMapperNotConfiguredException;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.model.dto.LocalDTO;
import com.teste.api.model.entidades.Local;
import com.teste.api.model.repository.LocalRepository;

@Service
public class LocalService {

	@Autowired
	private LocalRepository localRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unused")
	private ServiceUtils serviceUtils;

	public Local adicionaLocal(Local local) {
		return localRepository.save(local);
	}
	
	public Optional<Local> atualizaLocal(Local atualizaLocal) throws RepositoryNotInjectedException {// estudar mais sobre função lambda
		
     if (atualizaLocal == null) {
   	     throw new LocalNotFoundException("Local fornecido é não existe");
   	 }
   	 if (localRepository == null) {
   	     throw new RepositoryNotInjectedException("LocalRepositoryRepository não foi injetado");
   	 }
		return ServiceUtils.atualizarEntidade(atualizaLocal.getId(), atualizaLocal, localRepository);
				
	}
	
	
	public LocalDTO buscarLocalPorIdDTO(int id) {
		 Optional<Local> optionalLocal = localRepository.findById(id);
		
	  if (!optionalLocal.isPresent()) {
		   throw new LocalNotFoundException("Local com id " + id + " não encontrado");
		  
	  }
		Local local = localRepository.findById(id).get();
		return modelMapper.map(local, LocalDTO.class);
		
	}
	
	
	public List<LocalDTO> bucarLocal() throws RepositoryNotInjectedException, ModelMapperNotConfiguredException {
		
  	  if (localRepository == null) {
	      throw new RepositoryNotInjectedException("LocalRepository não foi injetado");
	  }
	  if (modelMapper == null) {
	      throw new ModelMapperNotConfiguredException("ModelMapper não foi configurado");
	  }

	  List<Local> local = this.localRepository.findAll();
	  return local.stream().map(ingresso -> modelMapper.map(local, LocalDTO.class))
	          .collect(Collectors.toList());
	}
		
	

}

package com.teste.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.api.exception.IngressoNotFoundException;
import com.teste.api.exception.ModelMapperNotConfiguredException;
import com.teste.api.exception.NomeIngressoSetorInvalidoException;
import com.teste.api.exception.RepositoryNotInjectedException;
import com.teste.api.exception.SetorNotFoundException;
import com.teste.api.model.dto.IngressoDTO;
import com.teste.api.model.entidades.Ingresso;
import com.teste.api.model.entidades.Setores;
import com.teste.api.model.repository.IngressoRepository;

@Service
public class IngressoService {

	@Autowired
	private IngressoRepository ingressoRepository;

	@Autowired
	private SetorService setorService;

	@Autowired
	private ModelMapper modelMApper;

	@SuppressWarnings("unused")
	private ServiceUtils serviceUtils;
	
	

	public Ingresso criaIngresso(Ingresso ingresso) throws NomeIngressoSetorInvalidoException, SetorNotFoundException, RepositoryNotInjectedException {

		Optional<Setores> setor = setorService.obetemSetorPorId(ingresso.getSetor().getId());


		   if (!setor.isPresent()) {
		       throw new SetorNotFoundException("Setor com id " + ingresso.getSetor().getId() + " não encontrado");
		   }

		   if (!ingresso.getNome().equalsIgnoreCase(setor.get().getNome())) {
		       throw new NomeIngressoSetorInvalidoException("O nome do ingresso não corresponde ao nome do setor");
		   }

		   return ingressoRepository.save(ingresso);
		}

    public IngressoDTO obterIngressoPorIdDTO(int id) {
		   Optional<Ingresso> optionalIngresso = ingressoRepository.findById(id);

		   if (!optionalIngresso.isPresent()) {
		       throw new IngressoNotFoundException("Ingresso com id " + id + " não encontrado");
		   }

		   Ingresso ingresso = optionalIngresso.get();
		   return modelMApper.map(ingresso, IngressoDTO.class);
     }
    
	public Optional<Ingresso> obterIngressoPorId(int id) {
		return ingressoRepository.findById(id);
	}
	

    public List<IngressoDTO> listarIngressos() throws RepositoryNotInjectedException, ModelMapperNotConfiguredException {
    	  if (ingressoRepository == null) {
    	      throw new RepositoryNotInjectedException("IngressoRepository não foi injetado");
    	  }

    	  if (modelMApper == null) {
    	      throw new ModelMapperNotConfiguredException("ModelMapper não foi configurado");
    	  }

    	  List<Ingresso> ingressos = this.ingressoRepository.findAll();

    	  return ingressos.stream().map(ingresso -> modelMApper.map(ingresso, IngressoDTO.class))
    	          .collect(Collectors.toList());
    	}



    public Optional<Ingresso> atualizaIngresso(Ingresso atualizaIngresso) throws IngressoNotFoundException, RepositoryNotInjectedException {
    	 
    	if (atualizaIngresso == null) {
    	     throw new IngressoNotFoundException("Ingresso fornecido é não existe");
    	 }
    	 if (ingressoRepository == null) {
    	     throw new RepositoryNotInjectedException("IngressoRepository não foi injetado");
    	 }
    	 return ServiceUtils.atualizarEntidade(atualizaIngresso.getId(), atualizaIngresso, ingressoRepository);
    	 
    	}


}

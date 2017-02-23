package com.droidablebee.springboot.rest.service;

import java.util.List;

import com.droidablebee.springboot.rest.domain.Parametrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.droidablebee.springboot.rest.repository.ParametrageRepository;

@Service
@Transactional
public class ParametrageService {

	@Autowired 
	private ParametrageRepository repository;

	@Transactional(readOnly = true)
	public Page<Parametrage> findAll(PageRequest request) {
		
		return repository.findAll(request);
	}
	
	@Transactional(readOnly = true)
	public List<Parametrage> findParametreWithLibelleLike(String libelle) {
		
		return repository.findParametreWithLibelleLike(libelle.toUpperCase());
	}
	
	@Transactional(readOnly = true)
	public Parametrage findOne(Long id) {
		
		return repository.findOne(id);
	}
	
	public Parametrage save(Parametrage parametrage) {
		
		return repository.saveAndFlush(parametrage);
	}
	
	public void delete(Long id) {
		
		repository.delete(id);
	}
}

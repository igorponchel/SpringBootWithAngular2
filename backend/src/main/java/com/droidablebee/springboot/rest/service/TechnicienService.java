package com.droidablebee.springboot.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.droidablebee.springboot.rest.domain.Technicien;
import com.droidablebee.springboot.rest.repository.TechnicienRepository;

@Service
@Transactional
public class TechnicienService {

	@Autowired
	private TechnicienRepository repository;

	@Transactional(readOnly = true)
	public Page<Technicien> findAll(Pageable pageable) {

		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Technicien findOne(Long id) {

		return repository.findOne(id);
	}

	public Technicien save(Technicien person) {

		return repository.saveAndFlush(person);
	}

	public Technicien findByUserNameAndPassword(String userName, String password) {

		return repository.findByUserNameAndPassword(userName, password);
	}

	public void delete(Long id) {

		repository.delete(id);
	}
}

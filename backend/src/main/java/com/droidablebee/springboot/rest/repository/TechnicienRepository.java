package com.droidablebee.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.droidablebee.springboot.rest.domain.Technicien;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {

	Technicien findByUserNameAndPassword(String login, String password);
}
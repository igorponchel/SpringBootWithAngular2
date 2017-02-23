package com.droidablebee.springboot.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Zone {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="zone_id")
	private Long id;

	@NotNull
	@Column(name="nom")
	private String nom;
	
	
	public Zone() {}

	public Zone(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}

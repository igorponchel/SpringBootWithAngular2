package com.droidablebee.springboot.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Parametrage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="parametrage_id")
    private Long id;

    @NotNull
    @Column(name="libelle")
    private String libelle;

    @NotNull
    @Column(name="valeur")
    private String valeur;

    @Column(name="commentaire")
    private String commentaire;

    protected Parametrage() {}
    
    

	public Parametrage(Long id, String libelle, String valeur, String commentaire) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.valeur = valeur;
		this.commentaire = commentaire;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

    
}
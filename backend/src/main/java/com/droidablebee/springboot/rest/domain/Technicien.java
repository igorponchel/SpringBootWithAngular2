package com.droidablebee.springboot.rest.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Technicien {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="technicien_id")
    private Long id;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;
   
    @NotNull
    @Column(name="user_name")
    private String userName;

    @NotNull
    @Column(name="password")
    private String password;
    
    @Column(name="mail")
    private String mail;
    
    @Column(name="sexe")
    private String sexe;
    
    @Valid
	@ManyToMany
	@JoinTable(name="tech_zones", joinColumns=@JoinColumn(name="tech_id", referencedColumnName="technicien_id"),
	inverseJoinColumns=@JoinColumn(name="zones_id", referencedColumnName="zone_id"))
    private Set<Zone> zones;
    
    @Column(name="qualification")
    private String qualification;
    
    @Column(name="adresse_postale")
    private String adressePostale;
    
    @Column(name="mobile")
    private boolean mobile;
    
    
	public Technicien() {
		super();
	}

	
	public Technicien(Long id, String firstName, String lastName, String userName, String password, String mail, String sexe,
			Set<Zone> zones, String qualification, String adressePostale, boolean mobile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.sexe = sexe;
		this.zones = zones;
		this.qualification = qualification;
		this.adressePostale = adressePostale;
		this.mobile = mobile;
	}

	public Technicien(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Set<Zone> getZones() {
		return zones;
	}

	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getAdressePostale() {
		return adressePostale;
	}

	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}


	public boolean getMobile() {
		return mobile;
	}


	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "Technicien [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", mail=" + mail + ", sexe=" + sexe + ", zones=" + zones
				+ ", qualification=" + qualification + ", adressePostale=" + adressePostale + ", mobile=" + mobile
				+ "]";
	}
	
}
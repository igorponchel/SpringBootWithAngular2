package com.droidablebee.springboot.rest.repository;

import java.util.List;

import com.droidablebee.springboot.rest.domain.Parametrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParametrageRepository extends JpaRepository<Parametrage, Long> {

	@Query("select p from Parametrage p where UPPER(p.libelle) like %?1%")
	public List<Parametrage> findParametreWithLibelleLike(String libelle);
}
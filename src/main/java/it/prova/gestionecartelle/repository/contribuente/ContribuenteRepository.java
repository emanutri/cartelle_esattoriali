package it.prova.gestionecartelle.repository.contribuente;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionecartelle.model.Contribuente;


public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository{

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);
}

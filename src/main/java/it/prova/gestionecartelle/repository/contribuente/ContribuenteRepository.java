package it.prova.gestionecartelle.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionecartelle.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository {

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);

	@Query("from Contribuente c join fetch c.cartelle where c.id = ?1")
	Contribuente findSingleContribuenteEager(Long id);
}

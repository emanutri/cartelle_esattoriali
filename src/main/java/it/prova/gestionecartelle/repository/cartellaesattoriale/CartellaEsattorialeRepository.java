package it.prova.gestionecartelle.repository.cartellaesattoriale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionecartelle.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository
		extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository {

	@Query("from CartellaEsattoriale c join fetch c.contribuente where c.id = ?1")
	CartellaEsattoriale findSingleCartellaEager(Long id);
}

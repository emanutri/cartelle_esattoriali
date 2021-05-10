package it.prova.gestionecartelle.repository.cartellaesattoriale;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionecartelle.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository
		extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository {

}

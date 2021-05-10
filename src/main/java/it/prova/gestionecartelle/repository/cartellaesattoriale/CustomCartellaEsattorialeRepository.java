package it.prova.gestionecartelle.repository.cartellaesattoriale;

import java.util.List;

import it.prova.gestionecartelle.model.CartellaEsattoriale;

public interface CustomCartellaEsattorialeRepository {

	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);

}

package it.prova.gestionecartelle.service;

import java.util.List;

import it.prova.gestionecartelle.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {

	public List<CartellaEsattoriale> listAllElements();

	public CartellaEsattoriale caricaSingoloElemento(Long id);

	public void aggiorna(CartellaEsattoriale cartellaEsattorialeInstance);

	public void inserisciNuovo(CartellaEsattoriale cartellaEsattorialeInstance);

	public void rimuovi(CartellaEsattoriale cartellaEsattorialeInstance);

	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);
}

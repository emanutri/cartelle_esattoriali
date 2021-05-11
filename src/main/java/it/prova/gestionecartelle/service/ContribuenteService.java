package it.prova.gestionecartelle.service;

import java.util.List;

import it.prova.gestionecartelle.model.Contribuente;

public interface ContribuenteService {

	public List<Contribuente> listAllElements();

	public Contribuente caricaSingoloElemento(Long id);

	public void aggiorna(Contribuente contribuenteInstance);

	public void inserisciNuovo(Contribuente contribuenteInstance);

	public void rimuovi(Contribuente contribuenteInstance);

	public List<Contribuente> findByExample(Contribuente example);

	public List<Contribuente> cercaByCognomeENomeILike(String term);

	public Contribuente caricaSingoloElementoEager(Long id);
}

package it.prova.gestionecartelle.repository.contribuente;

import java.util.List;

import it.prova.gestionecartelle.model.Contribuente;

public interface CustomContribuenteRepository {

	public List<Contribuente> findByExample(Contribuente example);
}

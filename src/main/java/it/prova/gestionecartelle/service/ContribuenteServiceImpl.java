package it.prova.gestionecartelle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecartelle.model.Contribuente;
import it.prova.gestionecartelle.repository.contribuente.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService {

	@Autowired
	private ContribuenteRepository repository;

	@Override
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>) repository.findAll();
	}

	@Override
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElementoEager(Long id) {
		return repository.findSingleContribuenteEager(id);
	}

	@Override
	public void aggiorna(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Override
	public void inserisciNuovo(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Override
	public void rimuovi(Contribuente contribuenteInstance) {
		repository.delete(contribuenteInstance);
	}

	@Override
	public List<Contribuente> findByExample(Contribuente example) {
		return repository.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Contribuente> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}

}

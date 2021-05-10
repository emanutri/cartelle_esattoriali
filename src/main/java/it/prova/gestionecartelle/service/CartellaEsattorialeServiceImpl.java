package it.prova.gestionecartelle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecartelle.model.CartellaEsattoriale;
import it.prova.gestionecartelle.repository.cartellaesattoriale.CartellaEsattorialeRepository;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {

	@Autowired
	private CartellaEsattorialeRepository repository;

	@Override
	public List<CartellaEsattoriale> listAllElements() {
		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Override
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findSingleCartellaEager(id);
	}

	@Override
	public void aggiorna(CartellaEsattoriale cartellaEsattorialeInstance) {
		repository.save(cartellaEsattorialeInstance);
	}

	@Override
	public void inserisciNuovo(CartellaEsattoriale cartellaEsattorialeInstance) {
		repository.save(cartellaEsattorialeInstance);
	}

	@Override
	public void rimuovi(CartellaEsattoriale cartellaEsattorialeInstance) {
		repository.delete(cartellaEsattorialeInstance);
	}

	@Override
	public void invalida(CartellaEsattoriale cartellaEsattorialeInstance) {
		repository.cambiaStato(cartellaEsattorialeInstance);
		repository.save(cartellaEsattorialeInstance);
	}

	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		return repository.findByExample(example);
	}

}

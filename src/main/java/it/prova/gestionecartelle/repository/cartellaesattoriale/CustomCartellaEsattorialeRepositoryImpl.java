package it.prova.gestionecartelle.repository.cartellaesattoriale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionecartelle.model.CartellaEsattoriale;
import it.prova.gestionecartelle.model.Stato;

public class CustomCartellaEsattorialeRepositoryImpl implements CustomCartellaEsattorialeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from CartellaEsattoriale c where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" c.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getImporto() != null) {
			whereClauses.add(" c.importo =:importo ");
			paramaterMap.put("importo", example.getImporto());
		}
		if (example.getStato() != null) {
			whereClauses.add(" c.stato =:stato ");
			paramaterMap.put("stato", example.getStato());
		}
		if (example.getContribuente() != null && example.getContribuente().getId() != null) {
			whereClauses.add(" c.contribuente = :contribuente ");
			paramaterMap.put("contribuente", example.getContribuente());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<CartellaEsattoriale> typedQuery = entityManager.createQuery(queryBuilder.toString(),
				CartellaEsattoriale.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public void cambiaStato(CartellaEsattoriale cartella) {
		cartella.setStato(Stato.INVALIDATA);
	}

}

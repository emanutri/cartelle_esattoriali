package it.prova.gestionecartelle.utility;

import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecartelle.model.CartellaEsattoriale;
import it.prova.gestionecartelle.model.Contribuente;
import it.prova.gestionecartelle.model.Stato;

public class UtilityCustom {

	public Double sommaTotCartelle(Contribuente contribuenteItem) {

		List<CartellaEsattoriale> cartelleContribuente = new ArrayList<>();
		Double somma = 0.0;

		cartelleContribuente = contribuenteItem.getCartelle();

		for (CartellaEsattoriale cartellaItem : cartelleContribuente) {
			if (!cartellaItem.getStato().equals(Stato.INVALIDATA)) {
				somma += cartellaItem.getImporto();
			}
			
		}
		return somma;

	}

	public Double sommaCartelleConclusePagate(Contribuente contribuenteItem) {

		List<CartellaEsattoriale> cartelleContribuente = new ArrayList<>();
		Double somma = 0.0;

		cartelleContribuente = contribuenteItem.getCartelle();

		for (CartellaEsattoriale cartellaItem : cartelleContribuente) {

			if (cartellaItem.getStato().equals(Stato.CONCLUSA)) {
				somma += cartellaItem.getImporto();
			}
		}
		return somma;
	}
	
	public Double sommaCartelleContenzioso(Contribuente contribuenteItem) {
		
		List<CartellaEsattoriale> cartelleContribuente = new ArrayList<>();
		Double somma = 0.0;

		cartelleContribuente = contribuenteItem.getCartelle();

		for (CartellaEsattoriale cartellaItem : cartelleContribuente) {

			if (cartellaItem.getStato().equals(Stato.IN_CONTENZIOSO)) {
				somma += cartellaItem.getImporto();
			}
		}
		return somma;
	}
}

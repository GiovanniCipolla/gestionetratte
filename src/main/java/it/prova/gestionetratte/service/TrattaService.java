package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaService {

	List<Tratta> listAllElements();
	
	List<Tratta> listAllElementsEager();
	
	List<Tratta> tratteAttive();
	
	Tratta caricaSingoloElemento(Long id,boolean eager);
	
	Tratta aggiorna(Tratta trattaInstance);
	
	Tratta inserisciNuovo(Tratta trattaInstance);
	
	void rimuovi(Long idToRemove);
	
	List<Tratta> findByExample(Tratta example);
}

package it.prova.gestionetratte.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

@Service
@Transactional
public class TrattaServiceImpl implements TrattaService {

	@Autowired
	private TrattaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tratta> listAllElements() {
		return (List<Tratta>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElemento(Long id, boolean eager) {
		if(eager)
			return repository.findSingleTrattaEager(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tratta aggiorna(Tratta trattaInstance) {
		return repository.save(trattaInstance);
	}

	@Override
	@Transactional
	public Tratta inserisciNuovo(Tratta trattaInstance) {
		return repository.save(trattaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> findByExample(Tratta example) {
		return repository.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> tratteAttive() {
		return repository.findAllTratteAttive();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> listAllElementsEager() {
		return repository.findAllTrattaEager();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> findByCodiceAndDescrizione(String codice, String descrizione) {
		return repository.findByCodiceAndDescrizione(codice, descrizione);
	}

	@Override
	@Transactional
	public List<Tratta> concludiTratte() {
		List<Tratta> tratteAttive = repository.findAllTratteAttive();
		for (Tratta trattaItem : tratteAttive) {
			LocalDateTime dateTimeAtterraggio = LocalDateTime.of(trattaItem.getData(), trattaItem.getOraAtterraggio());
			if (dateTimeAtterraggio.isBefore(LocalDateTime.now())) {
				trattaItem.setStato(Stato.CONCLUSA);
			}
		}
		repository.saveAll(tratteAttive);
		return tratteAttive;
	}
}

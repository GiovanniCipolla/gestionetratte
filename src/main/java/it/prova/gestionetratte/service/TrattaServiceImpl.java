package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

@Service
@Transactional(readOnly = true)
public class TrattaServiceImpl implements TrattaService {

	@Autowired
	private TrattaRepository repository;
	
	@Override
	public List<Tratta> listAllElements() {
		return (List<Tratta>) repository.findAll();
	}

	@Override
	public Tratta caricaSingoloElemento(Long id, boolean eager) {
		if(eager)
			return repository.findSingleTrattaEager(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public Tratta aggiorna(Tratta trattaInstance) {
		return repository.save(trattaInstance);
	}

	@Override
	public Tratta inserisciNuovo(Tratta trattaInstance) {
		return repository.save(trattaInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
		
	}

	@Override
	public List<Tratta> findByExample(Tratta example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Tratta> tratteAttive() {
		return repository.findAllTratteAttive();
	}

	@Override
	public List<Tratta> listAllElementsEager() {
		return repository.findAllTrattaEager();
	}

}

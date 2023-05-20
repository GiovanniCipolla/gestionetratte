package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
@Transactional(readOnly = true)
public class AirbusServiceImpl implements AirbusService {

	@Autowired
	private AirbusRepository repository;
	
	@Override
	public List<Airbus> listAllElements() {
		return (List<Airbus>) repository.findAll();
	}

	@Override
	public Airbus caricaSingoloElemento(Long id, boolean eager) {
		if(eager)
			return repository.findByIdEager(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public Airbus aggiorna(Airbus airbusInstance) {
		return repository.save(airbusInstance);
	}

	@Override
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		return repository.save(airbusInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
		
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Airbus> listAllElementsEager() {
		return repository.findAllEager();
	}

}

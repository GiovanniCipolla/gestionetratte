package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService {

	@Autowired
	private AirbusRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Airbus> listAllElements() {
		return (List<Airbus>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElemento(Long id, boolean eager) {
		if(eager)
			return repository.findByIdEager(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Airbus aggiorna(Airbus airbusInstance) {
		return repository.save(airbusInstance);
	}

	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		return repository.save(airbusInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> findByExample(Airbus example) {
		return repository.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> listAllElementsEager() {
		return repository.findAllEager();
	}


	@Override
	@Transactional(readOnly = true)
	public Airbus findByCodiceAndDescrizione(String codice, String descrizione) {
		return repository.findByCodiceAndDescrizione(codice, descrizione);
	}

	@Override
	public List<AirbusDTO> listaAirbusEvidenziandoSovrapposizioni() {
		List<AirbusDTO> listaAirbus = AirbusDTO.createAirbusDTOListFromModelList(repository.findAllEager(), true);

		for (AirbusDTO airbusItem : listaAirbus) {
			for (TrattaDTO trattaItem : airbusItem.getTratte()) {
				for (TrattaDTO trattaItem2 : airbusItem.getTratte()) {
					if (trattaItem.getData() == trattaItem2.getData() 
							&& (trattaItem2.getOraDecollo().isAfter(trattaItem.getOraDecollo())
							&& (trattaItem2.getOraDecollo().isBefore(trattaItem.getOraAtterraggio())))
							|| (trattaItem2.getOraAtterraggio().isAfter(trattaItem.getOraAtterraggio())
									&& trattaItem.getOraAtterraggio().isBefore(trattaItem2.getOraAtterraggio()))) {
						airbusItem.setConSovrapposizioni(true);
					}
				}
			}
		}
		return listaAirbus;
	}

}

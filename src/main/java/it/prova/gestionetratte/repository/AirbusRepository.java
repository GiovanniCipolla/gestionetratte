package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>,CustomAirbusRepository {

	@Query("select distinct from Airbus a left join fetch a.tratte where a.id=?1")
	Airbus findByIdEager(Long idAirbus);
	
	@Query("select distinct a from Airbus a left join fetch a.tratte")
	List<Airbus> findAllEager();
	
}

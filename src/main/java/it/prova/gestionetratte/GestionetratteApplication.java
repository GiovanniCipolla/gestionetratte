package it.prova.gestionetratte;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.service.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner{

	
	@Autowired
	private AirbusService airbusService;
	
	@Autowired
	private TrattaService trattaService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String codiceAirbus1 = "a01";
		String descrizioneAirbus1 = "AirItaly";
		Airbus airbus1 = airbusService.findByCodiceAndDescrizione(codiceAirbus1, descrizioneAirbus1);
		if (airbus1 == null) {
			airbus1 = new Airbus(codiceAirbus1, descrizioneAirbus1, LocalDate.now().minusDays(1), 45);
			airbusService.inserisciNuovo(airbus1);
		}
		
		Tratta BostonRoma = new Tratta("HJKGJ678", "Roma-Boston", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(10, 30), Stato.ATTIVA, airbus1);
		if (trattaService.findByCodiceAndDescrizione(BostonRoma.getCodice(), BostonRoma.getDescrizione()).isEmpty()) {
			trattaService.inserisciNuovo(BostonRoma);
		}
		
		String codiceAirbus2 = "b03";
		String descrizioneAirbus2 = "AirLingus";
		Airbus airbus2 = airbusService.findByCodiceAndDescrizione(codiceAirbus2, descrizioneAirbus2);
		if (airbus2 == null) {
			airbus2 = new Airbus(codiceAirbus2, descrizioneAirbus2, LocalDate.of(2020, 05, 19), 105);
			airbusService.inserisciNuovo(airbus2);
		}
		
		Tratta DublinParigi = new Tratta("KKOOP67", "Dublin-Parigi", LocalDate.of(2022, 12, 1), LocalTime.of(10, 0), LocalTime.of(18, 30), Stato.CONCLUSA, airbus2);
		if (trattaService.findByCodiceAndDescrizione(DublinParigi.getCodice(), DublinParigi.getDescrizione()).isEmpty()) {
			trattaService.inserisciNuovo(DublinParigi);
		}
	}

}

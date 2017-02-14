package org.did;


import java.util.Date;

import org.did.dao.ClientRepository;
import org.did.dao.CompteRepository;
import org.did.dao.OperationRepository;
import org.did.entities.Client;
import org.did.entities.Compte;
import org.did.entities.CompteCourant;
import org.did.entities.CompteEpargne;
import org.did.entities.Retrait;
import org.did.entities.Versement;
import org.did.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VotreBanqueApplication implements CommandLineRunner{
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	public static void main(String[] args)  {
		SpringApplication.run(VotreBanqueApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		/*
		Client c1 = clientRepository.save(new Client ("David","David@gmail.com"));
		Client c2 = clientRepository.save(new Client ("Kevin","Kevin@gmail.com"));
		Compte cp1 = compteRepository.save(
				new CompteCourant("c1", new Date(),9000.0,c1,6000));
		Compte cp2 = compteRepository.save(
				new CompteEpargne("c2", new Date(),400.0,c2,5.5));
		operationRepository.save(new Versement(new Date(),9000.0,cp1));
        operationRepository.save(new Versement(new Date(),5000.0,cp1));
        operationRepository.save(new Versement(new Date(),7000.0,cp1));
        operationRepository.save(new Retrait(new Date(),500.0,cp1));
        
        operationRepository.save(new Versement(new Date(),7000.0,cp2));
        operationRepository.save(new Versement(new Date(),6000.0,cp2));
        operationRepository.save(new Versement(new Date(),8000.0,cp2));
        operationRepository.save(new Retrait(new Date(),530.0,cp2));
		
		
		banqueMetier.verser("c1", 666666666666666.5); */
	}
}

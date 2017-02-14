package org.did.metier;

import java.util.Date;

import org.did.dao.CompteRepository;
import org.did.dao.OperationRepository;
import org.did.entities.Compte;
import org.did.entities.CompteCourant;
import org.did.entities.Operation;
import org.did.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired
      private CompteRepository compteRepository;
	@Autowired
	  private OperationRepository operationRepository;
	
	@Override
	public  Compte ConsulterCompte(String CodeCpte) {
		Compte cp = compteRepository.findOne(CodeCpte);
		if(cp==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}
//un versement
	@Override
	public void verser(String CodeCpte, Double Montant) {
		Compte cp = ConsulterCompte(CodeCpte);
		Versement v = new Versement(new Date(),Montant,cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+Montant);
		compteRepository.save(cp);
		
	}
//un retrait
	@Override
	public void retirer(String CodeCpte, Double Montant) {
		Compte cp = ConsulterCompte(CodeCpte);
		double facilitesCaisse = 0;
		if(cp instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse<Montant)
			throw new RuntimeException("solde insuffisant");
		Versement v = new Versement(new Date(),Montant,cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()-Montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String CodeCpte1, String CodeCpte2, Double Montant) {
		if(CodeCpte1.equals(CodeCpte2))
			throw new RuntimeException ("operation impossible ! virement vers le meme compte impossible"); 
		
		retirer(CodeCpte1,Montant);
		  verser(CodeCpte2, Montant);
		  
		
	}

	@Override
	public Page<Operation> listOperation(String CodeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.listOperation(CodeCpte, new PageRequest(page,size));
	}

}

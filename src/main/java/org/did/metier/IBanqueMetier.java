package org.did.metier;

import org.did.entities.Compte;
import org.did.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	public Compte ConsulterCompte(String CodeCpte);
	public void verser(String CodeCpte, Double Montant);
	public void retirer(String CodeCpte, Double Montant);
	public void virement(String CodeCpte1, String CodeCpte2, Double Montant);
	public Page<Operation> listOperation(String CodeCpte, int page, int size);
	
}

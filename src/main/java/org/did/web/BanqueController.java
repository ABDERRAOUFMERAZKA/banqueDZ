package org.did.web;

import org.did.entities.Compte;
import org.did.entities.Operation;
import org.did.metier.BanqueMetierImpl;
import org.did.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	@Autowired
IBanqueMetier ibanqueMetier;
	@RequestMapping(value="/Operations")
	public String Index(){
		return "comptes";
	}
	
	@RequestMapping(value="/help")
	public String help(){
		return "help";
	}
	
	
	@RequestMapping(value="/consulterCompte")
	public String consulter(Model model,String codeCompte, 
			@RequestParam(name="page",defaultValue="0")int page , 
			@RequestParam(name="size",defaultValue="4")int size){
		model.addAttribute("codeCompte",codeCompte);
		try{
		Compte cp = ibanqueMetier.ConsulterCompte(codeCompte);
		Page<Operation> pageOperation = ibanqueMetier.listOperation(codeCompte, page, size);
		model.addAttribute("listeOperations", pageOperation.getContent());// getContent permet de retourner la liste des opérations
		int [] pages = new int[pageOperation.getTotalPages()];//création d'un tableau des pages
		model.addAttribute("pages",pages);//ajouter le tableau au model
		model.addAttribute("compte",cp);
		}
		catch (Exception e){
			model.addAttribute("exception", e);
		}
		return "comptes";
	}
  	@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, 
			double montant, String codeCompte2 ){
  		try{
  			if(typeOperation.equals("VERS")){
  	  			ibanqueMetier.verser(codeCompte, montant);
  	  		}
  	  		else if (typeOperation.equals("RETR")){
  	  			ibanqueMetier.retirer(codeCompte, montant);
  	  		}
  	  		else if(typeOperation.equals("VIR")){
  	  			ibanqueMetier.virement(codeCompte, codeCompte2, montant);
  	  		}
  		}
  		catch (Exception e){
  			model.addAttribute("error",e);
  			return "redirect:/consulterCompte?codeCompte="+codeCompte
  					+"&error="+e.getMessage();
  		}
  		
  		return "redirect:/consulterCompte?codeCompte="+codeCompte ;
	}
}


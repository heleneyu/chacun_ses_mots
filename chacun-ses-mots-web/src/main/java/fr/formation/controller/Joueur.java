package fr.formation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.projet.IDAOJoueur;

@Controller
public class Joueur {

	@Autowired
	private IDAOJoueur daoJoueur;
	
	@GetMapping({"/connexion"})
	public String connexion(Model model) {
		
		return "csmConnexion";
	}
	
	@PostMapping({ "/connexion" })
	public String connexion(@Valid @ModelAttribute Joueur j, BindingResult result, Model model) {
		if (result.hasErrors()) {

			return "csmConnexion";
		}
		
//		daoJoueur.save(j);
		return "redirect:/csmJoueur";
	}
	
	@GetMapping({"/profil/{idJoueur}"})
	public String profil(@PathVariable int idJoueur, Model model) {
		
		return "csmProfil";
		
	}

}

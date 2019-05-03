package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.formation.projet.IDAOPartie;

@Controller
public class JeuController {
	
	private IDAOPartie daoPartie;
	
	@GetMapping({"/tour"})
	public String tourJoueur(Model model) {
		model.addAttribute("partie", 1);
		return "csmTourJoueur";
	}

}

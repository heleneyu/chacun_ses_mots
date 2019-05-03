package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.formation.projet.IDAOPartie;

@Controller
public class PartieController {

	@Autowired
	private IDAOPartie daoPartie;
	@GetMapping({"/partie-liste"})
	public String partieListe(Model model) {
		model.addAttribute("parties", daoPartie.findAll());
		return "csmPartieListe";
	}
	
}

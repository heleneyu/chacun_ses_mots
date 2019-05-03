package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JeuController {
	
	@GetMapping({"/tour"})
	public String tourJoueur(Model model) {
		return "csmTourJoueur";
	}

}

package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Controller
public class JeuController {
	
	@GetMapping
	public String tourJoueur(@PathVariable int idPartie,Model model) {
		return "csmTourCase";
	}

}

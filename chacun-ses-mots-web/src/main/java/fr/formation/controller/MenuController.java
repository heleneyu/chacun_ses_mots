package fr.formation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.formation.projet.IDAOPartie;

@Controller
public class MenuController {
	
	@Autowired
	private IDAOPartie daoPartie;
	
	@GetMapping({"/menu"})
	public String menu(Model model) {
		return "csmMenu";
	}
	
//	@PostMapping({"/menu"})
//	public String menu(@Valid @ModelAttribute Partie p, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			
//			return "csmMenu";
//		}
//		daoPartie.save(p);
//		return "redirect:/setgame";
//	}

}

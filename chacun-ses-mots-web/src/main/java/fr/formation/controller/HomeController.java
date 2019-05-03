package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/accueil", "", "/home"})
	public String home(String username, Model model) {
		
		return "csmHome";
	}

}

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

import fr.formation.model.Joueur;
import fr.formation.projet.IDAOJoueur;

@Controller
public class JoueurController {

	@Autowired
	private IDAOJoueur daoJoueur;

	@GetMapping({ "/connexion" })
	public String connexion(Model model) {

		return "csmConnexion";
	}

	@PostMapping({ "/connexion" })
	public String connexion(@Valid @ModelAttribute Joueur j, BindingResult result, Model model) {
		if (result.hasErrors()) {

			return "csmConnexion";
		}

		if (daoJoueur.findByPseudoAndMotDePasse(j.getPseudo(), j.getMotDePasse()).isPresent()) {
			return "redirect:/csmJoueur";
		}
		model.addAttribute("erreurConnexion", "Pseudo ou mot de passe invalide");
		return "csmConnexion";
	}

	@GetMapping({ "/profil/{idJoueur}" })
	public String profil(@PathVariable int idJoueur, Model model) {
		
		model.addAttribute(daoJoueur.findById(idJoueur));
		return "csmProfil";

	}

}

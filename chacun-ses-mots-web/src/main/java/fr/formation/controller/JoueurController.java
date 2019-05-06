package fr.formation.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

import fr.formation.model.Joueur;
import fr.formation.projet.IDAOJoueur;

@Controller
@SessionScope
public class JoueurController {

	@Autowired
	private IDAOJoueur daoJoueur;

	@GetMapping({ "/connexion" })
	public String connexion(Model model) {

		return "csmConnexion";
	}

	@PostMapping({ "/connexion" })
	public String connexion(@Valid @ModelAttribute Joueur j, BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {

			return "csmConnexion";
		}

		
		if (daoJoueur.findByPseudoAndMotDePasse(j.getPseudo(), j.getMotDePasse()).isPresent()) {
			Joueur joueur = daoJoueur.findByPseudoAndMotDePasse(j.getPseudo(), j.getMotDePasse()).get();
			session.setAttribute("joueur",joueur);
			daoJoueur.save(joueur);
			return "redirect:/profil/" + joueur.getId();
		}
		model.addAttribute("erreurConnexion", "Pseudo ou mot de passe invalide");
		return "csmConnexion";
	}

	
//	@ModelAttribute
//	public Joueur getJoueur(int id) {
//		return daoJoueur.findById(id).get();
//	}
	
	
	
	@GetMapping({ "/profil/{idJoueur}" })
	public String profil(@PathVariable int idJoueur, Model model) {
		
		model.addAttribute("joueur", daoJoueur.findById(idJoueur));
		return "csmProfil";

	}

}

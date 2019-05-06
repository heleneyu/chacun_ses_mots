package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Joueur;
import fr.formation.model.Partie;
import fr.formation.projet.IDAOJoueur;
import fr.formation.projet.IDAOPartie;

@Controller
public class VoteController {

	@Autowired
	private IDAOJoueur daoJoueur;
	@Autowired
	private IDAOPartie daoPartie;

	@GetMapping({ "/vote" })
	public String pageVote(@RequestParam(required = false, defaultValue = "0") int id, HttpSession session,
			Model model) {
		if (id != 0) {
			Joueur j = daoJoueur.findById(id).get();
			j.setScore(j.getScore() + 1);
			daoJoueur.save(j);
		}
		Partie p = (Partie) session.getAttribute("partie");
		if(id != 0) {
			p = daoPartie.findById(p.getId()).get();
			p.setVotes(p.getVotes() + 1);
			if(p.getVotes() == p.getNbJoueur()) {
				daoPartie.save(p);
			}
			daoPartie.save(p);
		}
		p = daoPartie.findByIdWithJoueurs(p.getId());
		model.addAttribute("joueurs", p.getJoueurs());
		if(p.getVotes() == p.getNbJoueur()) {
			model.addAttribute("tousVotes", true);
		}
		else {
			model.addAttribute("tousVotes", false);

		}
		return "csmScore";
	}

}

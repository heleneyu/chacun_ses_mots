package fr.formation.controller;



import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Joueur;
import fr.formation.model.Partie;
import fr.formation.model.Question;
import fr.formation.projet.IDAOJoueur;
import fr.formation.projet.IDAOPartie;
import fr.formation.projet.IDAOQuestion;

@Controller
public class MenuController {

	@Autowired
	private IDAOPartie daoPartie;
	
	@Autowired
	private IDAOJoueur daoJoueur;
	
	@Autowired
	private IDAOQuestion daoQuestion;

	@GetMapping({ "/menu" })
	public String menu(Model model) {
		return "csmMenu";
	}

	@PostMapping({ "/menu" })
	public String menu(@Valid @ModelAttribute Partie p, BindingResult result, Model model) {
		if (result.hasErrors()) {

			return "csmMenu";
		}
		daoPartie.save(p);
		return "redirect:/setgame";
	}

	@GetMapping({"/join","/rejoindre"})
	public String rejoindre(int id, Model model, HttpSession session) {
		Partie p = daoPartie.findById(id).get();
		if(session.getAttribute("joueur") != null) {
			p.getJoueurs().add((Joueur) session.getAttribute("joueur"));
			daoPartie.save(p);
			daoJoueur.save((Joueur)session.getAttribute("joueur"));
			
		return "redirect:/tour";
				}
		return "csmHome";
	}
	
	@GetMapping({"/setgame"})
	public String setGame(Model model
			,HttpSession session,
			@RequestParam String name,
			@RequestParam int modeA, @RequestParam int nbJoueurs, @RequestParam int nbTours, @RequestParam int modeJeu
			) {
		
		Partie p = Partie.creerPartie(modeA, nbJoueurs, nbTours, modeJeu, 0);

//		if(session.getAttribute("joueur") == null) {
//			Random random = new Random();
//			int number = random.nextInt();
//			while (number < 100) {
//				number = random.nextInt();
//			}
//			Joueur j = Joueur.creerJoueur(name);
//			p.setId(number);
//			j.setId(number);
//			session.setAttribute("joueur", j);
//		}
//		else {
		p.getJoueurs().add((Joueur) session.getAttribute("joueur"));
		Random r = new Random();
		List<Question> q = daoQuestion.findAll();
		p.setQuestionEnCours(q.get(r.nextInt(q.size())));
		p.getQuestionEnCours().setNbInput();
		((Joueur) session.getAttribute("joueur")).setId( daoPartie.save(p).getId());
//		}
		return "redirect:/tour";
	}
	

}

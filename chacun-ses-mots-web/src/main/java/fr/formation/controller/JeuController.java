package fr.formation.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.formation.model.Joueur;
import fr.formation.model.Partie;
import fr.formation.model.Reponse;
import fr.formation.projet.IDAOJoueur;
import fr.formation.projet.IDAOPartie;
import fr.formation.projet.IDAOReponse;

@Controller
@SessionAttributes("sessionJoueur")
public class JeuController {
	
	@Autowired
	private IDAOPartie daoPartie;
	@Autowired
	private IDAOJoueur daoJoueur;
	@Autowired
	private IDAOReponse daoReponse;
	

	
	@GetMapping({"/tour"})
	public String tourJoueur(
			HttpSession session,
			Model model) {
		Joueur j = (Joueur) session.getAttribute("joueur");
		Partie p = j.getPartie();

		model.addAttribute("partie", p);
		List<Reponse> r = daoReponse.findAll();
		int s = r.size();
		final int[] ints = new Random().ints(1, s)
				.distinct().limit(4).toArray();
		List<Reponse> main = new ArrayList<Reponse>();
		for (int i : ints) {
			main.add(r.get(i));
		}
		model.addAttribute("main", main);
		return "csmTourJoueur";
		
	}
	
	@GetMapping({"/jouer"})
	public String tourJoueurFini(
			@RequestParam(value="carteids", required=false, defaultValue="") int[] ids, 
			@RequestParam(value="textes", required=false, defaultValue="") String[] textes,
			HttpSession session,
			Model model) {
//		session.getAttribute("joueur").getReponsesEcrites.add(session.getAttribute("joueur").getMain().get(r1).getDonnee());		
//		session.getAttribute("joueur").getMain().clear();
//		for (int i2 = 0; i2 < 4; i2++) {
//			// Si on a pas assez de cartes propositions
//			if (p.getPaquetR().size() < 5) {
//				p.setPaquetR(melangerPaquetProposition(daoReponse));
//			}
//			j.getMain().add(p.getPaquetR().remove(0));
		
		Joueur j = (Joueur) session.getAttribute("joueur");
		Partie p = j.getPartie();
		//si le champ libre est remplis
		for(int i=0; i<p.getQuestionEnCours().getNbInput(); i++) {
			j.getReponsesEcrites().add(textes[i]);
		}
		//si le joueur a choisi une ou plusieurs cartes reponse
		for(int i=0; i<p.getQuestionEnCours().getNbInput(); i++) {
			j.getCartesJouee().add(daoReponse.findById(ids[i]).get());
		}
		
		return "csmVoteJoueur";
	}
	
	

}

package fr.formation.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

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
		
		Joueur j = (Joueur) session.getAttribute("joueur");
		Partie p = (Partie) session.getAttribute("partie");
		p = daoPartie.findByIdWithJoueurs(p.getId());
		
		for(int i=0; i<p.getQuestionEnCours().getNbInput() +1 ; i++) {
			if(ids.length != 0) {//si le joueur a choisi une ou plusieurs cartes reponse
				j.getReponsesEcrites().add(daoReponse.findById(ids[i]).get().getDonnee());
				System.out.println("reponse écrite "+i);
				System.out.println(daoReponse.findById(ids[i]).get().getDonnee());
			}
			else {//si le champ libre est remplis
				j.getReponsesEcrites().add(textes[i]);
				System.out.println("reponse texte "+i);
				System.out.println(textes[i]);
			}
		}
		System.out.println(p.reponse(j));
		daoJoueur.save(j);
		model.addAttribute("partie", p);
		model.addAttribute("joueur", j);
		return "csmVoteJoueur";
	}
	
	

}

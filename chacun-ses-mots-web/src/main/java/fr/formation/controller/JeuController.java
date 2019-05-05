package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionJoueur")
public class JeuController {
	
//	@Autowired
//	private IDAOPartie daoPartie;
//	@Autowired
//	private IDAOJoueur daoJoueur;
	

	
	@GetMapping({"/tour"})
	public String tourJoueur(
			HttpSession session,
			Model model) {
		
		model.addAttribute("partie", 1
//		daoPartie.findById(session.getAttribute("partie").getId());	
//		model.addAttribute("questions",daoQuestion.findAll());		
//		model.addAttribute("joueur",session.getAttribute("joueur"));
//		model.addAttribute("reponses",daoReponses.finAll();
				);
		return "csmTourJoueur";
	}
	
	@GetMapping({"/jouer"})
	public String tourJoueurFini(
			@RequestParam(value="r1", required=true, defaultValue="") int r1, @RequestParam int r2, @RequestParam int r3,
			HttpSession session,
			Model model) {
//		session.getAttribute("joueur").getReponsesEcrites.add(session.getAttribute("joueur").getMain().get(r1).getDonnee());		
//		session.getAttribute("joueur").getMain().clear();
//		for (int i2 = 0; i2 < 4; i2++) {
//			// Si on a pas assez de cartes propositions
//			if (p.getPaquetR().size() < 5) {
//				p.setPaquetR(melangerPaquetProposition(daoCarteReponse));
//			}
//			j.getMain().add(p.getPaquetR().remove(0));
		
		return "csmVoteJoueur";
	}
	
	

}

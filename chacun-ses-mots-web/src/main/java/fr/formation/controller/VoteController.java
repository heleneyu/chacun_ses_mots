package fr.formation.controller;

//import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VoteController {
	
//	@Autowired
//	private IDAOJoueur daoJoueur;
//	@Autowired
//	private IDAOPartie daoPartie;

	
	@GetMapping({"/vote"})
	public String pageVote(
			//HttpSession session, 
			Model model) {
//		model.addAttribute("joueur", session.getAttribute("joueur"));
		return "csmVoteJoueur";
	}
	
	@GetMapping({"/vote/{id}"})
	public String vote(@PathVariable int id 
			//, HttpSession session
			) {
		

		return "csmVoteJoueur";
	}
	
}

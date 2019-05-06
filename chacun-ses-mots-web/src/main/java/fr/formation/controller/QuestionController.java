package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.model.Question;
import fr.formation.projet.IDAOQuestion;

@Controller
//@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private IDAOQuestion daoQuestion;
	
	@GetMapping("/question/{idquestion}")
	public String question(Model model, @PathVariable int idquestion) {
		model.addAttribute("question", daoQuestion.findById(idquestion).get());
		return "csmQuestion";
	}
	
	@GetMapping({"/liste-question", "/liste-question/{idquestion}"})
	public String listequestion(Model model) {
		model.addAttribute("questions", daoQuestion.findAll());
		return "csmListe-question";
	}
	
	@GetMapping({"/supprimer-question","/supprimer-question/{idquestion}"})
	public String supprimerquestion(@PathVariable int idquestion) {
		daoQuestion.deleteById(idquestion);
		return "redirect:/liste-question";
	}

	@GetMapping("/ajouter-question")
	public String ajouterquestion() {
		return "csmAjouter-question";
	}
	@PostMapping("/ajouter-question")
	public String ajouterquestion(@ModelAttribute Question p) {
		daoQuestion.save(p);
		return "redirect:/liste-question";
	}
	
	@GetMapping({"/modifier-question/", "/modifier-question/{idquestion}"})
	public String modifierquestion(Model model, @PathVariable int idquestion) {
		model.addAttribute("questionM", daoQuestion.findById(idquestion).get());
		return "csmAjouter-question";
	}
	@PostMapping({"/modifier-question/", "/modifier-question/{idquestion}"})
	public String modifierquestionPost(@ModelAttribute Question q, @PathVariable int idquestion) {
		q.setId(idquestion);
		daoQuestion.save(q);
		return "redirect:/liste-question";
	}
}

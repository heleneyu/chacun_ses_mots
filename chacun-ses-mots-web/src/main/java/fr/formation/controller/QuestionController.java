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
	
	@GetMapping("/csmQuestion/{idquestion}")
	public String question(Model model, @PathVariable int idquestion) {
		model.addAttribute("question", daoQuestion.findById(idquestion).get());
		return "csmQuestion";
	}
	
	@GetMapping("/csmListe-question")
	public String listequestion(Model model) {
		model.addAttribute("questions", daoQuestion.findAll());
		return "csmListe-question";
	}
	
	@GetMapping({"/csmSupprimer-question","/csmSupprimer-question/{idquestion}"})
	public String supprimerquestion(@PathVariable int idquestion) {
		daoQuestion.deleteById(idquestion);
		return "redirect:/csmListe-question";
	}

	@GetMapping("/csmAjouter-question")
	public String ajouterquestion() {
		return "csmAjouter-question";
	}
	@PostMapping("/csmAjouter-question")
	public String ajouterquestion(@ModelAttribute Question p) {
		daoQuestion.save(p);
		return "redirect:/csmListe-question";
	}
	
	@GetMapping({"/csmModifier-question/", "/csmModifier-question/{idquestion}"})
	public String modifierquestion(Model model, @PathVariable int idquestion) {
		model.addAttribute("questionM", daoQuestion.findById(idquestion).get());
		return "csmAjouter-question";
	}
	@PostMapping({"/csmModifier-question/", "/csmModifier-question/{idquestion}"})
	public String modifierquestionPost(@ModelAttribute Question f, @PathVariable int idquestion) {
		f.setId(idquestion);
		daoQuestion.save(f);
		return "redirect:/csmListe-question";
	}
}

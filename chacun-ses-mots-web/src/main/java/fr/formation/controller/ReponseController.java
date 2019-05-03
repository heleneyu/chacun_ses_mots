package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.model.Reponse;
import fr.formation.projet.IDAOReponse;

@Controller
//@RequestMapping("/reponse")
public class ReponseController {
	@Autowired
	private IDAOReponse daoreponse;
	
	@GetMapping("/csmReponse/{idreponse}")
	public String reponse(Model model, @PathVariable int idreponse) {
		model.addAttribute("reponse", daoreponse.findById(idreponse).get());
		return "csmReponse";
	}
	
	@GetMapping("/csmListe-reponse")
	public String listereponse(Model model) {
		model.addAttribute("reponses", daoreponse.findAll());
		return "csmListe-reponse";
	}
	
	@GetMapping({"/csmSupprimer-reponse","/csmSupprimer-reponse/{idreponse}"})
	public String supprimerreponse(@PathVariable int idreponse) {
		daoreponse.deleteById(idreponse);
		return "redirect:/csmListe-reponse";
	}

	@GetMapping("/csmAjouter-reponse")
	public String ajouterreponse() {
		return "csmAjouter-reponse";
	}
	@PostMapping("/csmAjouter-reponse")
	public String ajouterreponse(@ModelAttribute Reponse p) {
		daoreponse.save(p);
		return "redirect:/csmListe-reponse";
	}
	
	@GetMapping({"/csmModifier-reponse/", "/csmModifier-reponse/{idreponse}"})
	public String modifierreponse(Model model, @PathVariable int idreponse) {
		model.addAttribute("reponseM", daoreponse.findById(idreponse).get());
		return "csmAjouter-reponse";
	}
	@PostMapping({"/csmModifier-reponse/", "/csmModifier-reponse/{idreponse}"})
	public String modifierreponsePost(@ModelAttribute Reponse f, @PathVariable int idreponse) {
		f.setId(idreponse);
		daoreponse.save(f);
		return "redirect:/csmListe-reponse";
	}
}

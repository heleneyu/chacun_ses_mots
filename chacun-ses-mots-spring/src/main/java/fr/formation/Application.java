package fr.formation;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.model.Question;
import fr.formation.model.Reponse;
import fr.formation.projet.IDAOJoueur;
import fr.formation.projet.IDAOPartie;
import fr.formation.projet.IDAOQuestion;
import fr.formation.projet.IDAOReponse;

public class Application {
	@Autowired
	private IDAOQuestion daoQuestion;
	@Autowired
	private IDAOReponse daoReponse;
	@Autowired
	private IDAOJoueur daoJoueur;
	@Autowired
	private IDAOPartie daoPartie;

	public void run(String[] args) {
		for (Question q : daoQuestion.findAll()) {
			System.out.println(q.getDonnee());
		}
	}
	
	public static ArrayList<Question> melangerPaquetQuestion(IDAOQuestion daoQuestion) {
		// Creation du paquet de questions
		ArrayList<Question> paquetQuestion = new ArrayList<Question>();
		for (Question c : daoQuestion.findAll()) {
			paquetQuestion.add(c);
			c.setNbInput();
		}
		Collections.shuffle(paquetQuestion);
		return paquetQuestion;
	}

	public static ArrayList<Reponse> melangerPaquetProposition(IDAOReponse daoReponse) {
		// creation du paquet de proposition
		ArrayList<Reponse> paquetReponse = new ArrayList<Reponse>();
		for (Reponse c : daoReponse.findAll()) {
			paquetReponse.add(c);
		}
		Collections.shuffle(paquetReponse);
		return paquetReponse;
	}
}

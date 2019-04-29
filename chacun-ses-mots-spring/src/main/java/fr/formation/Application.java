package fr.formation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.model.Joueur;
import fr.formation.model.Partie;
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
	
	public static void initialiserPartie(int nbJoueur, int nbTour, int modeJeu, int modeDonnee, IDAOReponse daoReponse, IDAOQuestion daoQuestion, IDAOPartie daoPartie) {

		Partie partie = Partie.creerPartie(modeDonnee, nbJoueur, nbTour, modeJeu, 0);
		daoPartie.save(partie);
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
	
	
	public static boolean ajouterJoueurAPartie(String pseudo, String mdp, IDAOJoueur daoPersonnes, Partie p) {
		Optional<Joueur> j = daoPersonnes.findByPseudoAndMotDePasse(pseudo, mdp);
		if(j.isPresent()) {
			p.getJoueurs().add(j.get());
			return true;
		}
		return false;
	}
	
	public static Joueur inscriptionJoueur(IDAOJoueur daoPersonnes, String nom, String prenom, String pseudo, String mdp) {
		Joueur j = new Joueur();
		j.setNom(nom);
		j.setPrenom(prenom);
		j.setPseudo(pseudo);
		j.setMotDePasse(mdp);
		j.setType("joueur");
		daoPersonnes.save(j);
		return j;

	}
	
}

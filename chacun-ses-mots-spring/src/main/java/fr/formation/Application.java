package fr.formation;

import org.springframework.beans.factory.annotation.Autowired;

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

	}
}

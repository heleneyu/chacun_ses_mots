package fr.formation.projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.formation.projet.dao.DAOCarteQuestionSQL;
import fr.formation.projet.dao.DAOCarteReponseSQL;
import fr.formation.projet.dao.DAOPersonneSQL;
import fr.formation.projet.dao.IDAOCarte;
import fr.formation.projet.dao.IDAOJoueur;

public class Application {

	public static void main(String[] args) {
	
//		String p = "Par exemple une ______ qui se termine comme ____";
//		if ("_ma phrase".startsWith("_")) {
//			p = " " + p;
//		}
//		
//		if ("ma phrase_".endsWith("_")) {
//			p += ".";
//		}
//		//LIKE '%_'
//		//LIKE '_%'
//		String[] a = p.split("_+");
//		
//		
//		System.out.println(a.length);
//		System.exit(0);

		Scanner sc = new Scanner(System.in);
		boolean wait = true;
		int nbJoueur = 0;
		int nbTour = 0;
		int modeJeu = 0;
		int modeDonnee = 0;

		IDAOCarte daoCarteQuestion = new DAOCarteQuestionSQL();
		IDAOCarte daoCarteReponse = new DAOCarteReponseSQL();
		IDAOJoueur daoPersonnes = new DAOPersonneSQL();

		// INSCRIPTION PROPOSEE
		boolean inscription = true;
		do {
			System.out.println("Inscription (1) ou Lancer partie (2) ou Quitter (3)");
			String start = "";
			start = sc.next();

			if (start.equals("1")) {
				inscriptionJoueur(daoPersonnes, sc);
			} else if (start.equals("3")) {
				return;
			} else {
				inscription = false;
			}
		} while (inscription == true);

		// définir les valeurs de la partie
		while (wait) {
			System.out.println("Selectionner le nombre de joueurs.");
			String s = sc.next();
			nbJoueur = Integer.parseInt(s);
			System.out.println("Selectionner le nombre de tour.");
			s = sc.next();
			nbTour = Integer.parseInt(s);
			System.out.println(
					"Selectionner le mode de jeu (1 pour mode Remplissage, 2 pour mode Proposition et 3 pour mode LesDeux)");
			s = sc.next();
			modeJeu = Integer.parseInt(s);
			System.out.println("Valider votre choix ( 1-OK; 0-Redo)");
			s = sc.next();
			int valide = Integer.parseInt(s);
			if (valide == 1) {
				wait = false;
			}
		}

		ArrayList<Carte> paquetQuestions = melangerPaquetQuestion(daoCarteQuestion);
		ArrayList<Carte> paquetPropositions = melangerPaquetProposition(daoCarteReponse);

		// Creer partie
		Partie partie = Partie.creerPartie(modeDonnee, nbJoueur, nbTour, modeJeu, 0);
		partie.setPaquetQ(paquetQuestions);
		partie.setPaquetR(paquetPropositions);

		ArrayList<Joueur> groupe = new ArrayList<Joueur>();
		for (int i = 1; i < nbJoueur + 1; i++) {
			// CONNECTION
			boolean saisie = false;
			boolean nonexist = true;
			while (saisie == false) {
				nonexist = true;
				System.out.println("Joueur " + i);
				System.out.println(
						"Se connecter en tant qu'utilisateur (1), en tant qu'anonyme (0) ou entrez votre pseudo ?");
				String u = sc.next();
				String pseudo = "";
				if (Pattern.matches("^1$", u)) {
					Joueur j = tentativeConnexionJoueur(sc, i, daoPersonnes, groupe, nonexist);
					if (j.getId() != 0 && !j.getMotDePasse().equals("")) {
						groupe.add(j);
						System.out.println(j.getPseudo());
						saisie = true;
					}
				}  else if (Pattern.matches("^0$", u)) { // pseudo anonyme
					pseudo = "Joueur " + String.valueOf(i);
					groupe.add(Joueur.creerJoueur(pseudo));
					System.out.println(pseudo);
					saisie = true;
				} else if (!u.equals("")) { // pseudo pour la partie
					groupe.add(Joueur.creerJoueur(u));
					System.out.println(u);
					saisie = true;
				}
			}
			

		}
		System.out.println("Fin création joueurs");
		partie.setGroupe(groupe);

		afficherJoueurs(groupe);

		lancerPartie(partie, daoCarteReponse, daoCarteQuestion, sc);

		sc.close();

	}// fin main

	public static void afficherJoueurs(ArrayList<Joueur> groupe) {
		System.out.println("Liste des joueurs : ");
		for (Joueur j : groupe) {
			System.out.println(j.getPseudo());
		}
	}

	public static ArrayList<Carte> melangerPaquetQuestion(IDAOCarte daoCarteQuestion) {
		// Creation du paquet de questions
		ArrayList<Carte> paquetQuestion = new ArrayList<Carte>();
		for (Carte c : daoCarteQuestion.findAll()) {
			paquetQuestion.add(c);
			c.setNbInput(c.getDonnee().split("_+").length);
		}
		Collections.shuffle(paquetQuestion);
		return paquetQuestion;
	}

	public static ArrayList<Carte> melangerPaquetProposition(IDAOCarte daoCarteReponse) {
		// creation du paquet de proposition
		ArrayList<Carte> paquetReponse = new ArrayList<Carte>();
		for (Carte c : daoCarteReponse.findAll()) {
			paquetReponse.add(c);
		}
		Collections.shuffle(paquetReponse);
		return paquetReponse;
	}

	public static void lancerPartie(Partie p, IDAOCarte daoCarteReponse, IDAOCarte daoCarteQuestion, Scanner sc) {

		// Pour chaque Tour
		for (int x = 0; x < p.getNbTour(); x++) {
			System.out.println("Tour " +(x+1)+ " sur " + p.getNbTour() +" :");
			// Si on a pas assez de cartes propositions
			if (p.getPaquetR().size() <= (4 * p.getNbJoueur())) {
				for (Carte c : daoCarteReponse.findAll()) {
					p.getPaquetR().add(c);
				}
				Collections.shuffle(p.getPaquetR());
			}
			// Si on a pas assez de cartes questions
			if (p.getPaquetR().size() < 1) {
				for (Carte c : daoCarteQuestion.findAll()) {
					p.getPaquetQ().add(c);
					if (c.getDonnee().endsWith("_")) {
						c.setDonnee(c.getDonnee()+ ".");
					}
					c.setNbInput(c.getDonnee().split("_+").length);
				}
				Collections.shuffle(p.getPaquetQ());
			}
			// on cree le tour
			// Collections.shuffle(paquetProposition);
			Tour tour = Tour.creerTour(1, p.getPaquetQ().remove(0), p);
			if (p.getModeSaisie() == 2) {
				for (Joueur j : p.getGroupe()) { // on donne des cartes proposition aux joueurs
					for (int i = 0; i < 4; i++) {
						j.getProposition().add(p.getPaquetR().remove(0));
					}
				}
				tour.afficheQuestionMode2(sc);
			} else if (p.getModeSaisie() == 3) {
				for (Joueur j : p.getGroupe()) { // on donne des cartes proposition aux joueurs
					for (int i = 0; i < 4; i++) {
						j.getProposition().add(p.getPaquetR().remove(0));
					}
				}
				tour.afficheQuestionMode3(sc);
			} else {
				tour.afficheQuestion(sc); // on affiche la question
			}
			//tour.afficheReponse(sc);
			tour.vote(sc);
			tour.afficheScore(sc);
			for (Joueur j : p.getGroupe()) {
				j.getProposition().clear();
			}
		}
		
		System.out.println("Le jeu est terminé. Merci d'avoir joué");
		System.out.println("Le gagnant est ");
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for(Joueur j: p.getGroupe()) {
		scores.add(j.getScore());
		}
		Integer maxScore = Collections.max(scores);
		for(Joueur j: p.getGroupe()) {
			if(j.getScore() == maxScore) {
				System.out.println(j.getPseudo());
			}
		}
	}

	public static boolean dejaPris(ArrayList<Joueur> g, int id) {
		for (Joueur t : g) {
			if (t.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public static Joueur tentativeConnexionJoueur(Scanner sc, int i, IDAOJoueur daoPersonnes, ArrayList<Joueur> groupe,
			boolean nonexist) {

		int id = 0;
		do {
			System.out.println("Joueur " + i + " entrez votre identifiant"); // ou sur son nom oou son id ?
			String sid = sc.next();
			try {
				id = Integer.parseInt(sid);
			} catch (Exception e) {
				id = 0;
			}
		} while (daoPersonnes.findById(id).getId() == 0 || dejaPris(groupe, id));

		Joueur joueur = daoPersonnes.findById(id);

		// verification du mot de passe
		boolean mdpok = false;
		int cpt = 0;
		while (!mdpok && joueur.getId() != 0 && cpt < 3) {
			System.out.println("Joueur " + i + " entrez votre mot de passe"); // ou sur son nom ou sonid ?
			String mdp = sc.next();
			if (mdp.equals(joueur.getMotDePasse())) { // verification du mot de passe
				mdpok = true;
			}
			cpt++;
		}

		return joueur;

	}

	public static Joueur inscriptionJoueur(IDAOJoueur daoPersonnes, Scanner sc) {
		Joueur j = new Joueur();
		System.out.println("Inscription Joueur");
		System.out.println("Entrez votre Nom");
		j.setNom(sc.next());
		System.out.println("Entrez votre Prenom");
		j.setPrenom(sc.next());
		System.out.println("Entrez votre Pseudo");
		j.setPseudo(sc.next());
		System.out.println("Entrez votre mot de passe");
		j.setMotDePasse(sc.next());
		j.setType("joueur");
		daoPersonnes.save(j);
		return j;

	}

}

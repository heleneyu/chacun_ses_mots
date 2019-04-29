
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.formation.model.Joueur;
import fr.formation.model.Partie;
import fr.formation.model.Question;
import fr.formation.model.Reponse;
import fr.formation.projet.DAOCarteQuestionHibernate;
import fr.formation.projet.DAOCarteReponseHibernate;
import fr.formation.projet.DAOPersonneHibernate;
import fr.formation.projet.IDAOCarteQuestion;
import fr.formation.projet.IDAOCarteReponse;
import fr.formation.projet.IDAOJoueur;

public class Application {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean wait = true;
		int nbJoueur = 0;
		int nbTour = 0;
		int modeJeu = 0;
		int modeDonnee = 0;

		IDAOCarteQuestion daoCarteQuestion = new DAOCarteQuestionHibernate();
		IDAOCarteReponse daoCarteReponse = new DAOCarteReponseHibernate();
		IDAOJoueur daoPersonnes = new DAOPersonneHibernate();


		// INSCRIPTION PROPOSEE
		boolean inscription = true;
		do {
			System.out.println("Lancer partie (1)");
			System.out.println("Inscription (2)");
			System.out.println("Se connecter en tant qu'administrateur (3)");
			System.out.println("Quitter (4)");
			String start = "";
			start = sc.next();

			if (start.equals("2")) {
				inscriptionJoueur(daoPersonnes, sc);
			} else if (start.equals("4")) {
				System.exit(0);
			} else if (start.equals("3")) {
				Joueur admin = connexionAdmin(sc, daoPersonnes);
				if (admin != null) {
					actionsAdmin(sc, admin, daoCarteQuestion, daoCarteReponse, daoPersonnes);
				}
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

		// Creer partie
		Partie partie = Partie.creerPartie(modeDonnee, nbJoueur, nbTour, modeJeu, 0);

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
				} else if (Pattern.matches("^0$", u)) { // pseudo anonyme
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
		partie.setJoueurs(groupe);

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

	public static ArrayList<Question> melangerPaquetQuestion(IDAOCarteQuestion daoCarteQuestion) {
		// Creation du paquet de questions
		ArrayList<Question> paquetQuestion = new ArrayList<Question>();
		for (Question c : daoCarteQuestion.findAll()) {
			paquetQuestion.add(c);
			c.setNbInput();
		}
		Collections.shuffle(paquetQuestion);
		return paquetQuestion;
	}

	public static ArrayList<Reponse> melangerPaquetProposition(IDAOCarteReponse daoCarteReponse) {
		// creation du paquet de proposition
		ArrayList<Reponse> paquetReponse = new ArrayList<Reponse>();
		for (Reponse c : daoCarteReponse.findAll()) {
			paquetReponse.add(c);
		}
		Collections.shuffle(paquetReponse);
		return paquetReponse;
	}

	public static void lancerPartie(Partie p, IDAOCarteReponse daoCarteReponse, IDAOCarteQuestion daoCarteQuestion,
			Scanner sc) {

		p.setPaquetQ(melangerPaquetQuestion(daoCarteQuestion));
		p.setPaquetR(melangerPaquetProposition(daoCarteReponse));


		// Pour chaque Tour
		for (int x = 0; x < p.getNbTour(); x++) {
			System.out.println("Tour " +(x+1)+ " sur " + p.getNbTour() +" :");
			// Si on a pas assez de cartes questions
			if (p.getPaquetQ().size() < 1) {
				p.setPaquetQ(melangerPaquetQuestion(daoCarteQuestion));
			}

			p.setQuestionEnCours(p.getPaquetQ().get(0));

			if (p.getModeSaisie() == 2 || p.getModeSaisie() == 3) {

				// on redonne des cartes proposition aux joueurs
				for (Joueur j : p.getJoueurs()) {
					if (x != 0) {
						j.getMain().clear();
					} else {
						j.setMain(new ArrayList<Reponse>());
					}

					for (int i2 = 0; i2 < 4; i2++) {
						// Si on a pas assez de cartes propositions
						if (p.getPaquetR().size() < 5) {
							p.setPaquetR(melangerPaquetProposition(daoCarteReponse));
						}
						j.getMain().add(p.getPaquetR().remove(0));
//						System.out.println(j.getMain().get(i2).getDonnee());

					}
				}

			}

			p.afficheQuestions(sc);
	//		p.afficheReponses(sc);
			Collections.shuffle(p.getJoueurs());
			p.vote(sc);
			p.afficheScore();
			sc.next();
			p.getPaquetQ().remove(0);
		}
		// Fin du tour

		System.out.println("Le jeu est terminé. Merci d'avoir joué");
		System.out.println("Le gagnant est ");
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for(Joueur j: p.getJoueurs()) {
		scores.add(j.getScore());
		}
		Integer maxScore = Collections.max(scores);
		for(Joueur j: p.getJoueurs()) {
			if(j.getScore() == maxScore) {
				System.out.println(j.getPseudo());
			}
		}
		// Fin de la partie

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

	public static Joueur connexionAdmin(Scanner sc, IDAOJoueur daoPersonnes) {
		int id = 0;
		do {
			System.out.println("Entrez votre identifiant d'administrateur");
			String sid = sc.next();
			try {
				id = Integer.parseInt(sid);
			} catch (Exception e) {
				id = 0;
			}
		} while (daoPersonnes.findById(id).getId() == 0);

		System.out.println(daoPersonnes.findById(id).getType());
		Joueur joueur = daoPersonnes.findById(id);

		if (!(joueur.getType().equals("admin"))) {
			return null;
		}

		// verification du mot de passe
		boolean mdpok = false;
		int cpt = 0;
		while (!mdpok && joueur.getId() != 0 && cpt < 3) {
			System.out.println(joueur.getPseudo() + " entrez votre mot de passe"); // ou sur son nom ou sonid ?
			String mdp = sc.next();
			if (mdp.equals(joueur.getMotDePasse())) { // verification du mot de passe
				mdpok = true;
			}
			cpt++;
		}
		return joueur;
	}

	public static void actionsAdmin(Scanner sc, Joueur j, IDAOCarteQuestion daoCarteQuestion,
			IDAOCarteReponse daoCarteReponse, IDAOJoueur daoPersonnes) {
		String choix = "";
		do {
			System.out.println("Bonjour " + j.getPseudo() + " ! Que voulez vous faire ?");
			System.out.println("QUESTIONS");
			System.out.println("1-Ajouter");
			System.out.println("2-Modifier");
			System.out.println("3-Supprimer");
			System.out.println("4-Les voir TOUTES");
			System.out.println();
			System.out.println("REPONSES");
			System.out.println("5-Ajouter");
			System.out.println("6-Modifier");
			System.out.println("7-Supprimer");
			System.out.println("8-Les voir TOUTES");
			System.out.println();
			System.out.println("JOUEURS");
			System.out.println("9-Ajouter");
			System.out.println("10-Modifier");
			System.out.println("11-Supprimer");
			System.out.println("12-Les voir TOUS");
			System.out.println("13-QUITTER");

			choix = sc.next();

			switch (Integer.parseInt(choix)) {
			case 1:
				ajouterQuestion(sc, daoCarteQuestion);
				break;
			case 2:
				modifierQuestion(sc, daoCarteQuestion);
				break;
			case 3:
				supprimerQuestion(sc, daoCarteQuestion);
				break;
			case 4:
				for (Question q : daoCarteQuestion.findAll()) {
					System.out.println("ID " + q.getId() + " : " + q.getDonnee());
				}
				break;
			case 5:
				ajouterReponse(sc, daoCarteReponse);
				break;
			case 6:
				modifierReponse(sc, daoCarteReponse);
				break;
			case 7:
				supprimerReponse(sc, daoCarteReponse);
				break;
			case 8:
				for (Reponse r : daoCarteReponse.findAll()) {
					System.out.println("ID " + r.getId() + " : " + r.getDonnee());
				}
				break;
			case 9:
				inscriptionJoueur(daoPersonnes, sc);
				break;
			case 10:
				modifierJoueur(sc, daoPersonnes);
				break;
			case 11:
				supprimerJoueur(sc, daoPersonnes);
				break;
			case 12:
				for (Joueur r : daoPersonnes.findAll()) {
					System.out.println("ID " + r.getId() + " : " + r.getPseudo() +" - "+ r.getNom()+ " "+r.getPrenom());
				}
				break;
			case 13:
				return;
			}
		} while (Integer.parseInt(choix) != 13);
	}

	public static void ajouterQuestion(Scanner sc, IDAOCarteQuestion daoCarteQuestion) {
		System.out.println("Ecrivez votre nouvelle question :");
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		String text = sc.nextLine();
		Question newQ = new Question();
		newQ.setDonnee(text);
		newQ.setTypeDonnee("texte");
		daoCarteQuestion.save(newQ);
	}

	public static void modifierQuestion(Scanner sc, IDAOCarteQuestion daoCarteQuestion) {
		System.out.println("Entrez la question à modifier (id)");
		int id = sc.nextInt();
		daoCarteQuestion.findById(id);
		System.out.println(daoCarteQuestion.findById(id).getDonnee());
		System.out.println("Ecrivez votre nouvelle version de la question :");
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		String text = sc.nextLine();
		daoCarteQuestion.findById(id).setDonnee(text);
		daoCarteQuestion.save(daoCarteQuestion.findById(id));
	}

	public static void supprimerQuestion(Scanner sc, IDAOCarteQuestion daoCarteQuestion) {
		System.out.println("Entrez la question à supprimer (id)");
		int id = sc.nextInt();
		daoCarteQuestion.findById(id);
		System.out.println(daoCarteQuestion.findById(id).getDonnee());
		System.out.println("Voulez-vous supprimer cette Question ?");
		System.out.println("Oui (1) / Non (2)");
		String choix = sc.next();
		if (choix.equals("1")) {
			daoCarteQuestion.deleteById(id);
		}
	}

	public static void ajouterReponse(Scanner sc, IDAOCarteReponse daoCarteReponse) {
		System.out.println("Ecrivez votre nouvelle reponse :");
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		String text = sc.nextLine();
		Reponse newQ = new Reponse();
		newQ.setDonnee(text);
		newQ.setTypeDonnee("texte");
		daoCarteReponse.save(newQ);
	}

	public static void modifierReponse(Scanner sc, IDAOCarteReponse daoCarteReponse) {
		System.out.println("Entrez la reponse à modifier (id)");
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		int id = sc.nextInt();
		daoCarteReponse.findById(id);
		System.out.println(daoCarteReponse.findById(id).getDonnee());
		System.out.println("Ecrivez votre nouvelle version de la reponse :");
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		String text = sc.nextLine();
		daoCarteReponse.findById(id).setDonnee(text);
		daoCarteReponse.save(daoCarteReponse.findById(id));
	}

	public static void supprimerReponse(Scanner sc, IDAOCarteReponse daoCarteReponse) {
		System.out.println("Entrez la Reponse à supprimer (id)");
		int id = sc.nextInt();
		daoCarteReponse.findById(id);
		System.out.println(daoCarteReponse.findById(id).getDonnee());
		System.out.println("Voulez-vous supprimer cette reponse ?");
		System.out.println("Oui (1) / Non (2)");
		String choix = sc.next();
		if (choix.equals("1")) {
			daoCarteReponse.deleteById(id);
		}
	}

	public static void modifierJoueur(Scanner sc, IDAOJoueur daoPersonnes) {
		System.out.println("Entrez le joueur à modifier (id)");

		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		int id = sc.nextInt();
		System.out.println(daoPersonnes.findById(id).getPseudo());
		System.out.println("Que voulez vous modifier ?");
		System.out.println("1-Le nom");
		System.out.println("2-Le prenom");
		System.out.println("3-Le pseudo");
		System.out.println("4-Le mot de passe");
		System.out.println("5-Le type");
		System.out.println();
		System.out.println("6-Quitter");

		if (sc.hasNextLine()) {sc.nextLine();}
		int choixmodif = sc.nextInt();
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		switch (choixmodif) {
		case 1:
			System.out.println("Ecrivez le nouveau nom");
			String text = sc.nextLine();
			daoPersonnes.findById(id).setNom(text);
			break;
		case 2:
			System.out.println("Ecrivez le nouveau prenom");
			String prenom = sc.nextLine();
			daoPersonnes.findById(id).setPrenom(prenom);
			break;
		case 3:
			System.out.println("Ecrivez le nouveau pseudo");
			String pseudo = sc.nextLine();
			daoPersonnes.findById(id).setPseudo(pseudo);
			break;
		case 4:
			System.out.println("Ecrivez le nouveau mot de passe");
			String mdp = sc.nextLine();
			daoPersonnes.findById(id).setMotDePasse(mdp);
			break;
		case 5:
			System.out.println("Ecrivez le nouveau type");
			String type = sc.nextLine();
			daoPersonnes.findById(id).setMotDePasse(type);
			break;
		case 6:
			return;
		}

		daoPersonnes.save(daoPersonnes.findById(id));
	}

	public static void supprimerJoueur(Scanner sc, IDAOJoueur daoPersonnes) {
		System.out.println("Entrez la Joueur à supprimer (id)");
		int id = sc.nextInt();
		daoPersonnes.findById(id);
		System.out.println(daoPersonnes.findById(id).getPseudo());
		System.out.println("Voulez-vous supprimer ce Joueur ?");
		System.out.println("Oui (1) / Non (2)");
		String choix = sc.next();
		if (choix.equals("1")) {
			daoPersonnes.deleteById(id);
		}
	}

}

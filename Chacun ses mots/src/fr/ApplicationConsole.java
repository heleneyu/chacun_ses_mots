package fr;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Scanner;
//
//import fr.formation.projet.Carte;
//import fr.formation.projet.Joueur;
//import fr.formation.projet.Partie;
//import fr.formation.projet.Tour;

public class ApplicationConsole {
   //main
	boolean wait = true;
	int nbJoueur = 0;
	int nbTour = 0;
	int modeJeu = 0;
	int modeDonnee = 0;
	
	//creation et alimentation de la collection de questions -- version console
//	Questions questions = new Questions();
//	questions.getListe().add("Il faut vraiment que __________ travaille avec _________ , pour améliorer la vie des français.");
//	questions.getListe().add("___________ est le seul moyen de sauver le monde.");
//	questions.getListe().add("La vie c'est vraiment __________.");
//	questions.getListe().add("On ne fait pas __________ sans casser ___________.");
//	questions.getListe().add("J'aime beaucoup ______ .");
//	questions.getListe().add("Depuis que j'ai connu _________, ma vie a changée.");
//	questions.getListe().add("Rien n'est plus triste que _____.");
//	
//	
//	for(String q : questions.getListe()) {
//		System.out.println(q);
//	}
//	//creation du paquet de proposition de reponses
//	Questions proposition = new Questions();
//	proposition.getListe().add("les Pokemon");
//	proposition.getListe().add("les cookies");
//	proposition.getListe().add("Dark Vador");
//	proposition.getListe().add("JAVA");
//	proposition.getListe().add("Jeremy");
//	proposition.getListe().add("La prononciation à la japonaise");
//	proposition.getListe().add("Cot cot");
//	proposition.getListe().add("Marie");
//	proposition.getListe().add("Helene");
//	proposition.getListe().add("Sylvain");
//	proposition.getListe().add("Les soldes");
//	proposition.getListe().add("Une jupe à frange");
//	proposition.getListe().add("Les arnaques telephoniques");
//	proposition.getListe().add("Euromillion");
//	proposition.getListe().add("AJC");
//	proposition.getListe().add("Sopra Steria");
//	proposition.getListe().add("Sasuke");
//	proposition.getListe().add("Les gros chats");
//	proposition.getListe().add("Les bananes");
//	proposition.getListe().add("Babar");
//	proposition.getListe().add("Les Minions");
//	proposition.getListe().add("Franklin");
//	proposition.getListe().add("Un gros câlin");
//	proposition.getListe().add("Deux doigts dans le cul");
//	proposition.getListe().add("Les envies de femme enceinte");
//	proposition.getListe().add("Les galères de la sécu");
//	proposition.getListe().add("Des photos compromettantes sur mon PDG");
//	proposition.getListe().add("Un papillon tatoué dans le bas du dos");
//	proposition.getListe().add("Vivel Dop Fixation Béton");
//	proposition.getListe().add("Un ver solitaire");
//	proposition.getListe().add("Un couscous au lardons");
//	proposition.getListe().add("les angles de la table basse du salon");
//	proposition.getListe().add("la motivation");
//	proposition.getListe().add("les punks à chien");
//	proposition.getListe().add("la mentalité parisienne");
//	proposition.getListe().add("un iPad");
//	proposition.getListe().add("une tétine avec la moustache d'Adolf");
//	proposition.getListe().add("une rolex");
//	proposition.getListe().add("le passage du salaire brut au net");
//	proposition.getListe().add("la couenne du jambon");
//	proposition.getListe().add("un lego");
//	proposition.getListe().add("les bonnes manières");
//	proposition.getListe().add("une bonne baffe");
//	proposition.getListe().add("le porno au bureau");
//	proposition.getListe().add("Babar erotica");
//	proposition.getListe().add("une fille qui fait plus que ses 15 ans");
//	proposition.getListe().add("un CAP coiffure");
//	proposition.getListe().add("le racisme anti-blanc");
//	proposition.getListe().add("Yabon Banania");
//	proposition.getListe().add("Princesse Sarah");
//	proposition.getListe().add("Midi les Zouzous");
//	proposition.getListe().add("capsule Nespresso");
//	proposition.getListe().add("les anges de la téléréalité");
//	proposition.getListe().add("un sauna gay");
//	proposition.getListe().add("une flute à bec");
//	proposition.getListe().add("un canard en plastique jaune");
//	proposition.getListe().add("la Renault Espace");
//	proposition.getListe().add("l'orgasme total");
//	proposition.getListe().add("la consanginité");
//	proposition.getListe().add("rien");
//	proposition.getListe().add("une bonne bouteille");
//	proposition.getListe().add("des hémorroïdes");
//	proposition.getListe().add("un diabolo grenadine");
//	proposition.getListe().add("un film de 3h en streaming");
//	proposition.getListe().add("Celine Dion");
//	proposition.getListe().add("la vaseline");
//	proposition.getListe().add("les seins de Nami");
//	proposition.getListe().add("les problèmes de riches");
//	proposition.getListe().add("le string qui dépasse");
//	proposition.getListe().add("les promesses electorales");
//	
//	ArrayList<Carte> paquetProposition = new ArrayList<Carte>();
//	
//	for(int i = 0; i <  proposition.getListe().size(); i++) {
//		Carte c = new Carte();
//		c.setTypeCarte("Question");
//		c.setDonnee(proposition.getListe().get(i));
//		c.setNbInput(proposition.getListe().get(i).split("_+").length - 1);
//		paquetProposition.add(c);
//	}
//	for(int i = 0; i <  questions.getListe().size(); i++) {
//		Carte c = new Carte();
//		c.setTypeCarte("Question");
//		c.setDonnee(questions.getListe().get(i));
//		c.setNbInput(questions.getListe().get(i).split("_+").length - 1);
//		paquet.add(c);
//	}
//	
//	for(int i = 0; i <  questions.getListe().size(); i++) {
//		System.out.println(questions.getListe().get(i));
//	}
////	
//	Collections.shuffle(paquet);
//	Collections.shuffle(paquetProposition);
//	for(Carte c : paquet) {
//		System.out.println(c.getDonnee());
//	}
//	
//	Scanner sc = new Scanner(System.in);
//	
//	while (wait) {
//
//		System.out.println("Selectionner le nombre de joueurs.");
//		nbJoueur = sc.nextInt();
//		sc.nextLine();
//		System.out.println("Selectionner le nombre de tour.");
//		nbTour = sc.nextInt();
//		sc.nextLine();
//		System.out.println("Selectionner le mode de jeu (1 pour mode Remplissage, 2 pour mode Proposition et 3 pour mode LesDeux)");
//		modeJeu = sc.nextInt();
//		sc.nextLine();
//		System.out.println("Valider votre choix ( 1-OK; 0-Redo)");
//		int valide = sc.nextInt();
//		sc.nextLine();
//		if (valide == 1) {
//			wait = false;
//		}
//
//	}
//	
//	//Creer partie
//	Partie partie = Partie.creerPartie(modeDonnee, nbJoueur, nbTour, modeJeu, 0);
//	ArrayList<Joueur> groupe = new ArrayList<Joueur>();
//	for (int i = 1; i < nbJoueur + 1; i++) {
//		System.out.println("Joueur " + i + " entrez votre pseudo");
//		String pseudo = sc.nextLine();
//		Joueur j = Joueur.creerJoueur(pseudo);
//		groupe.add(j);
//	}
//	
//	
//	partie.setGroupe(groupe);
//	
//	
//	//Affiche joueurs
//	System.out.println("Liste des joueurs : ");
//	for (Joueur j : groupe) {
//		System.out.println(j.getPseudo());
//	}
//	//Pour chaque Tour
//	for (int x = 0; x < nbTour; x++) {
//		//Si on a pas assez de cartes propositions
//		if(paquetReponse.size() <= (4*nbJoueur)) {
//			for (Carte c : daoCarteReponse.findAll()) {
//				paquetReponse.add(c);
//			}
//			Collections.shuffle(paquetReponse);
//		}
//		//Si on a pas assez de cartes questions
//		if(paquetReponse.size() < 1) {
//			for (Carte c : daoCarteQuestion.findAll()) {
//				paquetQuestion.add(c);
//				c.setNbInput(c.getDonnee().split("_+").length - 1);
//			}
//			Collections.shuffle(paquetQuestion);
//		}
//		// on cree le tour
//		//Collections.shuffle(paquetProposition);
//		Tour tour = Tour.creerTour(1, paquetQuestion.remove(0), partie);
//		if (modeJeu == 2) {
//			for (Joueur j : groupe) { //on donne des cartes proposition aux joueurs
//				for (int i = 0; i < 4; i++) {
//					j.getProposition().add(paquetReponse.remove(0));
//				}
//			}
//			tour.afficheQuestionMode2(sc);
//		}
//		else if (modeJeu == 3) {
//			for (Joueur j : groupe) { //on donne des cartes proposition aux joueurs
//				for (int i = 0; i < 4; i++) {
//					j.getProposition().add(paquetReponse.remove(0));
//				}
//			}
//			tour.afficheQuestionMode3(sc);
//		}
//		else {
//		tour.afficheQuestion(sc); // on affiche la question
//		}
//		tour.afficheReponse(sc);
//		tour.vote(sc);
//		tour.afficheScore(sc);
//		for(Joueur j : groupe) {
//			j.getProposition().clear();
//		}
//	}
//	System.out.println("Le jeu est terminé. Merci d'avoir joué");
//	
//	sc.close();
}

package fr.formation.projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tour {
	private int tourEnCours;
	private Carte questionEnCours;
	private Partie partie;
	private ArrayList<Joueur> mix = new ArrayList<Joueur>();

	public int getTourEnCours() {
		return tourEnCours;
	}

	public void setTourEnCours(int tourEnCours) {
		this.tourEnCours = tourEnCours;
	}

	public Carte getQuestionEnCours() {
		return questionEnCours;
	}

	public void setQuestionEnCours(Carte questionEnCours) {
		this.questionEnCours = questionEnCours;
	}

	public static Tour creerTour(int tour, Carte question, Partie partie) {
		Tour t = new Tour();
		t.partie = partie;
		t.tourEnCours = tour;
		t.questionEnCours = question;
		for (Joueur j : partie.getGroupe()) {
			t.mix.add(j);
		}
		Collections.shuffle(t.mix);
		return t;
	}

	// chercheQuestion()
//	public void chercheQuestion(Questions liste) {
//		this.questionEnCours = Carte.Question;
//		this.questionEnCours.setDonnee(liste.getListe().remove(0));
//	}

	// affiche aux joueurs la question
	public void afficheQ(Joueur j, Scanner sc) {

		System.out.println(this.questionEnCours.getDonnee());
		System.out.println(j.getPseudo());
		System.out.println("Entrez votre réponse");
		j.getReponse().clear();
		// Si question ne se finit pas par underscore, retranche 1

		if (!(this.questionEnCours.getDonnee().startsWith("_"))){
			this.questionEnCours.setNbInput(this.questionEnCours.getNbInput() - 1);
		}
		for (int i = 0; i < this.questionEnCours.getNbInput(); i++) { // pour chaque champ libre
			String s = sc.next();
			j.getReponse().add(s);
		}
	}

	public void afficheQuestionMode2(Scanner sc) {
		System.out.println();
		for (int i = 0; i < partie.getGroupe().size(); i++) {// pour chaque joueur
			Joueur j = partie.getGroupe().get(i);
			System.out.println(this.questionEnCours.getDonnee());
			System.out.print(j.getPseudo());
			System.out.println(" faites votre choix.");
			j.getReponse().clear();
			for (int x = 0; x < 4; x++) { // on montre la liste des propositions
				System.out.println(x + " " + j.getProposition().get(x).getDonnee());

			}
			// Si question ne se finit pas par underscore, retranche 1
			if ( !(this.questionEnCours.getDonnee().startsWith("_"))){
				this.questionEnCours.setNbInput(this.questionEnCours.getNbInput() - 1);
			}
			for (int y = 0; y < this.questionEnCours.getNbInput(); y++) { // pour chaque champ libre
				String entree = sc.next();
				int e = Integer.parseInt(entree);
				j.getReponse().add(j.getProposition().get(e).getDonnee());
			}
		}
	}

	public void afficheQuestionMode3(Scanner sc) {
		System.out.println();
		if ((this.questionEnCours.getDonnee().startsWith("_"))){
				this.questionEnCours.setNbInput(this.questionEnCours.getNbInput() - 1);
		}
		for (int i = 0; i < partie.getGroupe().size(); i++) {// pour chaque joueur
			Joueur j = partie.getGroupe().get(i);
			System.out.println(this.questionEnCours.getDonnee());
			System.out.print(j.getPseudo());
			System.out.println(", faites votre choix ou entrez votre réponse");
			j.getReponse().clear();
			for (int x = 0; x < 4; x++) { // on montre la liste des propositions
				System.out.println(x + " " + j.getProposition().get(x).getDonnee());
			}
//			System.out.println("nb input" + this.questionEnCours.getNbInput());
			
			// Si question ne se finit pas par underscore, retranche 1

			for (int y = 0; y < this.questionEnCours.getNbInput(); y++) { // pour chaque champ libre
//				System.out.println("Boucle réponse");
				String entree = sc.next();
				if (Pattern.matches("^[0-9]$", entree)) {
					int e = Integer.parseInt(entree);
					j.getReponse().add(j.getProposition().get(e).getDonnee());
				} else {
					j.getReponse().add(entree);
				}
			}
		}
	}

	// affiche au joueur leur réponse
	public void afficheR(Joueur j, Scanner sc) {

		// System.out.println(this.questionEnCours.getDonnee());
		// System.out.println("Réponse");
		System.out.println(reponse(this.questionEnCours.getDonnee(), j.getReponse()));
		sc.nextLine();
	}

	public String reponse(String question, ArrayList<String> reponse) {
		String[] phraseDecoupe;
		String phrase = "";

		phraseDecoupe = question.split("_+");

		if (phrase.startsWith("_")) { //si ca commence par un underscore
			for (int i = 0; i < this.questionEnCours.getNbInput(); i++) {
				phrase = phrase + reponse.get(i) + phraseDecoupe[i];
			}
		} else {
			for (int i = 0; i < this.questionEnCours.getNbInput(); i++) {
				phrase = phrase + phraseDecoupe[i] + reponse.get(i);
			}
			if (!phrase.endsWith("_")) {
				phrase = phrase + phraseDecoupe[phraseDecoupe.length - 1];
			}
		}
		// System.out.print(phrase);
		return phrase;
	}

	public void afficheQuestion(Scanner sc) { // pour chaque joueur
		for (int i = 0; i < partie.getGroupe().size(); i++) {
			afficheQ(partie.getGroupe().get(i), sc);
		}

	}

	public void afficheReponse(Scanner sc) {
		System.out.println("Réponses");
		for (Joueur j : mix) {
			afficheR(j, sc);
		}

	}

	public void vote(Scanner sc) {
		// System.out.println(this.getQuestionEnCours().getDonnee());
		for (int i = 1; i < mix.size() + 1; i++) {
			System.out.println(i + " " + // numero du vote
					reponse(this.questionEnCours.getDonnee(), // question à découper
							mix.get(i - 1).getReponse())); // Reponse du joueur concerné

		}

		for (Joueur j : partie.getGroupe()) {
			System.out.println("Joueur " + j.getPseudo() + " Votez ");
			String votte = sc.next();
			int nbVote = Integer.parseInt(votte);
			mix.get(nbVote - 1).setScore(mix.get(nbVote - 1).getScore() + 1);// On donne au joueur "gagnant" son score
																				// +1
		}

	}

	public void afficheScore(Scanner sc) {
		System.out.println("Score : ");
		for (Joueur j : partie.getGroupe()) {
			System.out.println(j.getPseudo() + " : " + j.getScore() + " points");
		}
		sc.next();
	}

	public void changerTour() {
		tourEnCours++;
	}

}// fin class
	// affichePropositions()
	// changeEcranSelonEtatPartie()
// renvoiNumeroTour()
// afficheQuestion()
// affichageScore()

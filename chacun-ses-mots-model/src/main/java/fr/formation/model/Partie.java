package fr.formation.model;


import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "partie")
public class Partie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_ID")
	private int id;

	@Column(name = "PA_MODE_AFFICHAGE")
	private int modeAffichage;

	@Column(name = "PA_NB_JOUEURS")
	private int nbJoueur;

	@Column(name = "PA_NB_TOUR")
	private int nbTour;

	@Column(name = "PA_TOUR_EN_COURS")
	private int tourEnCours = 1;

	@Column(name = "PA_MODE_SAISIE")
	private int modeSaisie;

	@Column(name = "PA_NB_ECRAN")
	int nbEcran; // en focnctiondu nombre d'ecran utilisateurs

	@Column(name = "PA_ETAT_PARTIE")
	private int etatPartie;

	@ManyToOne
	@JoinColumn(name = "PA_QUESTION_ID")
	private Question questionEnCours;

	@OneToMany(mappedBy = "partie")
	private List<Joueur> joueurs;

	@Transient
	private List<Question> paquetQ;

	@Transient
	private List<Reponse> paquetR;
	
//	@ManyToMany
//	@JoinTable(
//			name="PARTIE_JOUEURS",
//			joinColumns=@JoinColumn(name="PA_PA_ID", referencedColumnName="PA_ID"),
//			inverseJoinColumns=@JoinColumn(name="PA_P_ID", referencedColumnName="P_ID")
//			)
//	private List<Joueur> joueurs;
	

//	+-------------------+---------+------+-----+---------+----------------+
//	| Field             | Type    | Null | Key | Default | Extra          |
//	+-------------------+---------+------+-----+---------+----------------+
//	| PA_ID             | int(11) | NO   | PRI | NULL    | auto_increment |
//	| PA_MODE_AFFICHAGE | int(11) | NO   |     | 1       |                |
//	| PA_NB_JOUEURS     | int(11) | NO   |     | 2       |                |
//	| PA_NB_TOUR        | int(11) | NO   |     | 1       |                |
//	| PA_MODE_SAISIE    | int(11) | NO   |     | 1       |                |
//	| PA_NB_ECRAN       | int(11) | NO   |     | 1       |                |
//	| PA_ETAT_PARTIE    | int(11) | NO   |     | 1       |                |
//	| PA_PARTIE_ID      | int(11) | NO   |     | NULL    |                |
//	| PA_JOUEUR_ID      | int(11) | NO   | MUL | NULL    |                |
//	+-------------------+---------+------+-----+---------+----------------+

	public Partie() {

	}

	public static Partie creerPartie(int modeAffichage, int nbJoueur, int nbTour, int modeSaisie, int etatPartie) {
		Partie p = new Partie();
		p.modeAffichage = modeAffichage;
		p.nbJoueur = nbJoueur;
		p.nbTour = nbTour;
		p.modeSaisie = modeSaisie;
		p.etatPartie = etatPartie;
		return p;
	}

	public void distribuerCartes() {

	}

	public void afficheQ(Joueur j, Scanner sc) {
		System.out.println(this.questionEnCours.getDonnee());
		System.out.println(j.getPseudo());
		j.getReponsesEcrites().clear();
	}

	public void getR1(Joueur j, Scanner sc) {
		// Mode 1 : réponse libre
		afficheQ(j, sc);
		System.out.println("Entrez votre réponse.");
		// Récupère réponse du joueur pour chaque champ libre
		for (int i = 0; i < this.questionEnCours.getNbInput(); i++) { // pour chaque champ libre
			String s = sc.next();
			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			j.getReponsesEcrites().add(s);
		}

	}

	public void getR2(Joueur j, Scanner sc) {
		// Mode 2: carte de réponses
		afficheQ(j, sc);
		System.out.println("Faites votre choix");
		// on montre la liste des propositions
		for (int x = 0; x < 4; x++) {
			System.out.println(x + " " + j.getMain().get(x).getDonnee());

		}
		// Récupère réponse du joueur pour chaque champ libre
		for (int y = 0; y < this.questionEnCours.getNbInput(); y++) {
			String entree = sc.next();
			int e = Integer.parseInt(entree);
			j.getReponsesEcrites().add(j.getMain().get(e).getDonnee());
		}
	}

	public void getR3(Joueur j, Scanner sc) {
		// Mode 3: réponses libres et cartes de réponses
		afficheQ(j, sc);
		System.out.println("Faites votre choix ou écrivez votre réponse");
		// on montre la liste des propositions
		for (int x = 0; x < 4; x++) {
			System.out.println(x + " " + j.getMain().get(x).getDonnee());
		}

		// Récupère réponse du joueur pour chaque champ libre
		for (int y = 0; y < this.questionEnCours.getNbInput(); y++) {
			String entree = sc.next();
			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			// Carte réponse
			if (Pattern.matches("^[1-4]$", entree)) {
				int e = Integer.parseInt(entree);
				j.getReponsesEcrites().add(j.getMain().get(e).getDonnee());
				// Réponse libre
			} else {
				j.getReponsesEcrites().add(entree);
			}
		}

	}

	public void afficheQuestions(Scanner sc) { // pour chaque joueur
		for (int i = 0; i < this.getJoueurs().size(); i++) {
			// Selon le mode de jeu
			if (this.getModeSaisie() == 1) {
				getR1(this.getJoueurs().get(i), sc);
			} else if (this.getModeSaisie() == 2) {
				getR2(this.getJoueurs().get(i), sc);
			} else if (this.getModeSaisie() == 3) {
				getR3(this.getJoueurs().get(i), sc);
			}
		}

	}

	public void afficheR(Joueur j) {
		System.out.println(reponse(j));
	}

	public String reponse(Joueur j) {
		String[] phraseDecoupe;
		String phrase = "";

		phraseDecoupe = this.questionEnCours.getDonnee().split("_+");
//		for(String s : phraseDecoupe) {
//			System.out.println(s);
//		}
//		for(String s : j.getReponsesEcrites()) {
//			System.out.println(s);
//		}

		// si ca commence par un underscore, mets la réponse en premier
		if (phrase.startsWith("_")) {
			for (int i = 0; i < this.questionEnCours.getNbInput() + 1; i++) {
				phrase = phrase + j.getReponsesEcrites().get(i) + phraseDecoupe[i];
			}
		} else {
			// sinon met la phrase en premier
			for (int i = 0; i < this.questionEnCours.getNbInput(); i++) {
				phrase = phrase + phraseDecoupe[i] + j.getReponsesEcrites().get(i);
			}
			// si se finit par un point rajoute le point
			if (!(phrase.endsWith("_"))) {
				phrase = phrase + phraseDecoupe[phraseDecoupe.length - 1];
			}
		}
		return phrase;
	}

	public void afficheReponses(Scanner sc) {
		System.out.println("Réponses");
		for (Joueur j : joueurs) {
			afficheR(j);
		}

	}

	public void vote(Scanner sc) {
		// Affiche les reponses de chaque joueurs avec leur numero de vote
		for (int i = 1; i < joueurs.size() + 1; i++) {
			System.out.println(i + " " + reponse(joueurs.get(i - 1)));

		}

		// Chaque joueur vote
		for (Joueur j : this.getJoueurs()) {
			System.out.println("Joueur " + j.getPseudo() + " Votez ");
			boolean ok = false;
			do {
				String vote = sc.next();
				int nbVote = Integer.parseInt(vote);
				if (j == joueurs.get(nbVote - 1)) {
					System.out.println("Ne votez pas pour vous même !!");
				}
				// On donne au joueur "gagnant" son score +1
				else {
					joueurs.get(nbVote - 1).setScore(joueurs.get(nbVote - 1).getScore() + 1);
					ok=true;
				}
			}while (ok==false);
		}

	}

	public void afficheScore() {
		// Affiche le score pour chaque joueur
		System.out.println("Score : ");
		for (Joueur j : this.getJoueurs()) {
			System.out.println(j.getPseudo() + " : " + j.getScore() + " points");
		}
	}

	public void changerTour() {
		tourEnCours++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getModeAffichage() {
		return modeAffichage;
	}

	public void setModeAffichage(int modeAffichage) {
		this.modeAffichage = modeAffichage;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public int getModeSaisie() {
		return modeSaisie;
	}

	public void setModeSaisie(int modeSaisie) {
		this.modeSaisie = modeSaisie;
	}

	public int getNbEcran() {
		return nbEcran;
	}

	public void setNbEcran(int nbEcran) {
		this.nbEcran = nbEcran;
	}

	public int getEtatPartie() {
		return etatPartie;
	}

	public void setEtatPartie(int etatPartie) {
		this.etatPartie = etatPartie;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public List<Question> getPaquetQ() {
		return paquetQ;
	}

	public void setPaquetQ(List<Question> paquetQ) {
		this.paquetQ = paquetQ;
	}

	public List<Reponse> getPaquetR() {
		return paquetR;
	}

	public void setPaquetR(List<Reponse> paquetR) {
		this.paquetR = paquetR;
	}

	public int getTourEnCours() {
		return tourEnCours;
	}

	public void setTourEnCours(int tourEnCours) {
		this.tourEnCours = tourEnCours;
	}

	public Question getQuestionEnCours() {
		return questionEnCours;
	}

	public void setQuestionEnCours(Question questionEnCours) {
		this.questionEnCours = questionEnCours;
	}

}

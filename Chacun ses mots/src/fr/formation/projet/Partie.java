package fr.formation.projet;
import java.util.ArrayList;

public class Partie {
	private int id;
	private ArrayList<Joueur> groupe = new ArrayList<Joueur>();
	private ArrayList<Integer> idJoueur = new ArrayList<Integer>();
	private ArrayList<Carte> paquetQ = new ArrayList<Carte>();
	private ArrayList<Carte> paquetR = new ArrayList<Carte>();
	private int modeAffichage;
	private int nbJoueur;
	private int nbTour;
	private int modeSaisie;
	//int nbEcran; //en focnctiondu nombre d'ecran utilisateurs
	private int etatPartie;

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
	
	
	
	public ArrayList<Integer> getIdJoueur() {
		return idJoueur;
	}
	public void setIdJoueur(ArrayList<Integer> idJoueur) {
		this.idJoueur = idJoueur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Joueur> getGroupe() {
		return groupe;
	}

	public void setGroupe(ArrayList<Joueur> groupe) {
		this.groupe = groupe;
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
	public int getEtatPartie() {
		return etatPartie;
	}
	public void setEtatPartie(int etatPartie) {
		this.etatPartie = etatPartie;
	}
	public ArrayList<Carte> getPaquetQ() {
		return paquetQ;
	}
	public void setPaquetQ(ArrayList<Carte> paquetQ) {
		this.paquetQ = paquetQ;
	}
	public ArrayList<Carte> getPaquetR() {
		return paquetR;
	}
	public void setPaquetR(ArrayList<Carte> paquetR) {
		this.paquetR = paquetR;
	}
	

}

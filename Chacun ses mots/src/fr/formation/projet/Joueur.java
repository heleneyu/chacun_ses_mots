package fr.formation.projet;
import java.util.ArrayList;

public class Joueur {
	private int id = 0;
	private String nom;
	private String prenom;
	private String pseudo = "";
	private String motDePasse;
	private String type;
	private ArrayList<String> reponse = new ArrayList<String>();
	private ArrayList<Carte> proposition = new ArrayList<Carte>();
	private int score = 0;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public ArrayList<String> getReponse() {
		return reponse;
	}
	public void setReponse(ArrayList<String> reponse) {
		this.reponse = reponse;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public ArrayList<Carte> getProposition() {
		return proposition;
	}
	public void setProposition(ArrayList<Carte> proposition) {
		this.proposition = proposition;
	}
	public static Joueur creerJoueur(String pseudo) {
		Joueur j = new Joueur();
		j.pseudo=pseudo;
		return j;
	}
}

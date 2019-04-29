package fr.formation.projet;

public class Carte {
	private int id ;
	private String typeCarte = ""; //soit question soit réponse
	private String typeDonnee =""; //soit texte, audio, video etc.
	private String donnee =""; //la donnée en elle meme (ou le lien vers video, etc)
	//ajouter un autre attribut de type grammaire
	private int nbInput;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbInput() {
		return nbInput;
	}

	public void setNbInput(int nbInput) {
		this.nbInput = nbInput;
	}

	public String getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}

	public String getTypeDonnee() {
		return typeDonnee;
	}

	public void setTypeDonnee(String typeDonnee) {
		this.typeDonnee = typeDonnee;
	}

	public String getDonnee() {
		return donnee;
	}

	public void setDonnee(String donnee) {
		this.donnee = donnee;
	}
	
	
	
}

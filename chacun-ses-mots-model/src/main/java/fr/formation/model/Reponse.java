package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reponses")
public class Reponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "R_ID")
	private int id ;
	
	@NotEmpty
	@NotNull
	@Column(name = "R_TYPE")
	private String typeDonnee =""; //soit verbe, nom ,adj etc.
	
	@NotEmpty
	@Lob
	@Column(name = "R_TEXT")
	private String donnee =""; //la donnée en elle meme (ou le lien vers video, etc)
	//ajouter un autre attribut de type grammaire
	
	
	@ManyToMany(mappedBy="cartesJouees")
	private List<Joueur> joueurs;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getNbInput() {
//		return nbInput;
//	}
//
//	public void setNbInput(int nbInput) {
//		this.nbInput = nbInput;
//	}

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


	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	
	
	
}

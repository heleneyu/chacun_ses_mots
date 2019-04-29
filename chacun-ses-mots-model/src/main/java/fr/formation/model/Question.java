package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Q_ID")
	private int id ;
	
	@NotEmpty
	@NotNull
	@Column(name = "Q_TYPE")
	private String typeDonnee =""; //soit texte, audio, video etc.
	
	@NotEmpty
	@Lob
	@Column(name = "Q_TEXT")
	private String donnee =""; //la donnée en elle meme (ou le lien vers video, etc)
	//ajouter un autre attribut de type grammaire
	
	@NotEmpty
	@Column (name = "Q_GRAMMAIRE")
	private String grammaire = "nom";
	
	@Transient
	private int nbInput;
	
	@OneToOne(mappedBy="questionEnCours")
	private Partie partie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbInput() {
		return nbInput;
	}
	
	public void setNbInput(){
		if(this.getDonnee().endsWith("_") || this.getDonnee().endsWith(" ")) {
			this.setDonnee(this.getDonnee() + ".");
		}
		this.setNbInput(this.getDonnee().split("_+").length-1);
		//System.out.println(this.getDonnee());
	}
	
	public void setNbInput(int nbInput) {
		this.nbInput = nbInput;
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


	public String getGrammaire() {
		return grammaire;
	}

	public void setGrammaire(String grammaire) {
		this.grammaire = grammaire;
	}
	
	
	
}

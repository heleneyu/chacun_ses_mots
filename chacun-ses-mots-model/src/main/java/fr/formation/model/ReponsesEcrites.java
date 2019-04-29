package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reponsesEcrites")
public class ReponsesEcrites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RE_ID")
	private int id ;
	
	@NotEmpty
	@NotNull
	@Column(name = "RE_TYPE")
	private String typeDonnee =""; //soit verbe, nom ,adj etc.
	
	@NotEmpty
	@Lob
	@Column(name = "RE_TEXT")
	private String donnee =""; //la donnée en elle meme (ou le lien vers video, etc)
	
	@ManyToOne
	@JoinColumn(name="RE_JOUEUR_ID")
	private Joueur joueur;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	
	
}

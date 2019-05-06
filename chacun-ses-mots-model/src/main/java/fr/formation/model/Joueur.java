package fr.formation.model;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;


@Entity
@Table (name ="personne")
public class Joueur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_ID")
	private int id = 0;
	
	@NotNull
	@Size(max=100)
	@Column (name= "P_NOM")
	private String nom;
	
	@NotNull
	@Size(max=100)
	@Column (name= "P_PRENOM")
	private String prenom;
	
	@NotNull
	@Size(max=100)
	@Column (name= "P_PSEUDO", nullable = false)
	private String pseudo = "";
	
	@NotNull
	@Size(max=100)
	@Column (name= "P_MDP", nullable = false)
	private String motDePasse;
	
	@NotNull
	@Size(max=50)
	@Column (name= "P_TYPE", nullable = false)
	private String type;
	
	@Column (name= "P_REPONSE")
	@Size(max=100)
	private String reponse = new String();
	
	@Column (name= "P_SCORE")
	private int score = 0;
	
	@ManyToOne
	@JoinColumn(name = "P_PARTIE_ID")
	private Partie partie;
	
	//reponses ecrites en java
	@Transient
	private List<String> reponsesEcrites = new ArrayList<String>();
	
	//table des réponses à garder en bdd
	@OneToMany (mappedBy = "joueur")
	private List<ReponsesEcrites> reponsesEcritesTable;
	
	//main du joueur
	@Transient
	private List<Reponse> mainsJoueurs;
	
	//cartes propositions jouées par le joueur
	@ManyToMany
	@JoinTable(
			name="cartesJouees",
			uniqueConstraints=@UniqueConstraint(columnNames = {"PJ_J_ID", "PJ_R_ID"}),
			joinColumns=@JoinColumn(name="PJ_J_ID", referencedColumnName="P_ID"),
			inverseJoinColumns=@JoinColumn(name="PJ_R_ID", referencedColumnName="R_ID"))
	private List<Reponse> cartesJouees;
	
//	+----------+--------------+------+-----+---------+----------------+
//	| Field    | Type         | Null | Key | Default | Extra          |
//	+----------+--------------+------+-----+---------+----------------+
//	| P_ID     | int(11)      | NO   | PRI | NULL    | auto_increment |
//	| P_NOM    | varchar(100) | NO   |     | NULL    |                |
//	| P_PRENOM | varchar(100) | NO   |     | NULL    |                |
//	| P_PSEUDO | varchar(100) | NO   |     | NULL    |                |
//	| P_MDP    | varchar(100) | NO   |     | NULL    |                |
//	| P_TYPE   | varchar(50)  | NO   |     | joueur  |                |
//	+----------+--------------+------+-----+---------+----------------+
	
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

	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public List<String> getReponsesEcrites() {
		return reponsesEcrites;
	}
	public void setReponsesEcrites(List<String> reponsesEcrites) {
		this.reponsesEcrites = reponsesEcrites;
	}

	public List<ReponsesEcrites> getReponsesEcritesTable() {
		return reponsesEcritesTable;
	}
	public void setReponsesEcritesTable(List<ReponsesEcrites> reponsesEcritesTable) {
		this.reponsesEcritesTable = reponsesEcritesTable;
	}
	public List<Reponse> getMain() {
		return mainsJoueurs;
	}
	public void setMain(List<Reponse> main) {
		this.mainsJoueurs = main;
	}
	public List<Reponse> getCartesJouee() {
		return cartesJouees;
	}
	public void setCartesJouee(List<Reponse> cartesJouee) {
		this.cartesJouees = cartesJouee;
	}
	public static Joueur creerJoueur(String pseudo) {
		Joueur j = new Joueur();
		j.pseudo=pseudo;
		return j;
	}
	public Partie getPartie() {
		return partie;
	}
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	
	
	
}

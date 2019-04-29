package fr.formation.projet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Joueur;

public interface IDAOJoueur  extends JpaRepository<Joueur, Integer>{

	public Joueur findByPseudo(String pseudo);
	
	public Optional<Joueur> findByPseudoAndMotDePasse(String pseudo, String mdp);
	
}

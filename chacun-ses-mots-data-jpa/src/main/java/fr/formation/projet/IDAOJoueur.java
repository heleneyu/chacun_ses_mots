package fr.formation.projet;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Joueur;

public interface IDAOJoueur  extends JpaRepository<Joueur, Integer>{

}

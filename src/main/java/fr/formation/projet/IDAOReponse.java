package fr.formation.projet;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Reponse;

public interface IDAOReponse  extends JpaRepository<Reponse, Integer>{

}

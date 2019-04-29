package fr.formation.projet;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Question;

public interface IDAOQuestion  extends JpaRepository<Question, Integer>{

}

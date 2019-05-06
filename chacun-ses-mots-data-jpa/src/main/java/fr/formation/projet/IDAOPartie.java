package fr.formation.projet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.model.Partie;

public interface IDAOPartie  extends JpaRepository<Partie, Integer>{
	
	
	@Query("select p from Partie p left join fetch p.joueurs j where p.id = :id")
	public Partie findByIdWithJoueurs(@Param("id") int id);

}

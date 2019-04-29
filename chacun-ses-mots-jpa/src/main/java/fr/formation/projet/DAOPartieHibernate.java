package fr.formation.projet;

import java.util.List;

import javax.persistence.EntityTransaction;

import fr.formation.model.Partie;


public class DAOPartieHibernate extends DAOHibernate implements IDAOPartie {

	public List<Partie> findAll() {
		return em.createQuery("select p from Partie p", Partie.class).getResultList();

	}

	public Partie findById(int id) {

		return em.find(Partie.class, id);
	}

	public Partie save(Partie entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(entity);
		tx.commit();
		return entity;
	}

	public void delete(Partie entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.merge(entity));
		tx.commit();
		
	}

	public void deleteById(int id) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.find(Partie.class, id));
		tx.commit();
		
		
	}

}

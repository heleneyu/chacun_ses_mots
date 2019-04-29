package fr.formation.projet;

import java.util.List;

import javax.persistence.EntityTransaction;

import fr.formation.model.Joueur;


public class DAOPersonneHibernate extends DAOHibernate implements IDAOJoueur {

	public List<Joueur> findAll() {
		return em.createQuery("select p from Joueur p", Joueur.class).getResultList();
	}

	public Joueur findById(int id) {
		return em.find(Joueur.class, id);

	}

	public Joueur save(Joueur entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(entity);
		tx.commit();
		return entity;

	}

	public void delete(Joueur entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.merge(entity));
		tx.commit();
		
		
	}

	public void deleteById(int id) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.find(Joueur.class, id));
		tx.commit();
		
		
	}

}

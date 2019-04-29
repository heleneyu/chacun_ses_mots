package fr.formation.projet;

import java.util.List;

import javax.persistence.EntityTransaction;


import fr.formation.model.Reponse;

public class DAOCarteReponseHibernate extends DAOHibernate implements IDAOCarteReponse {

	public List<Reponse> findAll() {
		return em.createQuery("select r from Reponse r", Reponse.class).getResultList();

	}

	public Reponse findById(int id) {
		return em.find(Reponse.class, id);

	}

	public Reponse save(Reponse entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(entity);
		tx.commit();
		return entity;
	}

	public void delete(Reponse entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.merge(entity));
		tx.commit();
		
	}

	public void deleteById(int id) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.find(Reponse.class, id));
		tx.commit();
		
	}

	

}

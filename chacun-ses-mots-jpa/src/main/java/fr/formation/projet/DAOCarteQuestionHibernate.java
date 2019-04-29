package fr.formation.projet;

import java.util.List;

import javax.persistence.EntityTransaction;


import fr.formation.model.Question;

public class DAOCarteQuestionHibernate extends DAOHibernate implements IDAOCarteQuestion {

	public List<Question> findAll() {
		return em.createQuery("select q from Question q", Question.class).getResultList();

	}

	public Question findById(int id) {
		return em.find(Question.class, id);

	}

	public Question save(Question entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(entity);
		tx.commit();
		return entity;
	}

	public void delete(Question entity) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.merge(entity));
		tx.commit();
		
	}

	public void deleteById(int id) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.remove(em.find(Question.class, id));
		tx.commit();
		
	}

}

package fr.formation.projet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOHibernate {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NomPersistenceUnit");
	protected static EntityManager em = emf.createEntityManager();
	
	public static void close() {
		em.close();
		emf.close();
	}
}

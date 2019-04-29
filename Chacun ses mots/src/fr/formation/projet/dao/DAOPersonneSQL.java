package fr.formation.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.Joueur;
import fr.formation.projet.factory.DBFactory;

public class DAOPersonneSQL implements IDAOJoueur{

	@Override
	public List<Joueur> findAll() {
		List<Joueur> mesPersonnes = new ArrayList<Joueur>();
		try {
			ResultSet myResult = DBFactory.createResultSet("SELECT* FROM personne");
			while (myResult.next()) {
				// ajout des infos des produits
				Joueur c = new Joueur();
				c.setNom(myResult.getString("P_NOM"));
				c.setPrenom(myResult.getString("P_PRENOM"));
				c.setPseudo(myResult.getString("P_PSEUDO"));
				c.setMotDePasse(myResult.getString("P_MDP"));
				c.setType(myResult.getString("P_TYPE"));
			
				mesPersonnes.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Erreur");
		}
		return mesPersonnes;
	}

	@Override
	public Joueur findById(int id) {
		Joueur c = new Joueur();
		try {
			ResultSet myResult = DBFactory.createResultSet("SELECT* FROM personne where P_ID = "+id);
			while (myResult.next()) {
				c.setId(myResult.getInt("P_ID"));
				c.setNom(myResult.getString("P_NOM"));
				c.setPrenom(myResult.getString("P_PRENOM"));
				c.setPseudo(myResult.getString("P_PSEUDO"));
				c.setMotDePasse(myResult.getString("P_MDP"));
				c.setType(myResult.getString("P_TYPE"));
			}
		} catch (SQLException e) {
			System.out.println("Erreur");
		}
		return c;
	}

	@Override
	public Joueur save(Joueur entity) {
		try {
			if (entity.getId() == 0) {
				DBFactory.execute(
						"INSERT INTO personne (P_NOM, P_PRENOM, P_PSEUDO, P_MDP, P_TYPE) VALUES (?,?,?,?,?)" ,
				entity.getNom(),
				entity.getPrenom(),
				entity.getPseudo(),
				entity.getMotDePasse(),
				entity.getType()
				);

			}
			else {
				DBFactory.execute(
						"UPDATE personne set P_NOM=?, P_PRENOM=?, P_PSEUDO=?, P_MDP=?, P_TYPE=? WHERE P_ID=?" ,
						entity.getNom(),
						entity.getPrenom(),
						entity.getPseudo(),
						entity.getMotDePasse(),
						entity.getType(),
						entity.getId()
				);

			}}
		

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur");
		}
		return entity;
	}

	@Override
	public void delete(Joueur entity) {
		deleteById(entity.getId());
		
	}

	@Override
	public void deleteById(int id) {
		DBFactory.execute2("DELETE FROM personne WHERE P_ID=?", Boolean.class, id);
		
	}

}

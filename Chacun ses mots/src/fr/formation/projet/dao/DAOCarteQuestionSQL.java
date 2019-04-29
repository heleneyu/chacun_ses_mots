package fr.formation.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.factory.DBFactory;
import fr.formation.projet.Carte;

public class DAOCarteQuestionSQL implements IDAOCarte{

	@Override
	public List<Carte> findAll() {
		List<Carte> mesCartes = new ArrayList<Carte>();
		try {
			ResultSet myResult = DBFactory.createResultSet("SELECT* FROM Questions");
			while (myResult.next()) {
				// ajout des infos des produits
				Carte c = new Carte();
				c.setDonnee(myResult.getString("Q_TEXT"));
				c.setTypeCarte("Question");
				c.setTypeDonnee(myResult.getString("Q_TYPE"));
				mesCartes.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Erreur");
		}
		return mesCartes;
	}

	@Override
	public Carte findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carte save(Carte entity) {
		try {
			if (entity.getId() == 0) {
				DBFactory.execute(
						"INSERT INTO Questions (Q_TEXT, Q_TYPE) VALUES (?,?)" ,
				entity.getDonnee(),
				entity.getTypeDonnee()
				);

			}
			else {
				DBFactory.execute(
						"UPDATE Questions set Q_TEXT=?, Q_TYPE=? WHERE Q_ID=?" ,
						entity.getDonnee(),
						entity.getTypeDonnee(),
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
	public void delete(Carte entity) {
		deleteById(entity.getId());
		
	}

	@Override
	public void deleteById(int id) {
		DBFactory.execute2("DELETE FROM questions WHERE Q_ID=?", Boolean.class, id);
		
	}

}

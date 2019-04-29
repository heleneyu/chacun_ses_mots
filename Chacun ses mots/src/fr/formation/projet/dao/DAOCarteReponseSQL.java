package fr.formation.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.factory.DBFactory;
import fr.formation.projet.Carte;

public class DAOCarteReponseSQL implements IDAOCarte{

	@Override
	public List<Carte> findAll() {
		List<Carte> mesCartes = new ArrayList<Carte>();
		try {
			ResultSet myResult = DBFactory.createResultSet("SELECT* FROM Reponses");
			while (myResult.next()) {
				// ajout des infos des produits
				Carte c = new Carte();
				c.setDonnee(myResult.getString("R_TEXT"));
				c.setTypeCarte("Reponse");
				c.setTypeDonnee(myResult.getString("R_TYPE")); //type réponse (verbe, nom, adjectif)
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
						"INSERT INTO Reponses (R_TEXT, R_TYPE) VALUES (?,?)" ,
				entity.getDonnee(),
				entity.getTypeDonnee()
				);

			}
			else {
				DBFactory.execute(
						"UPDATE Reponses set R_TEXT=?, R_TYPE=? WHERE R_ID=?" ,
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
		DBFactory.execute2("DELETE FROM Reponses WHERE R_ID=?", Boolean.class, id);
		
	}

}

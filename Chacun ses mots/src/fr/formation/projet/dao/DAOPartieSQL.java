package fr.formation.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.formation.projet.Partie;
import fr.formation.projet.factory.DBFactory;

public class DAOPartieSQL implements IDAOPartie{

	@Override
	public List<Partie> findAll() {
		List<Partie> mesParties = new ArrayList<Partie>();
		try {
			ResultSet myResult = DBFactory.createResultSet("SELECT* FROM personne");
			while (myResult.next()) {
				// ajout des infos des produits
				Partie c = new Partie();
				c.setModeAffichage(myResult.getInt("PA_MODE_AFFICHAGE"));
				c.setNbJoueur(myResult.getInt("PA_NB_JOUEURS"));
				c.setNbTour(myResult.getInt("PA_NB_TOUR"));
				c.setModeSaisie(myResult.getInt("PA_MODE_SAISIE"));
				c.setEtatPartie(myResult.getInt("PA_ETAT_PARTIE"));
				c.getIdJoueur().add(myResult.getInt("PA_JOUEUR_ID"));
				
			
				mesParties.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Erreur");
		}
		return mesParties;
	}

	@Override
	public Partie findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partie save(Partie entity) {
		try {
			if (entity.getId() == 0) {
				DBFactory.execute(
						"INSERT INTO partie (PA_MODE_AFFICHAGE, PA_NB_JOUEURS, PA_NB_TOUR, PA_MODE_SAISIE, PA_ETAT_PARTIE) VALUES (?,?,?,?,?)" ,
				entity.getModeAffichage(),
				entity.getNbJoueur(),
				entity.getNbTour(),
				entity.getModeSaisie(),
				entity.getEtatPartie()
				);

			}
			else {
				DBFactory.execute(
						"UPDATE partie set PA_MODE_AFFICHAGE=?, PA_NB_JOUEURS=?, PA_NB_TOUR=?, PA_MODE_SAISIE=?, PA_ETAT_PARTIE =? WHERE PA_ID=?" ,
						entity.getModeAffichage(),
						entity.getNbJoueur(),
						entity.getNbTour(),
						entity.getModeSaisie(),
						entity.getEtatPartie(),
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
	public void delete(Partie entity) {
		deleteById(entity.getId());
		
	}

	@Override
	public void deleteById(int id) {
		DBFactory.execute2("DELETE FROM partie WHERE PA_ID=?", Boolean.class, id);
		
	}
}

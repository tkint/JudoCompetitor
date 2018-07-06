package fr.competitor.ws.competition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.Region;
import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.model.judoka.Rank;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.competition.phase.PhaseBDD;
import fr.competitor.ws.judoka.JudokaBDD;
import fr.competitor.ws.region.RegionBDD;

/**
 * Classe de récupération des données pour le Web Services des compétitions
 * @author Thomas
 *
 */
public class CompetitionBDD extends BddObject {
	/**
	 * Fonction qui récupère la compétition spécifiée
	 * 
	 * @param id de la compétition désirée
	 * @return Competition
	 */
	public static Competition getCompetition(int id, Boolean details) {
		initBdd();
		try {
			Statement statementCompetition = (Statement) bdd.createStatement();
			ResultSet resultsetCompetition = statementCompetition
					.executeQuery("SELECT address, postalCode, city, country, name, capacity, dateStart, dateEnd, id_region "
							+ "FROM competition WHERE id_competition = " + id);
			resultsetCompetition.next();
			Region region = null;
			ArrayList<Rank> ranks = null;
			ArrayList<Judoka> judokas = null;
			ArrayList<Phase> phases = null;
			if (details) {
				region = RegionBDD.getRegion(resultsetCompetition
						.getInt("id_region"));
				judokas = new ArrayList<Judoka>();
				for (int id_judoka : getParticipants(id)) {
					judokas.add(JudokaBDD.getJudoka(id_judoka, false));
				}
				phases = PhaseBDD.getPhasesCompetition(id, true);
			}
			Competition Competition = new Competition(id,
					resultsetCompetition.getString("address"),
					resultsetCompetition.getString("postalCode"),
					resultsetCompetition.getString("city"),
					resultsetCompetition.getString("country"),
					resultsetCompetition.getString("name"),
					resultsetCompetition.getInt("capacity"),
					resultsetCompetition.getDate("dateStart"),
					resultsetCompetition.getDate("dateEnd"), region, ranks,
					judokas, phases);
			statementCompetition.close();
			return Competition;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des compétitions
	 * 
	 * @return ArrayList<Competition>
	 */
	public static ArrayList<Competition> getCompetitions(Boolean details) {
		initBdd();
		try {
			ArrayList<Competition> competitions = new ArrayList<Competition>();
			Statement statementCompetition = (Statement) bdd.createStatement();
			ResultSet resultsetCompetition = statementCompetition
					.executeQuery("SELECT id_competition, address, postalCode, city, country, name, capacity, dateStart, dateEnd, id_region "
							+ "FROM competition");
			while (resultsetCompetition.next()) {
				Region region = null;
				ArrayList<Rank> ranks = null;
				ArrayList<Judoka> judokas = null;
				ArrayList<Phase> phases = null;
				if (details) {
					region = RegionBDD.getRegion(resultsetCompetition
							.getInt("id_region"));
					judokas = new ArrayList<Judoka>();
					for (int id_judoka : getParticipants(resultsetCompetition
							.getInt("id_competition"))) {
						judokas.add(JudokaBDD.getJudoka(id_judoka, false));
					}
					phases = PhaseBDD
							.getPhasesCompetition(resultsetCompetition
									.getInt("id_competition"), true);
				}
				Competition competition = new Competition(
						resultsetCompetition.getInt("id_competition"),
						resultsetCompetition.getString("address"),
						resultsetCompetition.getString("postalCode"),
						resultsetCompetition.getString("city"),
						resultsetCompetition.getString("country"),
						resultsetCompetition.getString("name"),
						resultsetCompetition.getInt("capacity"),
						resultsetCompetition.getDate("dateStart"),
						resultsetCompetition.getDate("dateEnd"), region, ranks,
						judokas, phases);
				competitions.add(competition);
			}
			statementCompetition.close();
			return competitions;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère l'id de chaque participant d'une compétition
	 * 
	 * @param id de la compétition dont on veut les participants
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> getParticipants(int id) {
		initBdd();
		try {
			ArrayList<Integer> participants = new ArrayList<Integer>();
			Statement statementParticipate = (Statement) bdd.createStatement();
			ResultSet resultsetParticipate = statementParticipate
					.executeQuery("SELECT id_judoka "
							+ "FROM participate WHERE id_competition = " + id);
			while (resultsetParticipate.next()) {
				participants.add(resultsetParticipate.getInt("id_judoka"));
			}
			statementParticipate.close();
			return participants;
		} catch (SQLException e) {
		}
		return null;
	}
}
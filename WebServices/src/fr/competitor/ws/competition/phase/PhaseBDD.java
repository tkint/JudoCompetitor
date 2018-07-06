package fr.competitor.ws.competition.phase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.competition.phase.fight.FightBDD;

/**
 * Classe de récupération des données de Phase
 * @author Thomas
 *
 */
public class PhaseBDD extends BddObject {
	/**
	 * Retourne les différentes phases d'une compétition donnée
	 * @param id de la compétition
	 * @param details définit si oui ou non on récupére les objets liés
	 * @return ArrayList<Phase>
	 */
	public static ArrayList<Phase> getPhasesCompetition(int id, Boolean details) {
		initBdd();
		try {
			ArrayList<Phase> phases = new ArrayList<Phase>();
			Statement statementPhase = (Statement) bdd.createStatement();
			ResultSet resultsetPhase = statementPhase
					.executeQuery("SELECT DISTINCT p.id_phase, p.name "
							+ "FROM phase AS p INNER JOIN fight AS f "
							+ "ON f.id_phase = p.id_phase "
							+ "WHERE f.id_competition = " + id);
			while (resultsetPhase.next()) {
				ArrayList<Fight> fights = null;
				if (details) {
					fights = FightBDD.getFightsPhase(
							resultsetPhase.getInt("p.id_phase"), true);
				}
				Phase phase = new Phase(resultsetPhase.getInt("p.id_phase"),
						resultsetPhase.getString("p.name"), fights);
				phases.add(phase);
			}
			statementPhase.close();
			return phases;
		} catch (SQLException e) {
		}
		return null;
	}
}

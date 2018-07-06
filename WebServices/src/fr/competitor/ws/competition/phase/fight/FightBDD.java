package fr.competitor.ws.competition.phase.fight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.competition.phase.fight.mark.MarkBDD;
import fr.competitor.ws.judoka.JudokaBDD;

/**
 * Classe de récupération de données pour les combats
 * @author Thomas
 *
 */
public class FightBDD extends BddObject {
	/**
	 * Fonction qui récupère le combat spécifié
	 * @param id du combat désiré
	 * @param details définit si oui ou non on veut les objets liés
	 * @return Fight
	 */
	public static Fight getFight(int id, Boolean details) {
		Judoka opponentOne = null;
		Judoka opponentTwo = null;
		ArrayList<Mark> marks = null;
		if (details) {
			ArrayList<Judoka> judokas = JudokaBDD.getJudokasFight(id);
			opponentOne = judokas.get(0);
			opponentTwo = judokas.get(1);
			marks = MarkBDD.getMarksFight(id);
		}
		Fight fight = new Fight(id, opponentOne, opponentTwo, marks);
		return fight;
	}

	/**
	 * Retourne l'ensemble des combats d'une phase spécifiée
	 * @param id de la phase demandée
	 * @param details définit si oui ou non on veut les objets liés
	 * @return ArrayList<Fight>
	 */
	public static ArrayList<Fight> getFightsPhase(int id, Boolean details) {
		initBdd();
		try {
			ArrayList<Fight> fights = new ArrayList<Fight>();
			Statement statementFight = (Statement) bdd.createStatement();
			ResultSet resultsetFight = statementFight
					.executeQuery("SELECT id_fight FROM fight WHERE id_phase = "
							+ id);
			while (resultsetFight.next()) {
				Judoka opponentOne = null;
				Judoka opponentTwo = null;
				ArrayList<Mark> marks = null;
				if (details) {
					ArrayList<Judoka> judokas = JudokaBDD
							.getJudokasFight(resultsetFight.getInt("id_fight"));
					opponentOne = judokas.get(0);
					opponentTwo = judokas.get(1);
					marks = MarkBDD.getMarksFight(resultsetFight
							.getInt("id_fight"));
				}
				Fight fight = new Fight(resultsetFight.getInt("id_fight"),
						opponentOne, opponentTwo, marks);
				fights.add(fight);
			}
			statementFight.close();
			return fights;
		} catch (SQLException e) {
		}
		return null;
	}
}

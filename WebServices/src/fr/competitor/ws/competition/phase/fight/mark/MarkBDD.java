package fr.competitor.ws.competition.phase.fight.mark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.competition.phase.fight.Move;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.judoka.JudokaBDD;

/**
 * Classe de récupération des données liées aux marques (mouvements placés et validés)
 * @author Thomas
 *
 */
public class MarkBDD extends BddObject {
	/**
	 * Retourne toutes les marques d'un combat donné
	 * @param id du combat désiré
	 * @return ArrayList<Mark>
	 */
	public static ArrayList<Mark> getMarksFight(int id) {
		initBdd();
		try {
			ArrayList<Mark> marks = new ArrayList<Mark>();
			Statement statementMark = (Statement) bdd.createStatement();
			ResultSet resultsetMark = statementMark
					.executeQuery("SELECT ma.number, ma.id_judoka, mo.id_move, mo.name, mo.points "
							+ "FROM mark AS ma "
							+ "INNER JOIN move AS mo "
							+ "ON mo.id_move = ma.id_move "
							+ "WHERE ma.number != 0 AND ma.id_fight  = "
							+ id
							+ " ORDER BY ma.number ASC");
			while (resultsetMark.next()) {
				Mark mark = new Mark(resultsetMark.getInt("ma.number"),
						JudokaBDD.getJudoka(
								resultsetMark.getInt("ma.id_judoka"), false),
						new Move(resultsetMark.getInt("mo.id_move"),
								resultsetMark.getInt("mo.points"),
								resultsetMark.getString("mo.name")));
				marks.add(mark);
			}
			statementMark.close();
			return marks;
		} catch (SQLException e) {
		}
		return null;
	}
}

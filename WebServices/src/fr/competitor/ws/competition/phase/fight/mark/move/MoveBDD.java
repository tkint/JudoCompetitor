package fr.competitor.ws.competition.phase.fight.mark.move;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.competition.phase.fight.Move;
import fr.competitor.ws.BddObject;

/**
 * Classe de récupération des données des mouvements
 * 
 * @author Thomas
 * 
 */
public class MoveBDD extends BddObject {
	/**
	 * Retourne le mouvement spécifié
	 * 
	 * @param id
	 *            du mouvement demandé
	 * @return Move
	 */
	public static Move getMove(int id) {
		initBdd();
		try {
			Statement statementMove = (Statement) bdd.createStatement();
			ResultSet resultsetMove = statementMove
					.executeQuery("SELECT name, points FROM move "
							+ "WHERE id_move = " + id);
			resultsetMove.next();
			Move move = new Move(id, resultsetMove.getInt("points"),
					resultsetMove.getString("name"));
			statementMove.close();
			return move;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Retourne tous les mouvements
	 * @return ArrayList<Move>
	 */
	public static ArrayList<Move> getMoves() {
		initBdd();
		try {
			ArrayList<Move> moves = new ArrayList<Move>();
			Statement statementMove = (Statement) bdd.createStatement();
			ResultSet resultsetMove = statementMove
					.executeQuery("SELECT id_move, name, points FROM move");
			while (resultsetMove.next()) {
				Move move = new Move(resultsetMove.getInt("id_move"),
						resultsetMove.getInt("points"),
						resultsetMove.getString("name"));
				moves.add(move);
			}
			statementMove.close();
			return moves;
		} catch (SQLException e) {
		}
		return null;
	}
}

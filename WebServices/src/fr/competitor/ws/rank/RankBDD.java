package fr.competitor.ws.rank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.judoka.Rank;
import fr.competitor.ws.BddObject;

public class RankBDD extends BddObject {
	/**
	 * Fonction qui récupère le niveau spécifié
	 * 
	 * @param id
	 * @return Niveau
	 */
	public static Rank getRank(int id) {
		initBdd();
		try {
			Statement statementRank = (Statement) bdd.createStatement();
			ResultSet resultsetRank = statementRank
					.executeQuery("SELECT name, level "
							+ "FROM rank WHERE id_rank = " + id);
			resultsetRank.next();
			Rank rank = new Rank(id, resultsetRank.getInt("level"),
					resultsetRank.getString("name"));
			statementRank.close();
			return rank;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des niveaux
	 * 
	 * @return Liste de niveaux
	 */
	public static ArrayList<Rank> getRanks() {
		initBdd();
		try {
			ArrayList<Rank> ranks = new ArrayList<Rank>();
			Statement statementRank = (Statement) bdd.createStatement();
			ResultSet resultsetRank = statementRank
					.executeQuery("SELECT id_rank, name, level "
							+ "FROM rank");
			while (resultsetRank.next()) {
				Rank rank = new Rank(resultsetRank.getInt("id_rank"),
						resultsetRank.getInt("level"),
						resultsetRank.getString("name"));
				ranks.add(rank);
			}
			statementRank.close();
			return ranks;
		} catch (SQLException e) {
		}
		return null;
	}
}

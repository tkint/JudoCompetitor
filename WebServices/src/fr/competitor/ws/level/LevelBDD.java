package fr.competitor.ws.level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.Level;
import fr.competitor.ws.BddObject;

public class LevelBDD extends BddObject {
	/**
	 * Fonction qui récupère le niveau spécifié
	 * 
	 * @param id
	 * @return Niveau
	 */
	public static Level getLevel(int id) {
		initBdd();
		try {
			Statement statementLevel = (Statement) bdd.createStatement();
			ResultSet resultsetLevel = statementLevel
					.executeQuery("SELECT name, level "
							+ "FROM level WHERE id_level = " + id);
			resultsetLevel.next();
			Level level = new Level(id, resultsetLevel.getInt("level"),
					resultsetLevel.getString("name"));
			statementLevel.close();
			return level;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des niveaux
	 * 
	 * @return Liste de niveaux
	 */
	public static ArrayList<Level> getLevels() {
		initBdd();
		try {
			ArrayList<Level> levels = new ArrayList<Level>();
			Statement statementLevel = (Statement) bdd.createStatement();
			ResultSet resultsetLevel = statementLevel
					.executeQuery("SELECT id_level, name, level "
							+ "FROM level");
			while (resultsetLevel.next()) {
				Level level = new Level(resultsetLevel.getInt("id_level"),
						resultsetLevel.getInt("level"),
						resultsetLevel.getString("name"));
				levels.add(level);
			}
			statementLevel.close();
			return levels;
		} catch (SQLException e) {
		}
		return null;
	}
}

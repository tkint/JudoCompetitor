package fr.competitor.ws.region;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.Region;
import fr.competitor.ws.BddObject;

public class RegionBDD extends BddObject {
	/**
	 * Fonction qui récupère le niveau spécifié
	 * 
	 * @param id
	 * @return Region
	 */
	public static Region getRegion(int id) {
		initBdd();
		try {
			Statement statementRegion = (Statement) bdd.createStatement();
			ResultSet resultsetRegion = statementRegion
					.executeQuery("SELECT name "
							+ "FROM region WHERE id_region = " + id);
			resultsetRegion.next();
			Region region = new Region(id, resultsetRegion.getString("name"));
			statementRegion.close();
			return region;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des régions
	 * 
	 * @return Liste de régions
	 */
	public static ArrayList<Region> getRegions() {
		initBdd();
		try {
			ArrayList<Region> regions = new ArrayList<Region>();
			Statement statementRegion = (Statement) bdd.createStatement();
			ResultSet resultsetRegion = statementRegion
					.executeQuery("SELECT id_region, name FROM region");
			while (resultsetRegion.next()) {
				Region region = new Region(resultsetRegion.getInt("id_region"),
						resultsetRegion.getString("name"));
				regions.add(region);
			}
			statementRegion.close();
			return regions;
		} catch (SQLException e) {
		}
		return null;
	}
}

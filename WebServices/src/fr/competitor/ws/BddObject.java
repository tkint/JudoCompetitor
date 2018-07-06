package fr.competitor.ws;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

/**
 * Classe générale pour tous les objets des web services
 * @author Thomas
 *
 */
public class BddObject {
	// Paramètres de la base de données
	private static String url = "jdbc:mysql://sql/";
	private static String base = "competitor";
	private static String login = "root";
	private static String pass = "EpreuveE4";
	
	protected static Connection bdd = null;
	protected ArrayList<Integer> response = new ArrayList<Integer>();

	/**
	 * Initialise la connexion à la base de données
	 */
	public static void initBdd() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}
		try {
			bdd = (Connection) DriverManager.getConnection(url + base, login,
					pass);
		} catch (SQLException e) {
		}
	}
}
package fr.competitor.ws;

import com.google.gson.Gson;

/**
 * Classe qui permet de sérialiser les objets en châines JSON
 * @author Thomas
 *
 */
public class JsonManager {
	private Gson gson;
	private static volatile JsonManager instance;
	
	private JsonManager() {
		gson = new Gson();
	}

	/**
	 * Récupération de l'instance du manager
	 * @return instance
	 */
	public static JsonManager getInstance() {
		if (JsonManager.instance == null) {
			instance = new JsonManager();
		}
		return instance;
	}

	/**
	 * Sérialisation d'un objet en chaîne de caractère JSON
	 * @param o Objet
	 * @return Chaîne de caractére sérialisée en JSON
	 */
	public String toJson(Object o) {
		return gson.toJson(o);
	}
}

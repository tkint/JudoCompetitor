package fr.competitor.ws.judoka;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.Contact;
import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.model.judoka.Club;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.model.judoka.Rank;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.rank.RankBDD;

public class JudokaBDD extends BddObject {
	/**
	 * Fonction qui récupère le niveau spécifié
	 * 
	 * @param id
	 * @return Niveau
	 */
	public static Judoka getJudoka(int id, Boolean details) {
		initBdd();
		try {
			Statement statementJudoka = (Statement) bdd.createStatement();
			ResultSet resultsetJudoka = statementJudoka
					.executeQuery("SELECT address, postalCode, city, country, lastName, firstName, eMail, phone, age, height, weight, gender, id_rank, id_club "
							+ "FROM judoka AS j "
							+ "INNER JOIN contact AS c ON c.id_contact = j.id_contact "
							+ "WHERE id_judoka = " + id);
			resultsetJudoka.next();
			Contact representative = null;
			Rank rank = null;
			Club club = null;
			ArrayList<Fight> fights = null;
			ArrayList<Mark> marks = null;
			ArrayList<Competition> competitions = null;
			Judoka judoka = new Judoka(id,
					resultsetJudoka.getString("address"),
					resultsetJudoka.getString("postalCode"),
					resultsetJudoka.getString("city"),
					resultsetJudoka.getString("country"),
					resultsetJudoka.getString("lastName"),
					resultsetJudoka.getString("firstName"),
					resultsetJudoka.getString("eMail"),
					resultsetJudoka.getString("phone"),
					resultsetJudoka.getInt("age"),
					resultsetJudoka.getInt("height"),
					resultsetJudoka.getInt("weight"),
					resultsetJudoka.getBoolean("gender"), representative, rank,
					club, fights, marks, competitions);
			statementJudoka.close();
			return judoka;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des niveaux
	 * 
	 * @return Liste de niveaux
	 */
	public static ArrayList<Judoka> getJudokas(Boolean details) {
		initBdd();
		try {
			ArrayList<Judoka> judokas = new ArrayList<Judoka>();
			Statement statementJudoka = (Statement) bdd.createStatement();
			ResultSet resultsetJudoka = statementJudoka
					.executeQuery("SELECT j.id_judoka, address, postalCode, city, country, lastName, firstName, eMail, phone, age, height, weight, gender, id_rank, id_club "
							+ "FROM judoka AS j "
							+ "INNER JOIN contact AS c ON c.id_contact = j.id_contact");
			while (resultsetJudoka.next()) {
				Contact representative = null;
				Rank rank = null;
				Club club = null;
				ArrayList<Fight> fights = null;
				ArrayList<Mark> marks = null;
				ArrayList<Competition> competitions = null;
				Judoka judoka = new Judoka(
						resultsetJudoka.getInt("j.id_judoka"),
						resultsetJudoka.getString("address"),
						resultsetJudoka.getString("postalCode"),
						resultsetJudoka.getString("city"),
						resultsetJudoka.getString("country"),
						resultsetJudoka.getString("lastName"),
						resultsetJudoka.getString("firstName"),
						resultsetJudoka.getString("eMail"),
						resultsetJudoka.getString("phone"),
						resultsetJudoka.getInt("age"),
						resultsetJudoka.getInt("height"),
						resultsetJudoka.getInt("weight"),
						resultsetJudoka.getBoolean("gender"), representative,
						rank, club, fights, marks, competitions);
				judokas.add(judoka);
			}
			statementJudoka.close();
			return judokas;
		} catch (SQLException e) {
		}
		return null;
	}

	public static ArrayList<Judoka> getJudokasFight(int id) {
		initBdd();
		try {
			ArrayList<Judoka> judokas = new ArrayList<Judoka>();
			Statement statementMark = (Statement) bdd.createStatement();
			ResultSet resultsetMark = statementMark
					.executeQuery("SELECT id_judoka FROM mark WHERE id_fight = "
							+ id + " AND number = 0");
			while (resultsetMark.next()) {
				judokas.add(getJudoka(resultsetMark.getInt("id_judoka"), true));
			}
			return judokas;
		} catch (SQLException e) {
		}
		return null;
	}
}

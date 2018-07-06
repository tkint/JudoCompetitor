package fr.competitor.ws.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.article.Article;
import fr.competitor.model.article.Comment;
import fr.competitor.model.user.User;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.article.ArticleBDD;
import fr.competitor.ws.article.comment.CommentBDD;
import fr.competitor.ws.level.LevelBDD;

public class UserBDD extends BddObject {
	/**
	 * Fonction qui récupère l'utilisateur spécifié
	 * 
	 * @param id
	 * @return Utilisateur
	 */
	public static User getUser(int id, Boolean details) {
		initBdd();
		try {
			Statement statementUser = (Statement) bdd.createStatement();
			ResultSet resultsetUser = statementUser
					.executeQuery("SELECT id_user, login, password, lastName, firstName, eMail, phone, address, postalCode, city, country, id_level "
							+ "FROM user AS u "
							+ "INNER JOIN contact AS c ON u.id_contact = c.id_contact "
							+ "WHERE u.id_user = " + id);
			resultsetUser.next();
			ArrayList<Article> articles = null;
			ArrayList<Comment> comments = null;
			if (details) {
				articles = ArticleBDD.getArticlesUser(id, false);
				comments = CommentBDD.getCommentsUser(id);
			}
			User user = new User(id, resultsetUser.getString("address"),
					resultsetUser.getString("postalCode"),
					resultsetUser.getString("city"),
					resultsetUser.getString("country"),
					resultsetUser.getString("lastName"),
					resultsetUser.getString("firstName"),
					resultsetUser.getString("eMail"),
					resultsetUser.getString("phone"),
					resultsetUser.getString("login"),
					resultsetUser.getString("password"),
					LevelBDD.getLevel(resultsetUser.getInt("id_level")),
					articles, comments);
			statementUser.close();
			return user;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des utilisateurs
	 * 
	 * @return Liste d'utilisateurs
	 */
	public static ArrayList<User> getUsers(Boolean details) {
		initBdd();
		try {
			ArrayList<User> users = new ArrayList<User>();
			Statement statementUser = (Statement) bdd.createStatement();
			ResultSet resultsetUser = statementUser
					.executeQuery("SELECT id_user, login, password, lastName, firstName, eMail, phone, address, postalCode, city, country, l.id_level, name, level "
							+ "FROM user AS u "
							+ "INNER JOIN contact AS c ON u.id_contact = c.id_contact "
							+ "INNER JOIN level as l ON l.id_level = u.id_level");
			while (resultsetUser.next()) {
				ArrayList<Article> articles = null;
				ArrayList<Comment> comments = null;
				if (details) {
					articles = ArticleBDD.getArticlesUser(
							resultsetUser.getInt("id_user"), false);
					comments = CommentBDD.getCommentsUser(resultsetUser
							.getInt("id_user"));
				}
				User user = new User(resultsetUser.getInt("id_user"),
						resultsetUser.getString("address"),
						resultsetUser.getString("postalCode"),
						resultsetUser.getString("city"),
						resultsetUser.getString("country"),
						resultsetUser.getString("lastName"),
						resultsetUser.getString("firstName"),
						resultsetUser.getString("eMail"),
						resultsetUser.getString("phone"),
						resultsetUser.getString("login"),
						resultsetUser.getString("password"),
						LevelBDD.getLevel(resultsetUser.getInt("id_level")),
						articles, comments);
				users.add(user);
			}
			statementUser.close();
			return users;
		} catch (SQLException e) {
		}
		return null;
	}
}

package fr.competitor.ws.article.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.article.Comment;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.user.UserBDD;

public class CommentBDD extends BddObject {
	/**
	 * Fonction qui récupère le commentaire spécifiée
	 * 
	 * @param id
	 * @return Commentaire
	 */
	public static Comment getComment(int id) {
		initBdd();
		try {
			Statement statementComment = (Statement) bdd.createStatement();
			ResultSet resultsetComment = statementComment
					.executeQuery("SELECT id_user, dateCreate, comment "
							+ "FROM comment WHERE id_comment = " + id);
			resultsetComment.next();
			Comment comment = new Comment(id,
					resultsetComment.getString("comment"),
					resultsetComment.getDate("dateCreate"), UserBDD.getUser(
							resultsetComment.getInt("id_user"), false));
			statementComment.close();
			return comment;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des commentaires
	 * 
	 * @return Liste de commentaires
	 */
	public static ArrayList<Comment> getComments() {
		initBdd();
		try {
			ArrayList<Comment> comments = new ArrayList<Comment>();
			Statement statementComment = (Statement) bdd.createStatement();
			ResultSet resultsetComment = statementComment
					.executeQuery("SELECT id_comment, dateCreate, comment, id_user "
							+ "FROM comment");
			while (resultsetComment.next()) {
				Comment comment = new Comment(
						resultsetComment.getInt("id_comment"),
						resultsetComment.getString("comment"),
						resultsetComment.getDate("dateCreate"),
						UserBDD.getUser(resultsetComment.getInt("id_user"),
								false));
				comments.add(comment);
			}
			statementComment.close();
			return comments;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des commentaires d'un article
	 * 
	 * @return Liste de commentaires
	 */
	public static ArrayList<Comment> getCommentsArticle(int id) {
		initBdd();
		try {
			ArrayList<Comment> comments = new ArrayList<Comment>();
			Statement statementComment = (Statement) bdd.createStatement();
			ResultSet resultsetComment = statementComment
					.executeQuery("SELECT id_comment, dateCreate, comment, id_user "
							+ "FROM comment WHERE id_article = " + id);
			while (resultsetComment.next()) {
				Comment comment = new Comment(
						resultsetComment.getInt("id_comment"),
						resultsetComment.getString("comment"),
						resultsetComment.getDate("dateCreate"),
						UserBDD.getUser(resultsetComment.getInt("id_user"),
								false));
				comments.add(comment);
			}
			statementComment.close();
			return comments;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des commentaires d'un utilisateur
	 * 
	 * @return Liste de commentaires
	 */
	public static ArrayList<Comment> getCommentsUser(int id) {
		initBdd();
		try {
			ArrayList<Comment> comments = new ArrayList<Comment>();
			Statement statementComment = (Statement) bdd.createStatement();
			ResultSet resultsetComment = statementComment
					.executeQuery("SELECT id_comment, dateCreate, comment "
							+ "FROM comment WHERE id_user = " + id);
			while (resultsetComment.next()) {
				Comment comment = new Comment(
						resultsetComment.getInt("id_comment"),
						resultsetComment.getString("comment"),
						resultsetComment.getDate("dateCreate"),
						UserBDD.getUser(resultsetComment.getInt("id_user"),
								false));
				comments.add(comment);
			}
			statementComment.close();
			return comments;
		} catch (SQLException e) {
		}
		return null;
	}
}

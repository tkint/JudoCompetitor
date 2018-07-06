package fr.competitor.ws.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.Level;
import fr.competitor.model.article.Article;
import fr.competitor.model.article.Category;
import fr.competitor.model.article.Comment;
import fr.competitor.model.user.User;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.article.category.CategoryBDD;
import fr.competitor.ws.article.comment.CommentBDD;
import fr.competitor.ws.level.LevelBDD;
import fr.competitor.ws.user.UserBDD;

public class ArticleBDD extends BddObject {
	/**
	 * Fonction qui récupère le article spécifié
	 * 
	 * @param id
	 * @return Article
	 */
	public static Article getArticle(int id, Boolean details) {
		initBdd();
		try {
			Statement statementArticle = (Statement) bdd.createStatement();
			ResultSet resultsetArticle = statementArticle
					.executeQuery("SELECT title, article, dateCreate, dateUpdate, id_user, id_level "
							+ "FROM article WHERE id_article = " + id);
			resultsetArticle.next();
			User user = null;
			Level level = null;
			ArrayList<Category> categories = null;
			ArrayList<Comment> comments = null;
			if (details) {
				user = UserBDD.getUser(resultsetArticle.getInt("id_user"),
						false);
				level = LevelBDD.getLevel(resultsetArticle.getInt("id_level"));
				categories = CategoryBDD.getCategoriesArticle(id, false);
				comments = CommentBDD.getCommentsArticle(id);
			}
			Article article = new Article(id,
					resultsetArticle.getString("title"),
					resultsetArticle.getString("article"),
					resultsetArticle.getDate("dateCreate"),
					resultsetArticle.getDate("dateUpdate"), user, level,
					categories, comments);
			statementArticle.close();
			return article;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des articles
	 * 
	 * @return Liste de articles
	 */
	public static ArrayList<Article> getArticles(Boolean details) {
		initBdd();
		try {
			ArrayList<Article> articles = new ArrayList<Article>();
			Statement statementArticle = (Statement) bdd.createStatement();
			ResultSet resultsetArticle = statementArticle
					.executeQuery("SELECT id_article, title, article, dateCreate, dateUpdate, id_user, id_level "
							+ "FROM article");
			while (resultsetArticle.next()) {
				User user = null;
				Level level = null;
				ArrayList<Category> categories = null;
				ArrayList<Comment> comments = null;
				if (details) {
					user = UserBDD.getUser(resultsetArticle.getInt("id_user"),
							false);
					level = LevelBDD.getLevel(resultsetArticle
							.getInt("id_level"));
					categories = CategoryBDD.getCategoriesArticle(
							resultsetArticle.getInt("id_article"), false);
					comments = CommentBDD.getCommentsArticle(resultsetArticle
							.getInt("id_article"));
				}
				Article article = new Article(
						resultsetArticle.getInt("id_article"),
						resultsetArticle.getString("title"),
						resultsetArticle.getString("article"),
						resultsetArticle.getDate("dateCreate"),
						resultsetArticle.getDate("dateUpdate"), user, level,
						categories, comments);
				articles.add(article);
			}
			statementArticle.close();
			return articles;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des articles d'un utilisateur
	 * 
	 * @return Liste d'articles
	 */
	public static ArrayList<Article> getArticlesUser(int id, Boolean details) {
		initBdd();
		try {
			ArrayList<Article> articles = new ArrayList<Article>();
			Statement statementArticle = (Statement) bdd.createStatement();
			ResultSet resultsetArticle = statementArticle
					.executeQuery("SELECT id_article, title, article, dateCreate, dateUpdate, id_level "
							+ "FROM article WHERE id_user = " + id);
			while (resultsetArticle.next()) {
				User user = null;
				Level level = null;
				ArrayList<Category> categories = null;
				ArrayList<Comment> comments = null;
				if (details) {
					user = UserBDD.getUser(resultsetArticle.getInt("id_user"),
							false);
					level = LevelBDD.getLevel(resultsetArticle
							.getInt(id));
					categories = CategoryBDD.getCategoriesArticle(
							resultsetArticle.getInt("id_article"), false);
					comments = CommentBDD.getCommentsArticle(resultsetArticle
							.getInt("id_article"));
				}
				Article article = new Article(
						resultsetArticle.getInt("id_article"),
						resultsetArticle.getString("title"),
						resultsetArticle.getString("article"),
						resultsetArticle.getDate("dateCreate"),
						resultsetArticle.getDate("dateUpdate"), user, level,
						categories, comments);
				articles.add(article);
			}
			statementArticle.close();
			return articles;
		} catch (SQLException e) {
		}
		return null;
	}
	
	public static ArrayList<Article> getArticlesCategory(int id, Boolean details) {
		initBdd();
		try {
			ArrayList<Article> articles = new ArrayList<Article>();
			Statement statementArticle = (Statement) bdd.createStatement();
			ResultSet resultsetArticle = statementArticle
					.executeQuery("SELECT id_article, title, article, dateCreate, dateUpdate, id_level "
							+ "FROM article AS a "
							+ "INNER JOIN belongTo AS b "
							+ "ON b.id_article = a.id_article "
							+ "WHERE b.id_category = " + id);
			while (resultsetArticle.next()) {
				User user = null;
				Level level = null;
				ArrayList<Category> categories = null;
				ArrayList<Comment> comments = null;
				if (details) {
					user = UserBDD.getUser(resultsetArticle.getInt("id_user"),
							false);
					level = LevelBDD.getLevel(resultsetArticle
							.getInt(id));
					categories = CategoryBDD.getCategoriesArticle(
							resultsetArticle.getInt("id_article"), false);
					comments = CommentBDD.getCommentsArticle(resultsetArticle
							.getInt("id_article"));
				}
				Article article = new Article(
						resultsetArticle.getInt("id_article"),
						resultsetArticle.getString("title"),
						resultsetArticle.getString("article"),
						resultsetArticle.getDate("dateCreate"),
						resultsetArticle.getDate("dateUpdate"), user, level,
						categories, comments);
				articles.add(article);
			}
			statementArticle.close();
			return articles;
		} catch (SQLException e) {
		}
		return null;
	}
}

package fr.competitor.ws.article.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.article.Article;
import fr.competitor.model.article.Category;
import fr.competitor.ws.BddObject;
import fr.competitor.ws.article.ArticleBDD;

public class CategoryBDD extends BddObject {
	/**
	 * Fonction qui récupère la catégorie spécifiée
	 * 
	 * @param id
	 * @return Catégorie
	 */
	public static Category getCategory(int id, Boolean details) {
		initBdd();
		try {
			Statement statementCategory = (Statement) bdd.createStatement();
			ResultSet resultsetCategory = statementCategory
					.executeQuery("SELECT name "
							+ "FROM category WHERE id_category = " + id);
			resultsetCategory.next();
			ArrayList<Article> articles = null;
			if (details) {
				articles = ArticleBDD.getArticlesCategory(id, true);
			}
			Category category = new Category(id,
					resultsetCategory.getString("name"), articles);
			statementCategory.close();
			return category;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des catégories
	 * 
	 * @return Liste de catégories
	 */
	public static ArrayList<Category> getCategories(Boolean details) {
		initBdd();
		try {
			ArrayList<Category> categories = new ArrayList<Category>();
			Statement statementCategory = (Statement) bdd.createStatement();
			ResultSet resultsetCategory = statementCategory
					.executeQuery("SELECT id_category, name " + "FROM category");
			while (resultsetCategory.next()) {
				ArrayList<Article> articles = null;
				if (details) {
					articles = ArticleBDD.getArticlesCategory(
							resultsetCategory.getInt("c.id_category"), true);
				}
				Category category = new Category(
						resultsetCategory.getInt("id_category"),
						resultsetCategory.getString("name"), articles);
				categories.add(category);
			}
			statementCategory.close();
			return categories;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des catégories d'un article
	 * 
	 * @return Liste de catégories
	 */
	public static ArrayList<Category> getCategoriesArticle(int id,
			Boolean details) {
		initBdd();
		try {
			ArrayList<Category> categories = new ArrayList<Category>();
			Statement statementCategory = (Statement) bdd.createStatement();
			ResultSet resultsetCategory = statementCategory
					.executeQuery("SELECT c.id_category, name "
							+ "FROM category AS c "
							+ "INNER JOIN belongTo AS b ON b.id_category = c.id_category "
							+ "INNER JOIN article AS a ON a.id_article = b.id_article "
							+ "WHERE a.id_article = " + id);
			while (resultsetCategory.next()) {
				ArrayList<Article> articles = null;
				if (details) {
					articles = ArticleBDD.getArticlesCategory(
							resultsetCategory.getInt("c.id_category"), true);
				}
				Category category = new Category(
						resultsetCategory.getInt("c.id_category"),
						resultsetCategory.getString("name"), articles);
				categories.add(category);
			}
			statementCategory.close();
			return categories;
		} catch (SQLException e) {
		}
		return null;
	}
}

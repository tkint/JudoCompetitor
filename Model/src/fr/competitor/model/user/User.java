package fr.competitor.model.user;

import java.util.ArrayList;

import fr.competitor.model.Contact;
import fr.competitor.model.Level;
import fr.competitor.model.article.Article;
import fr.competitor.model.article.Comment;

public class User extends Contact {
	String login, password;
	Level level;
	ArrayList<Article> articles;
	ArrayList<Comment> comments;

	public User(Integer id, String address, String postalCode, String city, String country, String lastName,
			String firstName, String eMail, String phone, String login, String password, Level level,
			ArrayList<Article> articles, ArrayList<Comment> comments) {
		super(id, address, postalCode, city, country, lastName, firstName, eMail, phone);
		this.login = login;
		this.password = password;
		this.level = level;
		this.articles = articles;
		this.comments = comments;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", level=" + level + ", articles=" + articles
				+ ", comments=" + comments + "]";
	}
}

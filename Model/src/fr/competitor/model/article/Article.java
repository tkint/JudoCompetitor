package fr.competitor.model.article;

import java.sql.Date;
import java.util.ArrayList;

import fr.competitor.model.Level;
import fr.competitor.model.user.User;

public class Article {
	Integer id;
	String title, article;
	Date dateCreate, dateUpdate;
	User user;
	Level level;
	ArrayList<Category> categories;
	ArrayList<Comment> comments;

	public Article(Integer id, String title, String article, Date dateCreate, Date dateUpdate, User user, Level level,
			ArrayList<Category> categories, ArrayList<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.article = article;
		this.dateCreate = dateCreate;
		this.dateUpdate = dateUpdate;
		this.user = user;
		this.level = level;
		this.categories = categories;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", article=" + article + ", dateCreate=" + dateCreate
				+ ", dateUpdate=" + dateUpdate + ", user=" + user + ", level=" + level + ", categories=" + categories
				+ ", comments=" + comments + "]";
	}
}

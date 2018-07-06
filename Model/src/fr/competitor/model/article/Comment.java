package fr.competitor.model.article;

import java.sql.Date;

import fr.competitor.model.user.User;

public class Comment {
	Integer id;
	String comment;
	Date dateCreate;
	User user;

	public Comment(Integer id, String comment, Date dateCreate, User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.dateCreate = dateCreate;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", dateCreate=" + dateCreate + ", user=" + user.toString()
				+ "]";
	}
}

package fr.competitor.model.judoka;

import java.util.ArrayList;

import fr.competitor.model.Contact;
import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.competition.phase.fight.Fight;

public class Judoka extends Contact {
	Integer age, height, weight;
	Boolean gender;
	Contact representative;
	Rank rank;
	Club club;
	ArrayList<Fight> matches;
	ArrayList<Mark> marks;
	ArrayList<Competition> competitions;

	public Judoka(Integer id, String address, String postalCode, String city, String country, String lastName,
			String firstName, String eMail, String phone, Integer age, Integer height, Integer weight, Boolean gender,
			Contact representative, Rank rank, Club club, ArrayList<Fight> matches, ArrayList<Mark> marks,
			ArrayList<Competition> competitions) {
		super(id, address, postalCode, city, country, lastName, firstName, eMail, phone);
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.representative = representative;
		this.rank = rank;
		this.club = club;
		this.matches = matches;
		this.marks = marks;
		this.competitions = competitions;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Contact getRepresentative() {
		return representative;
	}

	public void setRepresentative(Contact representative) {
		this.representative = representative;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public ArrayList<Fight> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Fight> matches) {
		this.matches = matches;
	}

	public ArrayList<Mark> getMarks() {
		return marks;
	}

	public void setMarks(ArrayList<Mark> marks) {
		this.marks = marks;
	}

	public ArrayList<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(ArrayList<Competition> competitions) {
		this.competitions = competitions;
	}

	@Override
	public String toString() {
		return "Judoka [age=" + age + ", height=" + height + ", weight=" + weight + ", gender=" + gender
				+ ", representative=" + representative + ", rank=" + rank + ", club=" + club + ", matches=" + matches
				+ ", marks=" + marks + ", competitions=" + competitions + "]";
	}
}

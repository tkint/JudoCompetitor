package fr.competitor.model.competition.phase;

import java.util.ArrayList;

import fr.competitor.model.competition.phase.fight.Fight;

public class Phase {
	Integer id;
	String name;
	ArrayList<Fight> fights;

	public Phase(Integer id, String name, ArrayList<Fight> fights) {
		super();
		this.id = id;
		this.name = name;
		this.fights = fights;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Fight> getFights() {
		return fights;
	}

	public void setFights(ArrayList<Fight> fights) {
		this.fights = fights;
	}

	@Override
	public String toString() {
		return "Phase [id=" + id + ", name=" + name + ", fights=" + fights + "]";
	}
}

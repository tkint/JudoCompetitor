package fr.competitor.model.competition.phase.fight;

public class Move {
	Integer id, points;
	String name;

	public Move(Integer id, Integer points, String name) {
		super();
		this.id = id;
		this.points = points;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Move [id=" + id + ", points=" + points + ", name=" + name + "]";
	}
}

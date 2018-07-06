package fr.competitor.model.judoka;

public class Rank {
	Integer id, level;
	String name;

	public Rank(Integer id, Integer level, String name) {
		super();
		this.id = id;
		this.level = level;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Rank [id=" + id + ", level=" + level + ", name=" + name + "]";
	}
}

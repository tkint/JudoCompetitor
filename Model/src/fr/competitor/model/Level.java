package fr.competitor.model;

public class Level {
	Integer id, level;
	String name;

	public Level(Integer id, Integer level, String name) {
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
		return "Level [id=" + id + ", level=" + level + ", name=" + name + "]";
	}
}

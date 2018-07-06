package fr.competitor.model.judoka;

import java.util.ArrayList;

import fr.competitor.model.Region;

public class Club {
	String name;
	Region region;
	ArrayList<Judoka> judokas;

	public Club(String name, Region region, ArrayList<Judoka> judokas) {
		super();
		this.name = name;
		this.region = region;
		this.judokas = judokas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public ArrayList<Judoka> getJudokas() {
		return judokas;
	}

	public void setJudokas(ArrayList<Judoka> judokas) {
		this.judokas = judokas;
	}

	@Override
	public String toString() {
		return "Club [name=" + name + ", region=" + region + ", judokas=" + judokas + "]";
	}
}

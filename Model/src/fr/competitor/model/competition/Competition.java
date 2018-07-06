package fr.competitor.model.competition;

import java.sql.Date;
import java.util.ArrayList;

import fr.competitor.model.Details;
import fr.competitor.model.Region;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.model.judoka.Rank;

public class Competition extends Details {
	String name;
	Integer capacity;
	Date dateStart, dateEnd;
	Region region;
	ArrayList<Rank> ranks;
	ArrayList<Judoka> judokas;
	ArrayList<Phase> phases;

	public Competition(Integer id, String address, String postalCode, String city, String country, String name,
			Integer capacity, Date dateStart, Date dateEnd, Region region, ArrayList<Rank> ranks,
			ArrayList<Judoka> judokas, ArrayList<Phase> phases) {
		super(id, address, postalCode, city, country);
		this.name = name;
		this.capacity = capacity;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.region = region;
		this.ranks = ranks;
		this.judokas = judokas;
		this.phases = phases;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public ArrayList<Rank> getRanks() {
		return ranks;
	}

	public void setRanks(ArrayList<Rank> ranks) {
		this.ranks = ranks;
	}

	public ArrayList<Judoka> getJudokas() {
		return judokas;
	}

	public void setJudokas(ArrayList<Judoka> judokas) {
		this.judokas = judokas;
	}

	public ArrayList<Phase> getPhases() {
		return phases;
	}

	public void setPhases(ArrayList<Phase> phases) {
		this.phases = phases;
	}

	@Override
	public String toString() {
		return "Competition [name=" + name + ", capacity=" + capacity + ", dateStart=" + dateStart + ", dateEnd="
				+ dateEnd + ", region=" + region + ", ranks=" + ranks + ", judokas=" + judokas + ", phases=" + phases
				+ "]";
	}
}

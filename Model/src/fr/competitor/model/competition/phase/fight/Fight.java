package fr.competitor.model.competition.phase.fight;

import java.util.ArrayList;

import fr.competitor.model.judoka.Judoka;

public class Fight {
	Integer id;
	Judoka opponentOne, opponentTwo;
	ArrayList<Mark> marks;

	public Fight(Integer id, Judoka opponentOne, Judoka opponentTwo, ArrayList<Mark> marks) {
		super();
		this.id = id;
		this.opponentOne = opponentOne;
		this.opponentTwo = opponentTwo;
		this.marks = marks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Judoka getOpponentOne() {
		return opponentOne;
	}

	public void setOpponentOne(Judoka opponentOne) {
		this.opponentOne = opponentOne;
	}

	public Judoka getOpponentTwo() {
		return opponentTwo;
	}

	public void setOpponentTwo(Judoka opponentTwo) {
		this.opponentTwo = opponentTwo;
	}

	public ArrayList<Mark> getMarks() {
		return marks;
	}

	public void setMarks(ArrayList<Mark> marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Fight [id=" + id + ", opponentOne=" + opponentOne + ", opponentTwo=" + opponentTwo + ", marks=" + marks
				+ "]";
	}
}

package fr.competitor.model.competition.phase.fight;

import fr.competitor.model.judoka.Judoka;

public class Mark {
	Integer order;
	Judoka judoka;
	Move move;

	public Mark(Integer order, Judoka judoka, Move move) {
		super();
		this.order = order;
		this.judoka = judoka;
		this.move = move;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Judoka getJudoka() {
		return judoka;
	}

	public void setJudoka(Judoka judoka) {
		this.judoka = judoka;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	@Override
	public String toString() {
		return "Mark [order=" + order + ", judoka=" + judoka + ", move=" + move + "]";
	}
}

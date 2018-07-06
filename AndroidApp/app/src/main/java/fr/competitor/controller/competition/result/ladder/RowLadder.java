package fr.competitor.controller.competition.result.ladder;

import java.util.ArrayList;

import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.judoka.Judoka;

/**
 * Created by Thomas on 21/05/2016.
 */
public class RowLadder {
    private Judoka judoka;
    private Integer fights, wins, points;

    public RowLadder(Judoka judoka, Competition competition) {
        this.judoka = judoka;
        getData(competition);
    }

    private void getData(Competition competition) {
        ArrayList<Fight> fights = getJudokaFights(competition);
        this.fights = fights.size();
        int wins = 0;
        int points = 0;
        for (Fight fight:
             fights) {
            if (defineWinner(fight).getId().equals(this.judoka.getId()))
                wins++;
            points += getPointsJudokaFight(this.judoka, fight);
        }
        this.wins = wins;
        this.points = points;
    }

    private ArrayList<Fight> getJudokaFights(Competition competition) {
        ArrayList<Fight> fights = new ArrayList<>();
        for (Phase phase :
                competition.getPhases()) {
            for (Fight fight :
                    phase.getFights()) {
                if (this.judoka.getId().equals(fight.getOpponentOne().getId()) ||
                        this.judoka.getId().equals(fight.getOpponentTwo().getId())) {
                    fights.add(fight);
                }
            }
        }
        return fights;
    }

    private Judoka defineWinner(Fight fight) {
        Judoka judoka1 = fight.getOpponentOne();
        Judoka judoka2 = fight.getOpponentTwo();
        if (getPointsJudokaFight(judoka1, fight) > getPointsJudokaFight(judoka2, fight))
            return judoka1;
        return judoka2;
    }

    private int getPointsJudokaFight(Judoka judoka, Fight fight) {
        int points = 0;
        for (Mark mark:
                fight.getMarks()) {
            if (mark.getJudoka().getId().equals(judoka.getId())) {
                points += mark.getMove().getPoints();
            }
        }
        return points;
    }

    public Judoka getJudoka() {
        return judoka;
    }

    public void setJudoka(Judoka judoka) {
        this.judoka = judoka;
    }

    public Integer getFights() {
        return fights;
    }

    public void setFights(Integer fights) {
        this.fights = fights;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}

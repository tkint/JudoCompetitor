package fr.competitor.controller.competition.result.ladder;

import android.database.DatabaseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.model.judoka.Judoka;

/**
 * Created by Thomas on 21/05/2016.
 */
public class Ladder {
    List<RowLadder> rowLadders;

    public Ladder(Competition competition) {
        List<RowLadder> rowLadders = new ArrayList<>();
        for (Judoka judoka:
             competition.getJudokas()) {
            RowLadder rowLadder = new RowLadder(judoka, competition);
            rowLadders.add(rowLadder);
        }
        Collections.sort(rowLadders, new Comparator<RowLadder>() {
            @Override
            public int compare(RowLadder rowLadder1, RowLadder rowLadder2) {
                return rowLadder2.getPoints().compareTo(rowLadder1.getPoints());
            }
        });
        this.rowLadders = rowLadders;
    }

    public List<RowLadder> getRowLadders() {
        return rowLadders;
    }

    public RowLadder getRowLadder(int position) {
        return rowLadders.get(position);
    }

    public void setRowLadders(List<RowLadder> rowLadders) {
        this.rowLadders = rowLadders;
    }
}

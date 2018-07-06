package fr.competitor.controller.competition;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.competitor.Config;
import fr.competitor.controller.WebService;
import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.judoka.Judoka;

/**
 * Created by Thomas on 08/05/2016.
 */
public class CompetitionWSClient {
    private WebService ws = new WebService();
    private Gson gson = new Gson();

    public Competition getCompetition(int id) {
        try {
            String result = ws.execute(Config.PATH_WS_COMPETITION + "/" + id).get();
            return gson.fromJson(result, Competition.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Competition> getCompetitions() {
        try {
            String result = ws.execute(Config.PATH_WS_COMPETITION).get();
            return gson.fromJson(result, new TypeToken<List<Competition>>() {
            }.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Competition> getPastCompetitions() {
        java.util.Date dt = new java.util.Date();
        Date dateBase = new Date(dt.getTime());
        ArrayList<Competition> competitions = new ArrayList<Competition>();
        for (Competition compet:
             getCompetitions()) {
            if (compet.getDateEnd().before(dateBase)) {
                competitions.add(compet);
            }
        }
        return competitions;
    }

    public List<Phase> getPhasesCompetition(int idCompetition) {
        ArrayList<Phase> phases = new ArrayList<>();
        Competition competition = getCompetition(idCompetition);
        for (Phase phase:
             competition.getPhases()) {
            phases.add(phase);
        }
        return phases;
    }

    public Phase getPhase(int idPhase, int idCompetition) {
        for (Phase phase:
             getPhasesCompetition(idCompetition)) {
            if (phase.getId() == idPhase)
                return phase;
        }
        return null;
    }

    public List<Competition> getJudokaCompetitions(Judoka judoka) {
        ArrayList<Competition> competitions = new ArrayList<Competition>();
        for (Competition compet:
             getPastCompetitions()) {
            for (Judoka j:
                 compet.getJudokas()) {
                if (j.getId() == judoka.getId()) {
                    competitions.add(compet);
                }
            }
        }
        return competitions;
    }
}
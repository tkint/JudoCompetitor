package fr.competitor.controller.competition.result.phase.fight.mark.move;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.competitor.Config;
import fr.competitor.controller.WebService;
import fr.competitor.model.competition.phase.fight.Move;

/**
 * Created by Thomas on 21/05/2016.
 */
public class MoveWSClient extends WebService {
    private WebService ws = new WebService();
    private Gson gson = new Gson();

    public Move getMove(int id) {
        try {
            String result = ws.execute(Config.PATH_WS_MOVE + "/" + id).get();
            return gson.fromJson(result, Move.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Move> getMoves() {
        try {
            String result = ws.execute(Config.PATH_WS_MOVE).get();
            return gson.fromJson(result, new TypeToken<List<Move>>() {
            }.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

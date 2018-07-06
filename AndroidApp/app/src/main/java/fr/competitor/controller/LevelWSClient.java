package fr.competitor.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.competitor.Config;
import fr.competitor.model.Level;

/**
 * Created by Thomas on 08/05/2016.
 */
public class LevelWSClient {
    private WebService ws = new WebService();
    private Gson gson = new Gson();

    public Level getLevel(int id) {
        try {
            String result = ws.execute(Config.PATH_WS_LEVEL + "/" + id).get();
            return gson.fromJson(result, Level.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Level> getLevels() {
        try {
            String result = ws.execute(Config.PATH_WS_LEVEL).get();
            return gson.fromJson(result, new TypeToken<List<Level>>(){}.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

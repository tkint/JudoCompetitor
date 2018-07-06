package fr.competitor.controller.user.judoka;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.competitor.Config;
import fr.competitor.controller.WebService;
import fr.competitor.model.judoka.Judoka;

/**
 * Created by Thomas on 09/05/2016.
 */
public class JudokaWSClient {
    private WebService ws = new WebService();
    private Gson gson = new Gson();

    public Judoka getJudoka(int id) {
        try {
            String result = ws.execute(Config.PATH_WS_JUDOKA + "/" + id).get();
            return gson.fromJson(result, Judoka.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Judoka> getJudokas() {
        try {
            String result = ws.execute(Config.PATH_WS_JUDOKA).get();
            return gson.fromJson(result, new TypeToken<List<Judoka>>() {
            }.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

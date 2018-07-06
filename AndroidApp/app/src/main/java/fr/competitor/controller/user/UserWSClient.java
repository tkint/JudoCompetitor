package fr.competitor.controller.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.competitor.Config;
import fr.competitor.controller.WebService;
import fr.competitor.model.user.User;

/**
 * Created by Thomas on 09/05/2016.
 */
public class UserWSClient {
    private WebService ws = new WebService();
    private Gson gson = new Gson();

    public User getUser(int id) {
        try {
            String result = ws.execute(Config.PATH_WS_USER + "/" + id).get();
            return gson.fromJson(result, User.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User connect(String login, String password) {
        try {
            String result = ws.execute(Config.PATH_WS_USER + "/" + login + "/" + password).get();
            return gson.fromJson(result, User.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers() {
        try {
            String result = ws.execute(Config.PATH_WS_USER).get();
            return gson.fromJson(result, new TypeToken<List<User>>() {
            }.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

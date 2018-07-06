package fr.competitor;

import fr.competitor.model.user.User;

/**
 * Created by Thomas on 17/05/2016.
 */
public class Config {
    public static final String PATH_WS = "http://192.168.16.134:9090/CompetitorWS";
    public static final String PATH_WS_USER = "/User";
    public static final String PATH_WS_JUDOKA = "/Judoka";
    public static final String PATH_WS_LEVEL = "/Level";
    public static final String PATH_WS_COMPETITION = "/Competition";
    public static final String PATH_WS_MOVE = "/Move";
    public static final String PATH_WS_ARTICLE = "/Article";

    public static final String VAR_USER_ID = "idUser";
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

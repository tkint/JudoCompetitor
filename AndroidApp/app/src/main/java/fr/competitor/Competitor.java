package fr.competitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import fr.competitor.controller.user.UserWSClient;
import fr.competitor.model.user.User;

/**
 * Created by Thomas on 17/05/2016.
 */
public class Competitor extends AppCompatActivity {
    protected String log = "";
    protected User user = null;

    public static void setPreference(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreference(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    protected void refreshPreferences(Context context) {
        if (getPreference(Config.VAR_USER_ID, context) != null) {
            user = connect(Integer.parseInt(getPreference(Config.VAR_USER_ID, context)));
        }
    }

    protected User connect(int userId) {
        if (userId != 0)
            return new UserWSClient().getUser(userId);
        return null;
    }

    protected void disconnect() {
        user = null;
    }

    protected Boolean isConnected() {
        if (user != null)
            return true;
        return false;
    }
}

package fr.competitor.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.competitor.Competitor;
import fr.competitor.Config;
import fr.competitor.R;
import fr.competitor.view.article.Articles;
import fr.competitor.view.competition.Competitions;
import fr.competitor.view.competition.result.Results;
import fr.competitor.view.user.Users;

public class Home extends Competitor {
    Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }

    private void init() {
        connect = (Button) findViewById(R.id.connect);
    }

    private void refresh() {
        refreshPreferences(Home.this);
        if (isConnected())
            connect.setText(getResources().getText(R.string.h_disconnect));
        else
            connect.setText(getResources().getText(R.string.h_connect));
    }

    public void onClick(View v) {
        Boolean disconnect = false;
        Class target = null;
        switch (v.getId()) {
            case R.id.article: // Bouton article
                target = Articles.class;
                break;
            case R.id.user: // Bouton membre
                if (isConnected())
                    target = Users.class;
                else
                    Toast.makeText(Home.this, "Vous devez être connecté", Toast.LENGTH_SHORT).show();
                break;
            case R.id.competition: // Bouton competition
                target = Competitions.class;
                break;
            case R.id.result: // Bouton resultats
                if (isConnected())
                    target = Results.class;
                else
                    Toast.makeText(Home.this, "Vous devez être connecté", Toast.LENGTH_SHORT).show();
                break;
            case R.id.connect: // Bouton connexion
                if (isConnected()) {
                    setPreference(Config.VAR_USER_ID, "0", Home.this);
                    refresh();
                    disconnect();
                    disconnect = true;
                } else {
                    target = Connect.class;
                }
                break;
            case R.id.about: // Bouton à propos
                target = About.class;
                break;
        }
        if (target == null) {
            if (isConnected() && disconnect)
                log = "Utilisateur déconnecté";
            else
                log = "Activité non définie";
        } else {
            Intent intent = new Intent(this, target);
            startActivity(intent);
            log = "Changement d'activité -> " + target.getSimpleName();
        }
        Log.d("onClick", log);
    }
}

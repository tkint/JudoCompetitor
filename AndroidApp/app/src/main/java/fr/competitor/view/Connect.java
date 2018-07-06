package fr.competitor.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.competitor.Competitor;
import fr.competitor.Config;
import fr.competitor.R;
import fr.competitor.model.user.User;
import fr.competitor.controller.user.UserWSClient;

public class Connect extends Competitor {
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        init();
    }

    private void init() {
        login = (EditText) findViewById(R.id.login_input);
        password = (EditText) findViewById(R.id.password_input);
    }

    public void connectUser(View button) {
        if (login.getText().toString().matches("") || password.getText().toString().matches("")) {
            Toast.makeText(Connect.this, "Identifiant ou mot de passe vide", Toast.LENGTH_SHORT).show();
            Log.d("connectUser", "Identifiant ou mot de passe vide");
            return;
        }
        User user = connect(login.getText().toString(), md5(password.getText().toString()));

        if (user != null) {
            log = "Bienvenue " + user.getFirstName();
            setPreference(Config.VAR_USER_ID, user.getId().toString(), Connect.this);
            finish();
        } else {
            log = "Mauvais identifiants";
        }
        Log.d("connectUser", log);
        Toast.makeText(Connect.this, log, Toast.LENGTH_SHORT).show();
    }

    private User connect(String login, String password) {
        return new UserWSClient().connect(login, password);
    }

    private String md5(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte digest[] = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();
            for (byte str :
                    digest) {
                String s = Integer.toHexString(0xFF & str);
                while (s.length() < 2) {
                    s = "0" + s;
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package fr.competitor.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.controller.user.judoka.JudokaWSClient;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.model.user.User;
import fr.competitor.controller.user.UserAdapter;
import fr.competitor.controller.user.UserWSClient;

public class Users extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        init();
        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }

    private void init() {
        liste = (ListView) findViewById(R.id.liste);
    }

    private void refresh() {
        populateView();
        clickCallBack();
    }

    private void populateView() {
        List<Judoka> judokas = new JudokaWSClient().getJudokas();
        ArrayAdapter adapter = new UserAdapter(Users.this, judokas);
        liste.setAdapter(adapter);
    }

    private void clickCallBack() {
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View clicked, int position, long id) {
                TextView idArticle = (TextView) clicked.findViewById(R.id.idUser);
                changeToUser(Integer.parseInt(idArticle.getText().toString()));
            }
        });
    }

    private void changeToUser(int id) {
        Intent intent = new Intent(this, ViewUser.class);
        intent.putExtra("EXTRA_ID", id);
        startActivity(intent);
    }
}

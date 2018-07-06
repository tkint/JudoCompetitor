package fr.competitor.view.competition.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.controller.competition.CompetitionWSClient;
import fr.competitor.controller.competition.result.ResultAdapter;
import fr.competitor.model.competition.Competition;

public class Results extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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
        List<Competition> competitions = new CompetitionWSClient().getPastCompetitions();
        ArrayAdapter adapter = new ResultAdapter(Results.this, competitions);
        liste.setAdapter(adapter);
    }

    private void clickCallBack() {
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View clicked, int position, long id) {
                TextView idCompetition = (TextView) clicked.findViewById(R.id.idCompetition);
                changeToCompetition(Integer.parseInt(idCompetition.getText().toString()));
            }
        });
    }

    private void changeToCompetition(int id) {
        Intent intent = new Intent(this, ViewResult.class);
        intent.putExtra("EXTRA_ID", id);
        startActivity(intent);
    }
}

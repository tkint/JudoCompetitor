package fr.competitor.view.competition.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.controller.competition.CompetitionWSClient;
import fr.competitor.controller.competition.result.LadderAdapter;
import fr.competitor.controller.competition.result.ladder.Ladder;
import fr.competitor.controller.competition.result.phase.PhaseAdapter;
import fr.competitor.controller.competition.result.phase.fight.FightAdapter;
import fr.competitor.model.competition.Competition;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.competition.phase.fight.Fight;

public class ViewResult extends AppCompatActivity {
    ListView ladderData;
    TextView idCompetition;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

        Intent intent = getIntent();
        int id = intent.getIntExtra("EXTRA_ID", 0);
        Competition target = new CompetitionWSClient().getCompetition(id);

        init();
        initResult(target);
        populateView(target);
    }

    private void init() {
        ladderData = (ListView) findViewById(R.id.ladderData);
        idCompetition = (TextView) findViewById(R.id.idCompetition);
        name = (TextView) findViewById(R.id.name);
    }

    private void initResult(Competition target) {
        idCompetition.setText(target.getId().toString());
        name.setText(target.getName());
    }

    private void populateView(Competition target) {
        try {
            Ladder ladder = new Ladder(target);
            ArrayAdapter adapter = new LadderAdapter(ViewResult.this, ladder.getRowLadders());
            ladderData.setAdapter(adapter);
        } catch (Exception e) { Log.d("Exception", e.getMessage()); }
    }
}

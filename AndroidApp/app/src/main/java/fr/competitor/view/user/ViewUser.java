package fr.competitor.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.competitor.Competitor;
import fr.competitor.R;
import fr.competitor.controller.competition.CompetitionAdapter;
import fr.competitor.controller.competition.CompetitionWSClient;
import fr.competitor.controller.competition.result.ladder.Ladder;
import fr.competitor.controller.user.judoka.JudokaWSClient;
import fr.competitor.model.competition.Competition;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.model.user.User;

public class ViewUser extends Competitor {
    TextView name;
    TextView club;
    TextView rank;
    TextView age;
    TextView gender;
    TextView height;
    TextView weight;
    ListView competitions;
    TextView error;

    EditText start;
    EditText end;
    TextView result;

    Judoka judoka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        Intent intent = getIntent();
        int id = intent.getIntExtra("EXTRA_ID", 0);
        Judoka target = new JudokaWSClient().getJudoka(id);
        judoka = target;

        init();
        initUser(target);
        refreshPreferences(ViewUser.this);
    }

    private void init() {
        name = (TextView) findViewById(R.id.name);
        club = (TextView) findViewById(R.id.club);
        rank = (TextView) findViewById(R.id.rank);
        age = (TextView) findViewById(R.id.age);
        gender = (TextView) findViewById(R.id.gender);
        height = (TextView) findViewById(R.id.height);
        weight = (TextView) findViewById(R.id.weight);
        error = (TextView) findViewById(R.id.error);

        start = (EditText) findViewById(R.id.start_input);
        end = (EditText) findViewById(R.id.end_input);
        result = (TextView) findViewById(R.id.result);
    }

    private void initUser(Judoka judoka) {
        name.setText(judoka.getFirstName() + " " + judoka.getLastName());
        if (judoka.getClub() != null)
            club.setText("Club: " + judoka.getClub().getName().toString());
        if (judoka.getRank() != null)
            rank.setText("Grade: " + judoka.getRank().getName().toString());
        age.setText("Age: " + judoka.getAge().toString());
        if (judoka.getGender()) {
            gender.setText("Sexe: F");
        } else {
            gender.setText("Sexe: H");
        }
        height.setText("Taille: " + judoka.getHeight().toString() + "cm");
        weight.setText("Poids: " + judoka.getWeight().toString() + "kg");
        //populateCompetitions(judoka.getCompetitions());
    }

    private Judoka isJudoka(User user) {
        for (Judoka judoka :
                new JudokaWSClient().getJudokas()) {
            if (judoka.getId().equals(user.getId())) {
                return judoka;
            }
        }
        return null;
    }

    private void populateCompetitions(List<Competition> c) {
        ArrayAdapter adapter = new CompetitionAdapter(ViewUser.this, c);
        competitions.setAdapter(adapter);
    }

    public void search(View button) {
        if (start.getText().toString().matches("") || end.getText().toString().matches("")) {
            Toast.makeText(ViewUser.this, "Champs de recherche vides", Toast.LENGTH_SHORT).show();
            Log.d("search", "Champs de recherche vides");
            return;
        } else if (!isConnected()) {
            Toast.makeText(ViewUser.this, "Vous devez être connecté", Toast.LENGTH_SHORT).show();
            Log.d("search", "Vous devez être connecté");
        } else if (user.getLevel().getLevel() < 11) {
            Toast.makeText(ViewUser.this, "Vous n'avez pas les droits d'accéder à cette fonction", Toast.LENGTH_SHORT).show();
            Log.d("search", "Vous n'avez pas les droits d'accéder à cette fonction");
        } else {
            int first = countClassement(1, Integer.parseInt(start.getText().toString()), Integer.parseInt(end.getText().toString()), judoka);
            int second = countClassement(2, Integer.parseInt(start.getText().toString()), Integer.parseInt(end.getText().toString()), judoka);
            if (first >= 0 && second >= 0) {
                String str = "Le judoka " + judoka.getFirstName() + " " + judoka.getLastName() + " a été ";
                str +=  Integer.toString(first) + " fois premier et ";
                str +=  Integer.toString(second) + " fois second durant cet intervale";
                result.setText(str);
            } else {
                Toast.makeText(ViewUser.this, "Il n'y a pas de compétition dans cet intervale", Toast.LENGTH_SHORT).show();
                Log.d("search", "Il n'y a pas de compétition dans cet intervale");
            }
        }
    }

    private int countClassement(int position, int debut, int fin, Judoka judoka) {
        int count = 0;
        ArrayList<Competition> compets = new ArrayList<Competition>();
        Calendar cal = Calendar.getInstance();
        for (Competition competition :
                new CompetitionWSClient().getCompetitions()) {
            cal.setTime(competition.getDateStart());
            int year = cal.get(Calendar.YEAR);
            Log.d("Dates: ", debut + " | " + year + " | " + fin);
            if (year >= debut && year <= fin) {
                compets.add(competition);
            }
        }

        if (compets.size() < 1) {
            return -1;
        }

        position--;
        Ladder ladder;

        for (Competition competition :
                compets) {
            ladder = new Ladder(competition);
            if (ladder.getRowLadders() != null && ladder.getRowLadders().size() > position) {
                if (judoka.getId() == ladder.getRowLadder(position).getJudoka().getId()) {
                    count++;
                }
            }
        }
        return count;
    }
}

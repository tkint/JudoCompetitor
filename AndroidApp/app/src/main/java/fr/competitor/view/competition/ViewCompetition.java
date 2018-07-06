package fr.competitor.view.competition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

import fr.competitor.R;
import fr.competitor.model.competition.Competition;
import fr.competitor.controller.competition.CompetitionWSClient;

public class ViewCompetition extends AppCompatActivity {
    TextView idCompetition;
    TextView name;
    TextView date;
    TextView address;
    TextView city;
    TextView region;
    TextView capacity;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_competition);

        Intent intent = getIntent();
        int id = intent.getIntExtra("EXTRA_ID", 0);
        Competition target = new CompetitionWSClient().getCompetition(id);

        init();
        initCompetition(target);
    }

    private void init() {
        idCompetition = (TextView) findViewById(R.id.idCompetition);
        name = (TextView) findViewById(R.id.name);
        date = (TextView) findViewById(R.id.date);
        address = (TextView) findViewById(R.id.address);
        city = (TextView) findViewById(R.id.city);
        region = (TextView) findViewById(R.id.region);
        capacity = (TextView) findViewById(R.id.capacity);
        calendar = (CalendarView) findViewById(R.id.calendar);
    }

    private void initCompetition(Competition target) {
        idCompetition.setText(target.getId().toString());
        name.setText(target.getName());
        date.setText("Du " + target.getDateStart() + " au " + target.getDateEnd());
        address.setText(target.getAddress());
        city.setText(target.getPostalCode() + " - " + target.getCity());
        region.setText(target.getRegion().getName());
        capacity.setText(target.getCapacity() - target.getJudokas().size() + "/" + target.getCapacity());

        Calendar cal = Calendar.getInstance();
        cal.setTime(target.getDateStart());
        calendar.setDate(cal.getTimeInMillis());
    }
}

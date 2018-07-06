package fr.competitor.view;

import android.os.Bundle;
import android.widget.TextView;

import fr.competitor.Competitor;
import fr.competitor.R;

public class About extends Competitor {
    TextView session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        init();
        refresh();
    }

    private void init() {
        session = (TextView) findViewById(R.id.session);
    }

    private void refresh() {
        refreshPreferences(About.this);
        if (isConnected())
            session.setText(getResources().getText(R.string.p_session) + " " + user.getFirstName() + " " + user.getLastName());
    }
}

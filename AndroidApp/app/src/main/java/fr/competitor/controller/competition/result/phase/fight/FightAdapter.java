package fr.competitor.controller.competition.result.phase.fight;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.competitor.R;
import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.model.judoka.Judoka;

/**
 * Created by Thomas on 19/05/2016.
 */
public class FightAdapter extends ArrayAdapter<Fight> {
    List<Fight> fights;

    public FightAdapter(Context context, List<Fight> fights) {
        super(context, 0, fights);
        this.fights = fights;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_fight, parent, false);
        }

        FightViewHolder viewHolder = (FightViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new FightViewHolder();

            viewHolder.idFight = (TextView) convertView.findViewById(R.id.idFight);
            viewHolder.judoka1 = (TextView) convertView.findViewById(R.id.judoka1);
            viewHolder.rank1 = (TextView) convertView.findViewById(R.id.rank1);
            viewHolder.marks1 = (ListView) convertView.findViewById(R.id.marks1);

            viewHolder.judoka2 = (TextView) convertView.findViewById(R.id.judoka2);
            viewHolder.rank2 = (TextView) convertView.findViewById(R.id.rank2);
            viewHolder.marks2 = (ListView) convertView.findViewById(R.id.marks2);
            convertView.setTag(viewHolder);
        }

        Fight fight = getItem(position);

        //Log.d("Judoka 1", fight.getOpponentOne().getRank().toString());

        viewHolder.judoka1.setText("Thomas KINT");
        viewHolder.judoka2.setText("John WAYNE");

        Log.d("Position", Integer.toString(position));
        Log.d("Context", getContext().toString());

        // Judoka 1
        Judoka judoka1 = fight.getOpponentOne();
        viewHolder.judoka1.setText(judoka1.getFirstName() + " " + judoka1.getLastName());
        /*viewHolder.rank1.setText(judoka1.getRank().getName());
        // Population de la liste de mouvement
        ArrayList<Mark> marks1 = getJudokaMarksByFight(fight, judoka1);
        ArrayAdapter adapter1 = new MarkAdapter(super.getContext(), marks1, fight);
        viewHolder.marks1.setAdapter(adapter1);

        // Judoka 2
        Judoka judoka2 = fight.getOpponentTwo();
        viewHolder.judoka1.setText(judoka2.getLastName());
        viewHolder.rank1.setText(judoka2.getRank().getName());
        // Population de la liste de mouvement
        ArrayList<Mark> marks2 = getJudokaMarksByFight(fight, judoka2);
        ArrayAdapter adapter2 = new MarkAdapter(super.getContext(), marks2, fight);
        viewHolder.marks2.setAdapter(adapter2);*/

        return convertView;
    }

    private ArrayList<Mark> getJudokaMarksByFight(Fight fight, Judoka judoka) {
        ArrayList<Mark> marks = new ArrayList<>();
        for (Mark mark :
                fight.getMarks()) {
            if (mark.getJudoka().getId() == judoka.getId()) {
                marks.add(mark);
            }
        }
        return marks;
    }

    @Override
    public int getCount() {
        return fights.size();
    }

    public class FightViewHolder {
        public TextView idFight;
        public TextView judoka1;
        public TextView rank1;
        public ListView marks1;
        public TextView judoka2;
        public TextView rank2;
        public ListView marks2;
    }
}
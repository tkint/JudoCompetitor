package fr.competitor.controller.competition.result.phase;

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
import fr.competitor.controller.competition.result.phase.fight.FightAdapter;
import fr.competitor.model.competition.phase.Phase;
import fr.competitor.model.competition.phase.fight.Fight;

/**
 * Created by Thomas on 19/05/2016.
 */
public class PhaseAdapter extends ArrayAdapter<Phase> {
    public PhaseAdapter(Context context, List<Phase> phases) {
        super(context, 0, phases);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_phase, parent, false);
        }

        PhaseViewHolder viewHolder = (PhaseViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new PhaseViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }

        Phase phase = getItem(position);

        viewHolder.name.setText(phase.getName().toString());

        return convertView;
    }

    public class PhaseViewHolder {
        public TextView name;
        public ListView listeFights;
    }
}
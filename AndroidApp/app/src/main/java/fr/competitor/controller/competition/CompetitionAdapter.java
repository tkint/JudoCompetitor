package fr.competitor.controller.competition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.model.competition.Competition;

/**
 * Created by Thomas on 09/05/2016.
 */
public class CompetitionAdapter extends ArrayAdapter<Competition> {
    public CompetitionAdapter(Context context, List<Competition> competitions) {
        super(context, 0, competitions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_competition, parent, false);
        }

        CompetitionViewHolder viewHolder = (CompetitionViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new CompetitionViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.idCompetition);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.dateStart = (TextView) convertView.findViewById(R.id.dateStart);
            viewHolder.dateEnd = (TextView) convertView.findViewById(R.id.dateEnd);
            viewHolder.capacity = (TextView) convertView.findViewById(R.id.capacity);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(viewHolder);
        }

        Competition competition = getItem(position);

        viewHolder.id.setText(competition.getId().toString());
        viewHolder.name.setText(competition.getName().toString());
        viewHolder.dateStart.setText(competition.getDateStart().toString());
        viewHolder.dateEnd.setText(competition.getDateEnd().toString());
        String capacity = competition.getCapacity() - competition.getJudokas().size() + "/" + competition.getCapacity();
        viewHolder.capacity.setText("Places: " + capacity);
        String address = competition.getCity().toString() + " - " + competition.getRegion().getName();
        viewHolder.address.setText(address);
        return convertView;
    }

    public class CompetitionViewHolder {
        public TextView id;
        public TextView name;
        public TextView dateStart;
        public TextView dateEnd;
        public TextView capacity;
        public TextView address;
    }
}

package fr.competitor.controller.competition.result;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.model.competition.Competition;

/**
 * Created by Thomas on 19/05/2016.
 */
public class ResultAdapter extends ArrayAdapter<Competition> {
    public ResultAdapter(Context context, List<Competition> competitions) {
        super(context, 0, competitions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_result, parent, false);
        }

        ResultViewHolder viewHolder = (ResultViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ResultViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.idCompetition);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.dateStart = (TextView) convertView.findViewById(R.id.dateStart);
            viewHolder.dateEnd = (TextView) convertView.findViewById(R.id.dateEnd);
            convertView.setTag(viewHolder);
        }

        Competition competition = getItem(position);

        Log.d("Position", Integer.toString(position));
        Log.d("Context", getContext().toString());

        viewHolder.id.setText(competition.getId().toString());
        viewHolder.name.setText(competition.getName().toString());
        viewHolder.dateStart.setText(competition.getDateStart().toString());
        viewHolder.dateEnd.setText(competition.getDateEnd().toString());
        return convertView;
    }

    public class ResultViewHolder {
        public TextView id;
        public TextView name;
        public TextView dateStart;
        public TextView dateEnd;
    }
}

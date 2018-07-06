package fr.competitor.controller.competition.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.controller.competition.result.ladder.RowLadder;

/**
 * Created by Thomas on 21/05/2016.
 */
public class LadderAdapter extends ArrayAdapter<RowLadder> {
    public LadderAdapter(Context context, List<RowLadder> rowLadders) {
        super(context, 0, rowLadders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_ladder, parent, false);
        }

        LadderViewHolder viewHolder = (LadderViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new LadderViewHolder();
            viewHolder.idJudoka = (TextView) convertView.findViewById(R.id.idJudoka);
            viewHolder.place = (TextView) convertView.findViewById(R.id.place);
            viewHolder.judoka = (TextView) convertView.findViewById(R.id.judoka);
            viewHolder.fights = (TextView) convertView.findViewById(R.id.fights);
            viewHolder.wins = (TextView) convertView.findViewById(R.id.wins);
            viewHolder.looses = (TextView) convertView.findViewById(R.id.looses);
            viewHolder.points = (TextView) convertView.findViewById(R.id.points);
            convertView.setTag(viewHolder);
        }

        RowLadder rowLadder = getItem(position);

        viewHolder.idJudoka.setText(rowLadder.getJudoka().getId().toString());
        viewHolder.place.setText(Integer.toString(position + 1));
        viewHolder.judoka.setText(rowLadder.getJudoka().getFirstName() + " " + rowLadder.getJudoka().getLastName());
        viewHolder.fights.setText(rowLadder.getFights().toString());
        viewHolder.wins.setText(rowLadder.getWins().toString());
        viewHolder.looses.setText(Integer.toString(rowLadder.getFights() - rowLadder.getWins()));
        viewHolder.points.setText(rowLadder.getPoints().toString());

        return convertView;
    }

    public class LadderViewHolder {
        public TextView idJudoka;
        public TextView place;
        public TextView judoka;
        public TextView fights;
        public TextView wins;
        public TextView looses;
        public TextView points;
    }
}

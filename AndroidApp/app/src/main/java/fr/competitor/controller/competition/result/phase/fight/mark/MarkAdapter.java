package fr.competitor.controller.competition.result.phase.fight.mark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.model.competition.phase.fight.Fight;
import fr.competitor.model.competition.phase.fight.Mark;
import fr.competitor.model.competition.phase.fight.Move;

/**
 * Created by Thomas on 19/05/2016.
 */
public class MarkAdapter extends ArrayAdapter<Mark> {
    private Fight fight;

    public MarkAdapter(Context context, List<Mark> marks, Fight fight) {
        super(context, 0, marks);
        this.fight = fight;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_move, parent, false);
        }

        MarkViewHolder viewHolder = (MarkViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new MarkViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.number = (TextView) convertView.findViewById(R.id.number);
            viewHolder.points = (TextView) convertView.findViewById(R.id.points);
            convertView.setTag(viewHolder);
        }

        Mark mark = getItem(position);
        Move move = mark.getMove();

        int number = getNumberMoveFight(move, fight);

        viewHolder.name.setText(move.getName() + "(" + move.getPoints() + ")");
        viewHolder.points.setText(Integer.toString(number));
        viewHolder.points.setText(Integer.toString(move.getPoints() * number));
        return convertView;
    }

    private int getNumberMoveFight(Move move, Fight fight) {
        int count = 0;
        for (Mark mark :
                fight.getMarks()) {
            if (mark.getMove().getId().equals(move.getId())) {
                count++;
            }
        }
        return count;
    }

    public class MarkViewHolder {
        public TextView name;
        public TextView number;
        public TextView points;
    }
}

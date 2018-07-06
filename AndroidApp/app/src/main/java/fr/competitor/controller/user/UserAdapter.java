package fr.competitor.controller.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.model.judoka.Judoka;
import fr.competitor.model.user.User;

/**
 * Created by Thomas on 09/05/2016.
 */
public class UserAdapter extends ArrayAdapter<Judoka> {
    public UserAdapter(Context context, List<Judoka> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_user, parent, false);
        }

        UserViewHolder viewHolder = (UserViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new UserViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.idUser);
            viewHolder.login = (TextView) convertView.findViewById(R.id.login);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }

        Judoka user = getItem(position);

        viewHolder.id.setText(user.getId().toString());
        viewHolder.login.setText(user.getLastName());
        viewHolder.name.setText(user.getFirstName());

        return convertView;
    }

    public class UserViewHolder {
        public TextView id;
        public TextView login;
        public TextView name;
    }
}

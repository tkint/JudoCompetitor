package fr.competitor.controller.article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.model.article.Article;

/**
 * Created by Thomas on 09/05/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_article, parent, false);
        }

        ArticleViewHolder viewHolder = (ArticleViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ArticleViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.idArticle);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.article = (TextView) convertView.findViewById(R.id.article);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.comments = (TextView) convertView.findViewById(R.id.comments);
            convertView.setTag(viewHolder);
        }

        Article article = getItem(position);

        viewHolder.id.setText(article.getId().toString());
        viewHolder.title.setText(article.getTitle());
        String texte = article.getArticle();
        viewHolder.article.setText(texte.substring(0, texte.length() % 30));
        viewHolder.date.setText(article.getDateCreate().toString());
        viewHolder.comments.setText(Integer.toString(article.getComments().size()));

        return convertView;
    }

    public class ArticleViewHolder {
        public TextView id;
        public TextView title;
        public TextView article;
        public TextView date;
        public TextView comments;
    }
}

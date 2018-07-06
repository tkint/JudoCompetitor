package fr.competitor.view.article;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import fr.competitor.R;
import fr.competitor.model.article.Article;
import fr.competitor.model.article.Category;
import fr.competitor.model.user.User;
import fr.competitor.controller.article.ArticleWSClient;

public class ViewArticle extends AppCompatActivity {
    TextView idArticle;
    TextView title;
    TextView author;
    TextView date;
    TextView article;
    TextView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);

        Intent intent = getIntent();
        int id = intent.getIntExtra("EXTRA_ID", 0);
        Article target = new ArticleWSClient().getArticle(id);

        init();
        initArticle(target);
    }

    private void init() {
        idArticle = (TextView) findViewById(R.id.idArticle);
        title = (TextView) findViewById(R.id.title);
        author = (TextView) findViewById(R.id.author);
        date = (TextView) findViewById(R.id.date);
        article = (TextView) findViewById(R.id.article);
        categories = (TextView) findViewById(R.id.categories);
        article.setMovementMethod(new ScrollingMovementMethod());
    }

    private void changeToArticles(View button) {
        Intent intent = new Intent(this, Articles.class);
        startActivity(intent);
    }

    private void initArticle(Article target) {
        idArticle.setText(target.getId().toString());
        title.setText(target.getTitle());
        User user = target.getUser();
        author.setText(user.getFirstName() + " " + user.getLastName());
        date.setText(target.getDateCreate().toString());
        article.setText(target.getArticle());
        String strCat = "";
        ArrayList<Category> cat = target.getCategories();
        for (Category category :
                cat) {
            strCat += category.getName() + "| ";
        }
        categories.setText(strCat.substring(0, strCat.length() - 2));
    }
}

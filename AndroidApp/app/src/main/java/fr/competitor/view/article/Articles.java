package fr.competitor.view.article;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.competitor.R;
import fr.competitor.controller.article.ArticleAdapter;
import fr.competitor.model.article.Article;
import fr.competitor.controller.article.ArticleWSClient;

public class Articles extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        init();
        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }

    private void init() {
        liste = (ListView) findViewById(R.id.liste);
    }

    private void refresh() {
        populateView();
        clickCallBack();
    }

    private void populateView() {
        List<Article> articles = new ArticleWSClient().getArticles();
        ArrayAdapter adapter = new ArticleAdapter(Articles.this, articles);
        liste.setAdapter(adapter);
    }

    private void clickCallBack() {
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View clicked, int position, long id) {
                TextView idArticle = (TextView) clicked.findViewById(R.id.idArticle);
                changeToArticle(Integer.parseInt(idArticle.getText().toString()));
            }
        });
    }

    private void changeToArticle(int id) {
        Intent intent = new Intent(this, ViewArticle.class);
        intent.putExtra("EXTRA_ID", id);
        startActivity(intent);
    }
}

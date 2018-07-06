package fr.competitor.controller.article;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.competitor.Config;
import fr.competitor.controller.WebService;
import fr.competitor.model.article.Article;

/**
 * Created by Thomas on 08/05/2016.
 */
public class ArticleWSClient {
    private WebService ws = new WebService();
    private Gson gson = new Gson();

    public Article getArticle(int id) {
        try {
            String result = ws.execute(Config.PATH_WS_ARTICLE + "/" + id).get();
            return gson.fromJson(result, Article.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> getArticles() {
        try {
            String result = ws.execute(Config.PATH_WS_ARTICLE).get();
            return gson.fromJson(result, new TypeToken<List<Article>>() {
            }.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

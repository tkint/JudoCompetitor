package fr.competitor.controller;

import android.os.AsyncTask;

import java.io.IOException;

import fr.competitor.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Thomas on 08/05/2016.
 */
public class WebService extends AsyncTask<String, Void, String> {
    OkHttpClient client = new OkHttpClient();

    protected String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(Config.PATH_WS + url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public String doInBackground(String... url) {
        String result = null;
        try {
            result = run(url[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

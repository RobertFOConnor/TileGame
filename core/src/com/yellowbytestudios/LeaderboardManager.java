package com.yellowbytestudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class LeaderboardManager {

    public static final String LEADERBOARD_URL = "https://leaderboardapi.firebaseapp.com/scores";

    public static void postScore() {
        HttpRequestBuilder builder = new HttpRequestBuilder();
        Net.HttpRequest request = builder.newRequest().method(Net.HttpMethods.POST).url(LEADERBOARD_URL).build();
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setContent("name=rayray&score=5867");


        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("WebRequest", "HTTP Response code: " + httpResponse.getStatus().getStatusCode());
                String result = httpResponse.getResultAsString();
                Gdx.app.log("WebRequest", "HTTP Response code: " + result);
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("WebRequest", "HTTP request failed");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("WebRequest", "HTTP request cancelled");
            }
        });
    }

    public static void getScores() {
        HttpRequestBuilder builder = new HttpRequestBuilder();
        Net.HttpRequest request = builder.newRequest().method(Net.HttpMethods.GET).url(LEADERBOARD_URL).build();
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");


        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("WebRequest", "HTTP Response code: " + httpResponse.getStatus().getStatusCode());
                String result = httpResponse.getResultAsString();
                Gdx.app.log("WebRequest", "HTTP Response code: " + result);

                JsonValue scores = new JsonReader().parse(result).get(0);

                for (int i = 0; i < 2; i++) {
                    JsonValue scoreItem = scores.get(i);
                    System.out.print(scoreItem.get("name").asString() + " - " + scoreItem.get("score").asInt());
                    System.out.println();
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("WebRequest", "HTTP request failed");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("WebRequest", "HTTP request cancelled");
            }
        });
    }
}

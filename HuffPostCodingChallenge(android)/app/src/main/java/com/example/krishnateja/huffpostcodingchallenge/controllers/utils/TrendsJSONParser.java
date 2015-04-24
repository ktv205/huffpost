package com.example.krishnateja.huffpostcodingchallenge.controllers.utils;

import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class TrendsJSONParser {
    public static final String TREND = "trend";
    public static final String LINKS = "links";
    public static final String TWEETS = "tweets";
    public static final int TWEETS_CODE = 1;
    public static final int LINKS_CODE = 2;

    public static ArrayList<LinksTweetsTrend> getArrayList(String data, int code) {
        ArrayList<LinksTweetsTrend> linksTweetsTrendsArrayList = new ArrayList<>();
        try {
            JSONArray mainArray = new JSONArray(data);
            for (int i = 0; i < mainArray.length(); i++) {
                ArrayList<String> tweetsLinks = new ArrayList<>();
                LinksTweetsTrend linksTweetsTrend = new LinksTweetsTrend();
                String trend;
                JSONObject obj = mainArray.getJSONObject(i);
                trend = obj.getString(TREND);
                linksTweetsTrend.setTrend(trend);
                JSONArray tweetsLinksArray = null;
                if (code == TWEETS_CODE) {
                    tweetsLinksArray = obj.getJSONArray(TWEETS);
                } else if (code == LINKS_CODE) {
                    tweetsLinksArray = obj.getJSONArray(LINKS);
                }
                for (int j = 0; j < tweetsLinksArray.length(); j++) {
                    tweetsLinks.add(tweetsLinksArray.getString(j));
                }
                linksTweetsTrend.setLinksOrTweets(tweetsLinks);
                linksTweetsTrendsArrayList.add(linksTweetsTrend);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return linksTweetsTrendsArrayList;
    }
}

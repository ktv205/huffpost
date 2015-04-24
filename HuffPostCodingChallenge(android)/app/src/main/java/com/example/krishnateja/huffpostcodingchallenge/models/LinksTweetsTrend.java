package com.example.krishnateja.huffpostcodingchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class LinksTweetsTrend implements Parcelable {
    private String trend;
    private ArrayList<String> linksOrTweets = new ArrayList<>();

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public ArrayList<String> getLinksOrTweets() {
        return linksOrTweets;
    }

    public void setLinksOrTweets(ArrayList<String> linksOrTweets) {
        this.linksOrTweets = linksOrTweets;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public LinksTweetsTrend() {

    }

    public LinksTweetsTrend(Parcel source) {
        trend = source.readString();
        linksOrTweets = (ArrayList<String>) source.readSerializable();
    }

    public static final Parcelable.Creator<LinksTweetsTrend> CREATOR = new Parcelable.Creator<LinksTweetsTrend>() {

        @Override
        public LinksTweetsTrend createFromParcel(Parcel source) {
            return new LinksTweetsTrend(source);
        }

        @Override
        public LinksTweetsTrend[] newArray(int size) {
            return new LinksTweetsTrend[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trend);
        dest.writeSerializable(linksOrTweets);
    }
}

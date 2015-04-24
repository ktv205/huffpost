package com.example.krishnateja.huffpostcodingchallenge.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.krishnateja.huffpostcodingchallenge.R;
import com.example.krishnateja.huffpostcodingchallenge.controllers.utils.CommonFunctions;
import com.example.krishnateja.huffpostcodingchallenge.controllers.utils.TrendsAsyncTask;
import com.example.krishnateja.huffpostcodingchallenge.models.AppPreferences;
import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;
import com.example.krishnateja.huffpostcodingchallenge.models.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class HelperActivity extends ActionBarActivity implements TrendsAsyncTask.GetTweetsAndLinks {
    private static final String TAG = HelperActivity.class.getSimpleName();
    private int mFlag = 0;
    private static final String TWEETS = "tweet";
    private static final String LINKS = "link";
    private static final int TWEETS_CODE = 1;
    private static final int LINKS_CODE = 2;
    private ArrayList<LinksTweetsTrend> mTweetsArrayList;
    private ArrayList<LinksTweetsTrend> mLinksArrayList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
        if (mContext == null) {
            mContext = HelperActivity.this;
        }
        HashMap<String, String> tweetGetVariables = new HashMap<>();
        tweetGetVariables.put(AppPreferences.ServerConstants.GETVARIABLE, TWEETS);
        String[] path = {AppPreferences.ServerConstants.PATH, AppPreferences.ServerConstants.FILE};
        RequestParams tweetParams = CommonFunctions.buildParams(path, tweetGetVariables, mContext);
        new TrendsAsyncTask(mContext, TWEETS_CODE).execute(tweetParams);
        RequestParams linkParams = CommonFunctions.buildParams(path, null, mContext);
        new TrendsAsyncTask(mContext, LINKS_CODE).execute(linkParams);

    }

    @Override
    public void getData(int code, ArrayList<LinksTweetsTrend> arrayList) {
        mFlag++;
        if (code == TWEETS_CODE) {
            mTweetsArrayList = arrayList;

        } else {
            Log.d(TAG,"mLinksArrayList");
            mLinksArrayList = arrayList;
            Log.d(TAG,mLinksArrayList.size()+"sixe in else");
        }
        if (mFlag == 2) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(AppPreferences.IntentExtras.EXTRA_LINKS, mLinksArrayList);
            intent.putExtra(AppPreferences.IntentExtras.EXTRA_TWEETS, mTweetsArrayList);
            startActivity(intent);
            finish();
        }


    }
}

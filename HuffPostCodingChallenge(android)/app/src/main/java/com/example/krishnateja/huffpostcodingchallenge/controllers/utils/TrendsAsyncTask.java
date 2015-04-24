package com.example.krishnateja.huffpostcodingchallenge.controllers.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;
import com.example.krishnateja.huffpostcodingchallenge.models.RequestParams;

import java.util.ArrayList;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class TrendsAsyncTask extends AsyncTask<RequestParams, Void, String> {

    private Context mContext;
    private int mCode;
    private GetTweetsAndLinks mGetTweetsAndLinks;

    public interface GetTweetsAndLinks {
        public void getData(int code, ArrayList<LinksTweetsTrend> arrayList);
    }

    public TrendsAsyncTask(Context context, int code) {
        mContext = context;
        mCode = code;
        try {
            mGetTweetsAndLinks = (GetTweetsAndLinks) mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString() + " must implement GetTweetsAndLinks");
        }

    }

    @Override
    protected String doInBackground(RequestParams... params) {
        return HttpManager.sendUserData(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        ArrayList<LinksTweetsTrend> arrayList = null;
        if(s!=null) {
            arrayList = TrendsJSONParser.getArrayList(s,mCode);
        }
        mGetTweetsAndLinks.getData(mCode,arrayList);
    }
}

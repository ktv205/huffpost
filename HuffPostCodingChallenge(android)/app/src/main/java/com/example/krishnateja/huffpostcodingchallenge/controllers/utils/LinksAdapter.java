package com.example.krishnateja.huffpostcodingchallenge.controllers.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.krishnateja.huffpostcodingchallenge.R;
import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;


/**
 * Created by krishnateja on 4/24/2015.
 */
public class LinksAdapter extends BaseAdapter {

    private LinksTweetsTrend mLinksTweetsTrend;
    private Context mContext;

    public LinksAdapter(LinksTweetsTrend linksTweetsTrend,Context context){
          mLinksTweetsTrend=linksTweetsTrend;
        mContext=context;
    }

    @Override
    public int getCount() {
        return mLinksTweetsTrend.getLinksOrTweets().size();
    }

    @Override
    public Object getItem(int position) {
        return mLinksTweetsTrend.getLinksOrTweets().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class LinksHolder{
        TextView textView;
        public LinksHolder(View view){
           textView=(TextView)view.findViewById(android.R.id.text1);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        LinksHolder holder;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new LinksHolder(view);
            view.setTag(holder);
        } else {
            holder = (LinksHolder) view.getTag();
        }
        holder.textView.setText(mLinksTweetsTrend.getLinksOrTweets().get(position));
        holder.textView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        return view;
    }
}

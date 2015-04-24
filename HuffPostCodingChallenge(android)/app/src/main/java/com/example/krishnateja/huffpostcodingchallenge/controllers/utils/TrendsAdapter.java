package com.example.krishnateja.huffpostcodingchallenge.controllers.utils;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.krishnateja.huffpostcodingchallenge.R;
import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;

import java.util.ArrayList;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class TrendsAdapter implements ExpandableListAdapter {

    private Context mContext;
    private ArrayList<LinksTweetsTrend> mArrayList;

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public TrendsAdapter(ArrayList<LinksTweetsTrend> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mArrayList.get(groupPosition).getLinksOrTweets().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mArrayList.get(groupPosition).getLinksOrTweets().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    public class ParentHolder{
        TextView textView;
        public ParentHolder(View view){
            textView=(TextView)view.findViewById(android.R.id.text1);
        }

    }

    public class ChildHolder{
        TextView textView;
        public ChildHolder(View view){
            textView=(TextView)view.findViewById(android.R.id.text1);
        }
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=convertView;
        ParentHolder holder;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ParentHolder(view);
            view.setTag(holder);
        } else {
            holder = (ParentHolder) view.getTag();
        }
        holder.textView.setText(mArrayList.get(groupPosition).getTrend());
        holder.textView.setTextColor(mContext.getResources().getColor(R.color.primaryText));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;
        ChildHolder holder;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ChildHolder(view);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        holder.textView.setText(mArrayList.get(groupPosition).getLinksOrTweets().get(childPosition));
        holder.textView.setTextColor(mContext.getResources().getColor(R.color.secondaryText));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}

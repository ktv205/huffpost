package com.example.krishnateja.huffpostcodingchallenge.controllers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.krishnateja.huffpostcodingchallenge.R;
import com.example.krishnateja.huffpostcodingchallenge.controllers.utils.TrendsAdapter;
import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;

import java.util.ArrayList;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class TrendsFragment extends Fragment {
    ExpandableListView mExpandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_exapandable_list,container,false);
        mExpandableListView=(ExpandableListView)view.findViewById(R.id.expandable);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            ArrayList<LinksTweetsTrend> arrayList=bundle.getParcelableArrayList(Intent.EXTRA_TEXT);
            if(arrayList!=null) {
                TrendsAdapter adapter = new TrendsAdapter(arrayList, getActivity());
                mExpandableListView.setAdapter(adapter);
            }
        }
    }
}

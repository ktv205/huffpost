package com.example.krishnateja.huffpostcodingchallenge.controllers.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krishnateja.huffpostcodingchallenge.R;
import com.example.krishnateja.huffpostcodingchallenge.controllers.activities.WebViewActivity;
import com.example.krishnateja.huffpostcodingchallenge.controllers.utils.LinksAdapter;
import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class LinksFragment extends Fragment {

    private static final String TAG =LinksFragment.class.getSimpleName() ;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        mListView=(ListView)view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
           LinksTweetsTrend linksTweetsTrend=bundle.getParcelable(Intent.EXTRA_TEXT);
            if(linksTweetsTrend!=null) {
                LinksAdapter adapter=new LinksAdapter(linksTweetsTrend,getActivity());
                mListView.setAdapter(adapter);
            }
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d(TAG,"onitemClick");
                    String text=((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                    Log.d(TAG,text);
                    Uri uri = Uri.parse(text);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getActivity().startActivity(intent);
                }
            });
        }

    }
}

package com.example.krishnateja.huffpostcodingchallenge.controllers.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.krishnateja.huffpostcodingchallenge.R;
import com.example.krishnateja.huffpostcodingchallenge.controllers.fragments.LinksFragment;
import com.example.krishnateja.huffpostcodingchallenge.controllers.fragments.TrendsFragment;
import com.example.krishnateja.huffpostcodingchallenge.models.AppPreferences;
import com.example.krishnateja.huffpostcodingchallenge.models.LinksTweetsTrend;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<LinksTweetsTrend> mTweetsList;
    private ArrayList<LinksTweetsTrend> mLinksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().hasExtra(AppPreferences.IntentExtras.EXTRA_LINKS) && getIntent().hasExtra(AppPreferences.IntentExtras.EXTRA_TWEETS)) {
            mTweetsList = getIntent().getParcelableArrayListExtra(AppPreferences.IntentExtras.EXTRA_TWEETS);
            mLinksList = getIntent().getParcelableArrayListExtra(AppPreferences.IntentExtras.EXTRA_LINKS);
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                TrendsFragment fragment = new TrendsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Intent.EXTRA_TEXT, mTweetsList);
                fragment.setArguments(bundle);
                return fragment;
            } else {
                LinksFragment fragment = new LinksFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Intent.EXTRA_TEXT, mLinksList.get(position - 1));
                fragment.setArguments(bundle);
                return fragment;
            }
        }

        @Override
        public int getCount() {
            return mLinksList.size() + 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Trends";
            } else {
                return mLinksList.get(position - 1).getTrend();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

	/*
     * (non-Javadoc)
	 *
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, HelperActivity.class);
        startActivity(intent);
        finish();
        return true;
    }
}

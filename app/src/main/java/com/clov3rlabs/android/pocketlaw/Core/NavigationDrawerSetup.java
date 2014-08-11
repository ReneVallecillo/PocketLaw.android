package com.clov3rlabs.android.pocketlaw.Core;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.clov3rlabs.android.pocketlaw.Listeners.DrawerItemClickListener;
import com.clov3rlabs.android.pocketlaw.R;

/**
 * Created by reneval on 7/31/14.
 * This class will allow many activities to set the navigation drawer.
 */
public class NavigationDrawerSetup {

    private String[] mMenuTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBar actionBar;
    private Activity mCurrentActivity;
    private DrawerItemClickListener mDrawerListener;
    private CharSequence mTitle, mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;

    private static String TAG = "NavigationDrawerSetup";

    public NavigationDrawerSetup(String[] mMenuTitles,DrawerLayout mDrawerLayout, ListView mDrawerList,ActionBar actionBar, Activity mCurrentActivity){

        this.mMenuTitles = mMenuTitles;
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerList = mDrawerList;
        this.actionBar = actionBar;
        this.mCurrentActivity = mCurrentActivity;

    }


    public void configureDrawer(){
        Log.d(TAG, "llamo a configureDrawer");
        //Set Adapter
        mDrawerList.setAdapter(new ArrayAdapter<String>(mCurrentActivity,
                R.layout.drawer_list_item, mMenuTitles));

        //Set Listener
        mDrawerListener = new DrawerItemClickListener(mCurrentActivity,mDrawerLayout,mDrawerList,mMenuTitles);

        mDrawerList.setOnItemClickListener( mDrawerListener );
        mTitle = mDrawerListener.getTitle();

        mTitle = mDrawerTitle = mCurrentActivity.getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(mCurrentActivity, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                actionBar.setTitle(mTitle);
                mCurrentActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle(mDrawerTitle);
                mCurrentActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }
}

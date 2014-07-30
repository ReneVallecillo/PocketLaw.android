package com.clov3rlabs.android.pocketlaw.Listeners;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.clov3rlabs.android.pocketlaw.Fragments.DashboardFragment;
import com.clov3rlabs.android.pocketlaw.R;

/**
 * Created by reneval on 7/29/14.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {
    private Activity mActivity;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String [] mMenuTitles;
    private int mPosition;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectItem(i);
    }

    /** Swaps Fragments in the Main Content View */

    private void selectItem(int position){

        //TODO Create a new fragment and specify the planet to show based on position
        Fragment fragment = DashboardFragment.newInstance();

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = mActivity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer

        mDrawerList.setItemChecked(position,true);
        mDrawerLayout.closeDrawer(mDrawerList);
        mActivity.setTitle(mMenuTitles[position]);
    }


    public DrawerItemClickListener(Activity activity, DrawerLayout drawerLayout, ListView drawerList, String[] menuTitles){
        mActivity = activity;
        mDrawerLayout = drawerLayout;
        mDrawerList = drawerList;
        mMenuTitles = menuTitles;
    }

    public String getTitle(){
        return mMenuTitles[mPosition];
    }

}

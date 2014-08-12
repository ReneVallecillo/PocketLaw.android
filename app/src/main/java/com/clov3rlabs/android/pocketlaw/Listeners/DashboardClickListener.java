package com.clov3rlabs.android.pocketlaw.Listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.clov3rlabs.android.pocketlaw.Activities.LawActivity;

/**
 * Created by reneval on 8/11/14.
 */
public class DashboardClickListener implements View.OnClickListener {

    private Activity mContext;
    private int dashItem;
    private Intent intent;

    public DashboardClickListener(Activity context, int item){
        mContext = context;
        dashItem = item;
    }

    @Override
    public void onClick(View view) {

        switch (dashItem){
            case 0:
                intent = new Intent(mContext, LawActivity.class);
                break;
            default:
                intent = new Intent(mContext, LawActivity.class);
                break;
        }

        mContext.startActivity(intent);
    }
}

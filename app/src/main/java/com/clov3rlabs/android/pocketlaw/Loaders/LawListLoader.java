package com.clov3rlabs.android.pocketlaw.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.ListView;

import com.clov3rlabs.android.pocketlaw.Api.ApiClient;
import com.clov3rlabs.android.pocketlaw.Entities.Law;

import java.util.List;

/**
 * Created by reneval on 8/11/14.
 */
public class LawListLoader extends AsyncTaskLoader<List<Law>> {

    private List<Law> mData;

    public LawListLoader (Context context){
        super(context);
    }

    @Override
    public List<Law> loadInBackground() {

        mData = ApiClient.getPocketLawApiClient().getLaws();

        if (mData !=null)
            if(!mData.isEmpty())
                return mData;

        return null;

    }

    @Override
    protected void onStopLoading() {
        // The Loader is in a stopped state, so we should attempt to cancel the
        // current load (if there is one).
        cancelLoad();

        // Note that we leave the observer as is. Loaders in a stopped state
        // should still monitor the data source for changes so that the Loader
        // will know to force a new load if it is ever started again.
    }

    @Override
    protected void onReset() {
        // Ensure the loader has been stopped.
        onStopLoading();

        // At this point we can release the resources associated with 'mData'.
        if (mData != null) {
            releaseResources(mData);
            mData = null;
        }
    }

    @Override
    public void deliverResult(List<Law> data) {
        if (isReset()) {
            // The Loader has been reset; ignore the result and invalidate the data.
            releaseResources(data);
            return;
        }

        // Hold a reference to the old data so it doesn't get garbage collected.
        // We must protect it until the new data has been delivered.
        List<Law> oldData = mData;
        mData = data;

        if (isStarted()) {
            // If the Loader is in a started state, deliver the results to the
            // client. The superclass method does this for us.
            super.deliverResult(data);
        }

        // Invalidate the old data as we don't need it any more.
        if (oldData != null && oldData != data) {
            releaseResources(oldData);
        }
    }

    private void releaseResources(List<Law> data) {
        // For a simple List, there is nothing to do. For something like a Cursor, we
        // would close it in this method. All resources associated with the Loader
        // should be released here.
        data.clear();
    }


    @Override
    public void onCanceled(List<Law> data) {
        // Attempt to cancel the current asynchronous load.
        super.onCanceled(data);

        // The load has been canceled, so we should release the resources
        // associated with 'data'.
        releaseResources(data);
    }

}

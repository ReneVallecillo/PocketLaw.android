package com.clov3rlabs.android.pocketlaw.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.clov3rlabs.android.pocketlaw.Api.ApiClient;
import com.clov3rlabs.android.pocketlaw.Entities.Article;
import com.clov3rlabs.android.pocketlaw.Entities.Law;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by reneval on 8/11/14.
 */
public class ArticleListLoader extends AsyncTaskLoader<List<Article>> {

    private List<Article> mData;
    private static String TAG = "LawListLoader";
    private static boolean DEBUG = true;
    private int mLawID;

    public ArticleListLoader(Context context, int lawID){
        super(context);
        mLawID = lawID;
    }

    @Override
    public List<Article> loadInBackground() {

        if(DEBUG) Log.d(TAG,"Now loading all Articles");

        mData = new ArrayList<Article>();
        try {
            mData = ApiClient.getPocketLawApiClient().getAllArticles(mLawID);
        }catch (RetrofitError error){

        }

        if (mData !=null)
            if(!mData.isEmpty())
                return mData;

        return null;

    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // Deliver any previously loaded data immediately.
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            // When the observer detects a change, it should call onContentChanged()
            // on the Loader, which will cause the next call to takeContentChanged()
            // to return true. If this is ever the case (or if the current data is
            // null), we force a new load.
            forceLoad();
        }
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
    public void deliverResult(List<Article> data) {
        if (isReset()) {
            // The Loader has been reset; ignore the result and invalidate the data.
            releaseResources(data);
            return;
        }

        // Hold a reference to the old data so it doesn't get garbage collected.
        // We must protect it until the new data has been delivered.
        List<Article> oldData = mData;
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

    private void releaseResources(List<Article> data) {
        // For a simple List, there is nothing to do. For something like a Cursor, we
        // would close it in this method. All resources associated with the Loader
        // should be released here.
        data.clear();
    }


    @Override
    public void onCanceled(List<Article> data) {
        // Attempt to cancel the current asynchronous load.
        super.onCanceled(data);

        // The load has been canceled, so we should release the resources
        // associated with 'data'.
        releaseResources(data);
    }

}

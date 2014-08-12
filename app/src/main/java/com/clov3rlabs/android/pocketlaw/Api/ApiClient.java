package com.clov3rlabs.android.pocketlaw.Api;

import android.util.Log;

import com.clov3rlabs.android.pocketlaw.Entities.Law;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.GET;

/**
 * Created by reneval on 8/11/14.
 */
public class ApiClient {
    private static PocketLawApiInterface mPocketLawInterface;

    private static String TAG = "ApiClient";

    public static PocketLawApiInterface getPocketLawApiClient(){
        if (mPocketLawInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://google.com/api")
                    .setErrorHandler(new SyncErrorHandler())
                    .build();

            mPocketLawInterface = restAdapter.create(PocketLawApiInterface.class);
        }
        return mPocketLawInterface;
    }


    public interface PocketLawApiInterface {
        @GET("/v0.1/laws.json")
        List<Law> getLaws();

    }


    /**
     * This class will allow to custom handle the errors coming from
     * an synchronous request
     */
    static class SyncErrorHandler implements ErrorHandler{

        @Override
        public Throwable handleError(RetrofitError cause) {
            Log.d(TAG,"error se dio");
            if (cause.isNetworkError()) {
                if (cause.getCause() instanceof SocketTimeoutException) {
                    Log.e(TAG, "SocketTimeoutException, url=" + cause.getUrl());
                } else {
                    Log.e(TAG, "NoConnectionException, url=" + cause.getUrl());
                }
            } else {
                Log.e(TAG, "Error status:" + cause.getResponse().getStatus());
            }

            return cause;
        }
    }
}

package com.clov3rlabs.android.pocketlaw.Api;

import com.clov3rlabs.android.pocketlaw.Entities.Law;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by reneval on 8/11/14.
 */
public class ApiClient {
    private static PocketLawApiInterface mPocketLawInterface;

    public static PocketLawApiInterface getPocketLawApiClient(){
        if (mPocketLawInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://0.0.0.0:3000/api")
                    .build();

            mPocketLawInterface = restAdapter.create(PocketLawApiInterface.class);
        }
        return mPocketLawInterface;
    }


    public interface PocketLawApiInterface {
        @GET("/v0.1/laws.json")
        List<Law> getLaws();

    }
}

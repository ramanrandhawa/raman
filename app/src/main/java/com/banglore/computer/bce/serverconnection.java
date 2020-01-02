package com.banglore.computer.bce;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by raman on 7/4/17.
 */

public class serverconnection {


    public static retrofitapi doconnect()
    {
        RestAdapter.Builder rest=new RestAdapter.Builder();
        rest.setEndpoint("http://www.banglorecomputereducation.com/retrofit/app_banglorecomp2017");
        rest.setLogLevel(RestAdapter.LogLevel.FULL);
        rest.setClient(new OkClient(new OkHttpClient()));
        RestAdapter adapter=rest.build();
       retrofitapi retro= adapter.create(retrofitapi.class);
        return  retro;
    }



}

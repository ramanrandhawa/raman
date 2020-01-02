package com.banglore.computer.bce;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by raman on 7/4/17.
 */

public interface retrofitapi {

    @GET("/batchesFetchAll.php")
    void fetch( Callback<responsemodel> response);

    @GET("/placementFetchAll.php")
    void fetchplacement(Callback<responseplacement> response);


     @GET("/interview_fetchall.php")
    void fetchquestion(Callback<responsemodelquestion> question);

    @GET("/galleryPicsList.php")
    void ferchpics(Callback<responsepics> resppics);

     @FormUrlEncoded
    @POST("/interview_fetchall.php")
    void fetchallquestion(@Field("lang") String lang, Callback<responsemodelquestion> question);


    @FormUrlEncoded
    @POST("/querySMS.php")
    void sendQuery(@Field("tname") String tname, @Field("mobile") String mobile, @Field("query") String query, Callback<responseusersubmit> resp);
}

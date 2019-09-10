package com.example.disystestapplication.network;

import com.example.disystestapplication.model.PlayloadModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiCallback {

    String BASE_URL = "https://api.qa.mrhe.gov.ae/";

    String SIGNUP_URL = "https://api.qa.mrhe.gov.ae/";


    @Headers({"Accept: application/json", "consumer-key: mobile_dev", "consumer-secret: 20891a1b4504ddc33d42501f9c8d2215fbe85008"})
    @GET("mrhecloud/v1.4/api/public/v1/news")
    Call<PlayloadModel> getNews(@Query("local") String local);

    @Headers({"Accept: application/json", "consumer-key: mobile_dev", "consumer-secret: 20891a1b4504ddc33d42501f9c8d2215fbe85008"})
    @FormUrlEncoded
    @POST("MRHECloud/v1.4/docs/iskan/#api-CertificatesRequestToWhomItMayConcern")
    Call<PlayloadModel> getSignup(@Field("eid") int eid, @Field("name") String name, @Field("idbarahno") int idbarahno, @Field("emailaddress") String emailaddress, @Field("unifiednumber") int unifiednumber, @Field("mobileno") String mobilen0);

}

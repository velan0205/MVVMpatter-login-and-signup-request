package com.example.disystestapplication.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.disystestapplication.model.NewsModel;
import com.example.disystestapplication.network.ApiCallback;
import com.example.disystestapplication.model.PlayloadModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<NewsModel>> newsList;
    private MutableLiveData<PlayloadModel> signup;

    public LiveData<List<NewsModel>> getNews() {
        if (newsList == null) {
            newsList = new MutableLiveData<>();
            loadNews();
        }
        return newsList;
    }

    public LiveData<PlayloadModel> getSignUPResponse(int eid, String name, int iddharho, String emailadderss, int unified, String mobileno) {
        if (signup == null) {
            signup = new MutableLiveData<>();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiCallback.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            ApiCallback api = retrofit.create(ApiCallback.class);

            Call<PlayloadModel> call = api.getSignup(eid, name, iddharho, emailadderss, unified, mobileno);

            call.enqueue(new Callback<PlayloadModel>() {
                @Override
                public void onResponse(Call<PlayloadModel> call, Response<PlayloadModel> response) {
                    assert response.body() != null;
                    if (response.isSuccessful()) {
                        signup.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<PlayloadModel> call, Throwable t) {
                    Log.i("NewsViewModel", t.toString());
                }
            });
        }
        return signup;
    }

    private void loadNews() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiCallback.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCallback api = retrofit.create(ApiCallback.class);
        Call<PlayloadModel> call = api.getNews("en");

        call.enqueue(new Callback<PlayloadModel>() {
            @Override
            public void onResponse(Call<PlayloadModel> call, Response<PlayloadModel> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    newsList.setValue(response.body().getNewsModels());
                }
            }

            @Override
            public void onFailure(Call<PlayloadModel> call, Throwable t) {
                Log.i("NewsViewModel", t.toString());
            }
        });
    }


}




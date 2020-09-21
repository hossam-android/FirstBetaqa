package com.hashtag.firstbetaqa;





import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ebda3-mint on 8/15/17.
 */

public class SingletonRetrofit {

    private SingletonRetrofit() {
    }



    public static RetrofitApi getRetrofitInstant(){

        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.firstcard.sa/")
                .build();
        RetrofitApi retrofitApi =retrofit.create(RetrofitApi.class);

        return retrofitApi;
    }
}

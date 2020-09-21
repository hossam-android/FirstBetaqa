package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.OfferDetailsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersDetails extends AppCompatActivity {


    ImageView detailsOfferImage;
    TextView detailsTitle,toolTitle,detailsOffer;
    WebView detailsWebView;
    String lang="",newsid="";
    Locale current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_offers_details);


        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        detailsOfferImage= (ImageView) findViewById(R.id.detailsOfferImage);
        detailsTitle= (TextView) findViewById(R.id.detailsOfferTitle);
        toolTitle= (TextView) findViewById(R.id.title);
        detailsWebView= (WebView) findViewById(R.id.detailsContentWebView);
        detailsOffer= (TextView) findViewById(R.id.detailsOffer);

        if(getIntent().getExtras() != null){
            newsid=getIntent().getExtras().getString("nid","");
            toolTitle.setText(getIntent().getExtras().getString("title",""));
        }


        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<OfferDetailsDataModel> conn=retrofitApi.getOfferDetails(newsid,lang);
        conn.enqueue(new Callback<OfferDetailsDataModel>() {
            @Override
            public void onResponse(Call<OfferDetailsDataModel> call, Response<OfferDetailsDataModel> response) {

                Log.v("dddddd",response.body().getContent()+">>>");
                Picasso.with(OffersDetails.this).load(response.body().getPhoto()).into(detailsOfferImage);
                detailsTitle.setText(response.body().getTitle());
               // detailsOffer.setText(response.body().getDescription());
                detailsWebView.loadData(response.body().getContent(),"text/html ; charset=utf-8","UTF-8");
            }

            @Override
            public void onFailure(Call<OfferDetailsDataModel> call, Throwable t) {

                Log.v("dddddd",t.getMessage()+">>>"+newsid);
            }
        });

    }
}

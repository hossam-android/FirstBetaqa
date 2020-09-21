package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.PageDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageActivity extends AppCompatActivity {

    WebView webView;
    TextView titleTextView;
    String title,url,lang;
    Locale current;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        titleTextView= (TextView) findViewById(R.id.title);
        webView= (WebView) findViewById(R.id.webView);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        if(getIntent().getExtras() != null){
            title=getIntent().getExtras().getString("title","");
            url=getIntent().getExtras().getString("url","");
            titleTextView.setText(title);
        }

        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<PageDataModel>> conn=retrofitApi.getPageContent(url,lang);
        conn.enqueue(new Callback<List<PageDataModel>>() {
            @Override
            public void onResponse(Call<List<PageDataModel>> call, Response<List<PageDataModel>> response) {
                Log.v("sssss",response.body().get(0).getContent());
                webView.loadData(response.body().get(0).getContent(),"text/html ; charset=utf-8","UTF-8");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<PageDataModel>> call, Throwable t) {
                Log.v("sssss",t.getMessage()+">>");
                progressBar.setVisibility(View.GONE);

            }
        });



    }
}

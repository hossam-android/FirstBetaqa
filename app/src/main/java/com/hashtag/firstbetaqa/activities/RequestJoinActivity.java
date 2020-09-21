package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.RequestCardDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.fragments.RequestJoinDialogFragment;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestJoinActivity extends AppCompatActivity implements View.OnClickListener {



    TextView title;
    WebView contentWebView;
    Button requestCard;
    String lang;
    Locale current;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_join);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        progressBar= (ProgressBar) findViewById(R.id.progress);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        title= (TextView) findViewById(R.id.title);
        contentWebView= (WebView) findViewById(R.id.requestContent);
        requestCard= (Button) findViewById(R.id.requestCardBtn);
        requestCard.setOnClickListener(this);

        if(getIntent().getExtras() != null){
            title.setText(getIntent().getExtras().getString("title",""));
        }

        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<RequestCardDataModel>> conn=retrofitApi.requestCardInfo
                ("https://www.firstcard.sa/api.php?mod=pages&pageid=26",lang);
        conn.enqueue(new Callback<List<RequestCardDataModel>>() {
            @Override
            public void onResponse(Call<List<RequestCardDataModel>> call,
                                   Response<List<RequestCardDataModel>> response) {

                Log.v("aaaaaa",response.body().get(0).getContent()+">>>");
                progressBar.setVisibility(View.GONE);
                contentWebView.loadData(response.body().get(0).getContent(),"text/html ;" +
                        " charset=utf-8","UTF-8");
            }

            @Override
            public void onFailure(Call<List<RequestCardDataModel>> call, Throwable t) {
                Log.v("aaaaaa",t.getMessage()+">>>");
                progressBar.setVisibility(View.GONE);


            }
        });

    }

    @Override
    public void onClick(View view) {

        RequestJoinDialogFragment joinDialogFragment=new RequestJoinDialogFragment();
        joinDialogFragment.show(getSupportFragmentManager(),"join");

    }


}

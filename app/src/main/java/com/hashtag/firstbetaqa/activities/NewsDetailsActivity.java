package com.hashtag.firstbetaqa.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.NewsDetailsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;
import com.robertsimoes.shareable.Shareable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends AppCompatActivity {


    ImageView newsImage,leftImage,rightImage,shareImage,commentImage;
    TextView numComments,numViews,title,newstitle,newsDate;
    WebView webView;
    String nid,lang,next,prev;
    Locale current;
    ProgressBar progressBar;
    ImageView shadow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_details);


        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        if(getIntent().getExtras() != null){
            nid=getIntent().getExtras().getString("nid","");
        }

        title= (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.newsTitle));


        shadow= (ImageView) findViewById(R.id.shadow);
        newstitle= (TextView) findViewById(R.id.newsTitle);
        newsDate= (TextView) findViewById(R.id.newsDate);
        numViews= (TextView) findViewById(R.id.newsViews);
        numComments= (TextView) findViewById(R.id.newsComments);
        webView= (WebView) findViewById(R.id.newsDetails);

        newsImage= (ImageView) findViewById(R.id.newsImage);
        leftImage= (ImageView) findViewById(R.id.left);
        rightImage= (ImageView) findViewById(R.id.right);
        shareImage= (ImageView) findViewById(R.id.share);
        commentImage= (ImageView) findViewById(R.id.comment);



        getNewsDetails(nid);










    }

    private void getNewsDetails(String nid)
    {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<NewsDetailsDataModel> conn=retrofitApi.getNewsDetails(nid,lang);
        conn.enqueue(new Callback<NewsDetailsDataModel>() {
            @Override
            public void onResponse(Call<NewsDetailsDataModel> call, final Response<NewsDetailsDataModel> response) {

                Target target=new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
                    {

                        newsImage.setImageBitmap(bitmap);
                        shadow.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                };
                Picasso.with(NewsDetailsActivity.this).load(response.body().getPhoto()).into(target);

                newstitle.setText(response.body().getTitle()+"");
                numComments.setText(response.body().getNumberOfComments()+"");
                numViews.setText(response.body().getViews()+"");
                newsDate.setText(response.body().getAr_date().getDay()+" "+response.body().getAr_date().getNamemonth()+" "+response.body().getAr_date().getYear());
                webView.loadData(response.body().getContent(),"text/html; charset=utf-8", "UTF-8");

                if(response.body().getPrev().contains("https")){

                    prev=response.body().getPrev().split("nid=")[1];

                    leftImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getNewsDetails(prev);
                        }
                    });
                }

                if(response.body().getNext().contains("https")) {

                    next = response.body().getNext().split("nid=")[1];

                    rightImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getNewsDetails(next);
                        }
                    });
                }

                shareImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Shareable imageShare = new Shareable.Builder(NewsDetailsActivity.this)
                                .message(response.body().getShare())
                                .socialChannel(Shareable.Builder.TWITTER)
                                .socialChannel(Shareable.Builder.FACEBOOK)
                                .socialChannel(Shareable.Builder.ANY)
                                .build();
                        imageShare.share();
                    }
                });

                commentImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(NewsDetailsActivity.this,CommentsActivity.class);
                        intent.putExtra("newsid",response.body().getNewsid());
                        intent.putExtra("title",response.body().getTitle());
                        startActivity(intent);
                    }
                });

                progressBar.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(Call<NewsDetailsDataModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }


}

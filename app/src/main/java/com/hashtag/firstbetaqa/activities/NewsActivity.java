package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.NewsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.NewsAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    RecyclerView newsRecycler;
    Locale current;
    String lang,cid,catName;
    TextView title;
    String tag="NewsActivity";
    ProgressBar progressBar;
    PullRefreshLayout refreshLayout;
    LinearLayoutManager linearLayoutManager;
    int page=1 ,value=0;

    List<NewsDataModel> allNewsList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);


        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        newsRecycler= (RecyclerView) findViewById(R.id.newsRecycler);
        linearLayoutManager=new LinearLayoutManager(this);
        newsRecycler.setLayoutManager(linearLayoutManager);

        title= (TextView) findViewById(R.id.title);

        if(getIntent().getExtras() != null){
            cid=getIntent().getExtras().getString("cid","");
            catName=getIntent().getExtras().getString("catName","");
            title.setText(catName);

        }

        refreshLayout= (PullRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                page=1;
                value=0;
                allNewsList.clear();
                getNews(page);
            }
        });

        getNews(page);

        newsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                int lastItem=linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalItems=linearLayoutManager.getItemCount();

                if((lastItem+1) == totalItems){
                    getNews(++page);
                }

            }
        });

    }

    private void getNews(final int index) {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<NewsDataModel>> conn=retrofitApi.getNews(cid,lang,index);
        conn.enqueue(new Callback<List<NewsDataModel>>() {
            @Override
            public void onResponse(Call<List<NewsDataModel>> call, Response<List<NewsDataModel>> response) {

                allNewsList.addAll(response.body());
                NewsAdapter newsAdapter=new NewsAdapter(NewsActivity.this,allNewsList);
                newsRecycler.setAdapter(newsAdapter);
                int scrollTo=(value * response.body().size())-1;
                newsRecycler.scrollToPosition(scrollTo);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                value++;
            }

            @Override
            public void onFailure(Call<List<NewsDataModel>> call, Throwable t) {
                Log.v(tag,t.getMessage()+"?");
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);


            }
        });
    }
}

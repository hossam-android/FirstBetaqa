package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.OfferDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.OfferAdapter;
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

public class OffersActivity extends AppCompatActivity {

    RecyclerView offerRecycler;
    TextView title;

    String cid="",lang="";
    Locale current;

    ProgressBar progressBar;
    PullRefreshLayout refreshLayout;

    int page=1 ,value=0,scrollTo=0;

    List<OfferDataModel> allOffers=new ArrayList<>();
    
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_offers);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        title= (TextView) findViewById(R.id.title);
        offerRecycler= (RecyclerView) findViewById(R.id.offersRecycler);
        linearLayoutManager=new LinearLayoutManager(this);
        offerRecycler.setLayoutManager(linearLayoutManager);

        if(getIntent().getExtras() != null){
            title.setText(getIntent().getExtras().getString("title",""));
            cid=getIntent().getExtras().getString("cid","");
        }


        refreshLayout= (PullRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                page=1;
                value=0;
                allOffers.clear();
                getOffers(page);
            }
        });

        getOffers(page);
        
        offerRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int items=linearLayoutManager.getItemCount();
                int last=linearLayoutManager.findLastCompletelyVisibleItemPosition();
                
                if((last+1) == items){
                    getOffers(++page);
                }
                
            }
        });


    }

    private void getOffers(int index) {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<OfferDataModel>> conn=retrofitApi.getOffers(cid,lang,index);
        conn.enqueue(new Callback<List<OfferDataModel>>() {
            @Override
            public void onResponse(Call<List<OfferDataModel>> call, Response<List<OfferDataModel>> response) {
                allOffers.addAll(response.body());
                offerRecycler.setAdapter(new OfferAdapter(OffersActivity.this,allOffers));

                scrollTo= value*response.body().size();
                value++;

                offerRecycler.scrollToPosition(scrollTo);

                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

                value++;
            }

            @Override
            public void onFailure(Call<List<OfferDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
            }
        });
    }
}

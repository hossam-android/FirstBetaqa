package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.RegionDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.RegionAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionsInSubCat extends AppCompatActivity implements PullRefreshLayout.OnRefreshListener {


    RecyclerView regionRecycler;
    Locale current;
    String lang,cid,regionId,photo,titleString;
    ImageView regionImage;
    TextView title;

    ProgressBar progressBar;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regions_in_sub_cat);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        if(getIntent().getExtras() !=null){
            cid=getIntent().getExtras().getString("cid","");
            regionId=getIntent().getExtras().getString("id","");
            photo=getIntent().getExtras().getString("photo","");
            titleString=getIntent().getExtras().getString("title","");
        }

        regionRecycler= (RecyclerView) findViewById(R.id.regionsRecycler);
        regionRecycler.setLayoutManager(new LinearLayoutManager(this));
        regionImage= (ImageView) findViewById(R.id.regionImage);
        title= (TextView) findViewById(R.id.title);



        title.setText(titleString);
        Picasso.with(this).load(photo).transform(new RoundedCornersTransform(5,0)).into(regionImage);

        getRegions();









    }

    private void getRegions() {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        final Call<List<RegionDataModel>> conn=retrofitApi.getRegionForSubCats(cid,regionId,lang);
        conn.enqueue(new Callback<List<RegionDataModel>>() {
            @Override
            public void onResponse(Call<List<RegionDataModel>> call, Response<List<RegionDataModel>> response) {

                RegionAdapter regionAdapter=new RegionAdapter(RegionsInSubCat.this,response.body());
                regionRecycler.setAdapter(regionAdapter);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<RegionDataModel>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public void onRefresh() {
        getRegions();
    }
}

package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.SubRegionDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.SubRegionAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubRegionActivity extends AppCompatActivity implements PullRefreshLayout.OnRefreshListener {

    RecyclerView subRegionRecycler;
    TextView title;
    String cid,cityId,lang,titleString;
    Locale current;
    ProgressBar progressBar;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_region);


        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        if(getIntent().getExtras() != null){
            cid= getIntent().getExtras().getString("cid","");
            cityId= getIntent().getExtras().getString("cityId","");
            titleString= getIntent().getExtras().getString("title","");
        }


        Log.v("subREgion",cid+"   "+cityId);

        title= (TextView) findViewById(R.id.title);
        title.setText(titleString);

        subRegionRecycler= (RecyclerView) findViewById(R.id.subRegionRecycler);
        subRegionRecycler.setLayoutManager(new LinearLayoutManager(this));

        getSubRegion();



    }

    private void getSubRegion() {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<SubRegionDataModel>> conn=retrofitApi.getSubRegion(cid,cityId,lang);
        conn.enqueue(new Callback<List<SubRegionDataModel>>() {
            @Override
            public void onResponse(Call<List<SubRegionDataModel>> call, Response<List<SubRegionDataModel>> response) {

                SubRegionAdapter subRegionAdapter=new SubRegionAdapter(SubRegionActivity.this,response.body());
                subRegionRecycler.setAdapter(subRegionAdapter);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<SubRegionDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getSubRegion();
    }
}

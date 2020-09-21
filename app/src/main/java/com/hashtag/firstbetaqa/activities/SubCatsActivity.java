package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.SubCatsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.SubCatsAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCatsActivity extends AppCompatActivity
        implements PullRefreshLayout.OnRefreshListener {

    RecyclerView subCatsRecyclerView;
    String cid,lang,catName;
    Locale current;
    TextView title;
    ProgressBar progressBar;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub_cats);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        title= (TextView) findViewById(R.id.title);
        if(getIntent().getExtras() != null){
            cid=getIntent().getExtras().getString("cid","");
            catName=getIntent().getExtras().getString("catName","");
            title.setText(catName);

        }

        subCatsRecyclerView= (RecyclerView) findViewById(R.id.subCatsRecycler);
        subCatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSubCats();




    }

    private void getSubCats() {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<SubCatsDataModel>> conn=retrofitApi.getSubCats(cid,lang);
        conn.enqueue(new Callback<List<SubCatsDataModel>>() {
            @Override
            public void onResponse(Call<List<SubCatsDataModel>> call, Response<List<SubCatsDataModel>> response) {

                SubCatsAdapter subCatsAdapter=new SubCatsAdapter(SubCatsActivity.this,response.body());
                subCatsRecyclerView.setAdapter(subCatsAdapter);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
///
            }

            @Override
            public void onFailure(Call<List<SubCatsDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public void onRefresh() {
        getSubCats();
    }
}

package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.SubCountCatsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.SubCounCatAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCatCountActivity extends AppCompatActivity implements PullRefreshLayout.OnRefreshListener {

    String url="",catName="";

    TextView title;

    String lang="";

    RecyclerView coutSubRecycler;

    PullRefreshLayout refreshLayout;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cat_count);

        title=findViewById(R.id.title);
        
        lang=getResources().getConfiguration().locale.getLanguage();

        if(getIntent().getExtras() != null){
            url=getIntent().getExtras().getString("url","");
            catName=getIntent().getExtras().getString("catName","");
            title.setText(catName);

        }

        progressBar=findViewById(R.id.progress);
        WanderingCubes wanderingCubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(wanderingCubes);

        coutSubRecycler=findViewById(R.id.coutSubRecycler);
        coutSubRecycler.setLayoutManager(new LinearLayoutManager(this));

        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        getSubCountCats();

    }

    private void getSubCountCats() {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<SubCountCatsDataModel>> conn=retrofitApi.getSubCountCats(url,lang);
        conn.enqueue(new Callback<List<SubCountCatsDataModel>>() {
            @Override
            public void onResponse(Call<List<SubCountCatsDataModel>> call, Response<List<SubCountCatsDataModel>> response) {
                SubCounCatAdapter subCounCatAdapter=new SubCounCatAdapter(SubCatCountActivity.this,response.body());
                coutSubRecycler.setAdapter(subCounCatAdapter);

                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<SubCountCatsDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public void onRefresh() {
        getSubCountCats();
    }
}

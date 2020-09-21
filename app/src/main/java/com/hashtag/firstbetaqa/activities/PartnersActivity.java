package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.PartnersDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.PartnersGridAdapter;
import com.hashtag.firstbetaqa.fragments.AddPartnerDialogFragment;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnersActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;

    RecyclerView partnersRecycler;

    Button joinPartnersBtn;

    String url="",titleString="",lang="";
    
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);

        Locale locale=getResources().getConfiguration().locale;
        lang=locale.getLanguage();

        progressBar=findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);
        
        title=findViewById(R.id.title);
        partnersRecycler=findViewById(R.id.partnersRecycler);
        joinPartnersBtn=findViewById(R.id.joinPartners);
        joinPartnersBtn.setOnClickListener(this);

        if(getIntent().getExtras() != null){
            titleString=getIntent().getExtras().getString("title","");
            url=getIntent().getExtras().getString("url","");
            title.setText(titleString);
        }

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,calculateNoOfColumns());
        partnersRecycler.setLayoutManager(gridLayoutManager);

        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<PartnersDataModel>> conn=retrofitApi.getPartners(url,lang);
        conn.enqueue(new Callback<List<PartnersDataModel>>() {
            @Override
            public void onResponse(Call<List<PartnersDataModel>> call, Response<List<PartnersDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                PartnersGridAdapter partnersGridAdapter=new PartnersGridAdapter(PartnersActivity.this,response.body());
                partnersRecycler.setAdapter(partnersGridAdapter);
            }

            @Override
            public void onFailure(Call<List<PartnersDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });

    }

    public int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 150);
        return noOfColumns;
    }

    @Override
    public void onClick(View view) {

        AddPartnerDialogFragment addPartnerDialogFragment=new AddPartnerDialogFragment();
        addPartnerDialogFragment.show(getSupportFragmentManager(),"addPartner");

    }
}

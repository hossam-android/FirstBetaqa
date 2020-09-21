package com.hashtag.firstbetaqa;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hashtag.firstbetaqa.Model.MainCatsDataModel;
import com.hashtag.firstbetaqa.Model.SendTokenDataModel;
import com.hashtag.firstbetaqa.activities.SearchActivity;
import com.hashtag.firstbetaqa.adapter.MainCatsAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        PullRefreshLayout.OnRefreshListener {


    RecyclerView mainRecycler;
    String tag="MainActivity";
    MainCatsAdapter mainCatsAdapter;

    TextView toolbarTitleTextView,toolbarLangTextView;
    Toolbar toolbar;
    Locale current;

    ProgressBar progressBar;

    ImageView searchImageView;
    SearchView searchView;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
      //  Log.v("tokkk",refreshedToken);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);




        toolbarTitleTextView=toolbar.findViewById(R.id.title);
        toolbarTitleTextView.setText(getResources().getString(R.string.toolbar_home));

        toolbarLangTextView=toolbar.findViewById(R.id.changeLang);



        mainRecycler= (RecyclerView) findViewById(R.id.mainRecycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mainCatsAdapter.getItemViewType(position) == 1 ? 2 : 1;
            }
        });
        mainRecycler.setLayoutManager(gridLayoutManager);

        current = getResources().getConfiguration().locale;
        Log.v(tag,current.getLanguage()+"   "+current.getDisplayLanguage());


        toolbarLangTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(current.getLanguage().equals(new Locale("ar").getLanguage())){
                    setLocale("en");
                }else if(current.getLanguage().equals(new Locale("en").getLanguage())){
                    setLocale("ar");
                }




            }
        });


        if(current.getLanguage().equals("ar")){
            toolbarLangTextView.setText("E");

        }else{
            toolbarLangTextView.setText("ع");
        }

        getMainCats(current.getLanguage());

       // sendToken();

    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_item, menu);

//        final MenuItem myActionMenuItem = menu.findItem( R.id.search);
//        searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//                intent.putExtra("search",query);
//                startActivity(intent);
//
//                myActionMenuItem.collapseActionView();
//
//
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent=new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);

        return true;
    }

    private void sendToken() {
        if(FirebaseInstanceId.getInstance().getToken() != null) {
            RetrofitApi retrofitApi = SingletonRetrofit.getRetrofitInstant();
            Call<SendTokenDataModel> conn = retrofitApi.sendToken(FirebaseInstanceId.getInstance().getToken());
            conn.enqueue(new Callback<SendTokenDataModel>() {
                @Override
                public void onResponse(Call<SendTokenDataModel> call, Response<SendTokenDataModel> response) {
                    Log.v("tokennnn", response.body().getMessage() + ">>>");
                }

                @Override
                public void onFailure(Call<SendTokenDataModel> call, Throwable t) {

                    Log.v("tokennnn", t.getMessage() + ">>>");
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void getMainCats(String lang) {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<MainCatsDataModel>> conn=retrofitApi.getMainCats(lang);
        conn.enqueue(new Callback<List<MainCatsDataModel>>() {
            @Override
            public void onResponse(Call<List<MainCatsDataModel>> call, Response<List<MainCatsDataModel>> response) {

                Log.v(tag,response.body().get(0).getTitle());

                mainCatsAdapter=new MainCatsAdapter(MainActivity.this,response.body());
                mainRecycler.setAdapter(mainCatsAdapter);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<MainCatsDataModel>> call, Throwable t) {
                Log.v(tag,t.getMessage()+">>>");
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);


            }
        });
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition (0, 0);
        finish();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public void onRefresh() {
        getMainCats(current.getLanguage());
    }
}

package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.CircleButton;
import com.hashtag.firstbetaqa.Model.AreaDataModel;
import com.hashtag.firstbetaqa.Model.CitiesDataModel;
import com.hashtag.firstbetaqa.Model.SearchDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.AreaSpinnerAdapter;
import com.hashtag.firstbetaqa.adapter.CitiesSpinnerAdapter;
import com.hashtag.firstbetaqa.adapter.SearchAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    SearchView searchView;

    TextView title;

    String searchString;

    Locale current;
    String lang;

    ProgressBar progressBar;

    EditText searchEditText;
    CircleButton searchBtn;

    Spinner cities,district;
    String cid="",areaId="";

    NestedScrollView nestedScrollView;

    List<SearchDataModel> searchList=new ArrayList<>();

    int page=1 ,value=0,scrollTo=0;

    LinearLayoutManager linearLayoutManager;

    RecyclerView searchRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();


        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        searchEditText=findViewById(R.id.searchEditText);
        searchBtn=findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);


        title=findViewById(R.id.title);
        title.setText("نتائج البحث");

        cities=findViewById(R.id.cities);
        district=findViewById(R.id.district);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        searchRecycler=findViewById(R.id.searchRecycler);
        linearLayoutManager=new LinearLayoutManager(this);
        searchRecycler.setLayoutManager(linearLayoutManager);

//        searchString=getIntent().getExtras().getString("search","");
//
//        getSearchFromServer(searchString,"","");
        
        getCities();

        nestedScrollView=findViewById(R.id.nested);
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (nestedScrollView != null) {
                    if (nestedScrollView.getChildAt(0).getBottom() <=
                            (nestedScrollView.getHeight() + nestedScrollView.getScrollY())) {


                        int items=linearLayoutManager.getItemCount();
                        int last=linearLayoutManager.findLastCompletelyVisibleItemPosition();

                        if((last+1) == items){
                            searchBtn.startAnimation();
                            getSearchFromServer(searchEditText.getText().toString(),cid,areaId,++page);
                        }

                    } else {
                        //scroll view is not at bottom
                    }
                }
            }
        });




    }

    private void getCities() {
        progressBar.setVisibility(View.VISIBLE);
        
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<CitiesDataModel>> conn=retrofitApi.getCities(lang);
        conn.enqueue(new Callback<List<CitiesDataModel>>() {
            @Override
            public void onResponse(Call<List<CitiesDataModel>> call, final Response<List<CitiesDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                CitiesSpinnerAdapter citiesSpinnerAdapter=new CitiesSpinnerAdapter
                        (SearchActivity.this,R.layout.spinner_row,response.body());
                cities.setAdapter(citiesSpinnerAdapter);
                cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        cid=response.body().get(i).getCityid();
                        getAreas(response.body().get(i).getUrl(),lang);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<CitiesDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
        
        
    }

    private void getAreas(String url , String lang) {
        progressBar.setVisibility(View.VISIBLE);

        RetrofitApi retrofitApi=SingletonRetrofit.getRetrofitInstant();
        Call<List<AreaDataModel>> conn=retrofitApi.getArea(url,lang);
        conn.enqueue(new Callback<List<AreaDataModel>>() {
            @Override
            public void onResponse(Call<List<AreaDataModel>> call, final Response<List<AreaDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                AreaSpinnerAdapter areaSpinnerAdapter=new AreaSpinnerAdapter
                        (SearchActivity.this,R.layout.spinner_row,response.body());
                district.setAdapter(areaSpinnerAdapter);
                district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        areaId=response.body().get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<AreaDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    private void getSearchFromServer(String search , String cityId , String areaId, int index) {
//        progressBar.setVisibility(View.VISIBLE);
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Log.v("sasa",cityId+"  "+areaId);
        Call<List<SearchDataModel>> conn=retrofitApi.search(search,cityId,areaId,lang,index);
        conn.enqueue(new Callback<List<SearchDataModel>>() {
            @Override
            public void onResponse(Call<List<SearchDataModel>> call, Response<List<SearchDataModel>> response) {
                searchList.addAll(response.body());
                SearchAdapter searchAdapter=new SearchAdapter(SearchActivity.this,searchList);
                searchRecycler.setAdapter(searchAdapter);

                scrollTo=value*response.body().size();
                value++;

                searchRecycler.scrollToPosition(scrollTo);



//                progressBar.setVisibility(View.GONE);
                revertLoadingBtn();

            }

            @Override
            public void onFailure(Call<List<SearchDataModel>> call, Throwable t) {

                revertLoadingBtn();
                
                if (searchRecycler.getAdapter() != null){
                    searchList.clear();
                    searchRecycler.getAdapter().notifyDataSetChanged();
                }

//                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void revertLoadingBtn() {
        searchBtn.revertAnimation(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu( Menu menu) {
//        getMenuInflater().inflate( R.menu.search_item, menu);
//
//        final MenuItem myActionMenuItem = menu.findItem( R.id.search);
//        searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                getSearchFromServer(query,"","");
//                myActionMenuItem.collapseActionView();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                return false;
//            }
//        });
//        return true;
//    }

    @Override
    public void onClick(View view) {
        searchBtn.startAnimation();
        getSearchFromServer(searchEditText.getText().toString(),cid,areaId,page);

    }
}

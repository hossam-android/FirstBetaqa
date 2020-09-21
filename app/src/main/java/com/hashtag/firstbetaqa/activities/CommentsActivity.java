package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.CircleButton;
import com.hashtag.firstbetaqa.Model.CommentDataModel;
import com.hashtag.firstbetaqa.Model.DefaultDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.ShowSnackbar;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.CommentsAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView commentRecycler;
    String lang,newsid,title;
    Locale current;
    ImageView addComment;
    AlertDialog alertDialog ;
    EditText fullname,email,content;
    CircleButton send;

     AlertDialog.Builder builder;
     View dialog;

    ProgressBar progressBar;
    PullRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comments);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        ((TextView)toolbar.findViewById(R.id.title)).setText(getResources().getString(R.string.comments));

        addComment= (ImageView) findViewById(R.id.addComment);
        addComment.setOnClickListener(this);


        if(getIntent().getExtras() != null){
            newsid=getIntent().getExtras().getString("newsid","");
           // title=getIntent().getExtras().getString("title","التعليقات");
            Log.v("CommentA",newsid+"   >>"+title);

        }



        commentRecycler= (RecyclerView) findViewById(R.id.commentRecycler);
        commentRecycler.setLayoutManager(new LinearLayoutManager(this));


        refreshLayout= (PullRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                getComments();
            }
        });

        getComments();


    }

    private void getComments() {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<CommentDataModel>> conn=retrofitApi.getComments(newsid,lang);
        conn.enqueue(new Callback<List<CommentDataModel>>() {
            @Override
            public void onResponse(Call<List<CommentDataModel>> call, Response<List<CommentDataModel>> response) {
                CommentsAdapter adapter=new CommentsAdapter(CommentsActivity.this,response.body());
                commentRecycler.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<CommentDataModel>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

            }
        });
    }


    @Override
    public void onClick(View view) {

        builder=new AlertDialog.Builder(this);
        dialog= LayoutInflater.from(this).inflate(R.layout.add_comment_layout,null);
        fullname=dialog.findViewById(R.id.fullname);
        email=dialog.findViewById(R.id.email);
        content=dialog.findViewById(R.id.commentContent);
        send=dialog.findViewById(R.id.sendComment);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String fullnameString=fullname.getText().toString();
                String emailString=email.getText().toString();
                String contentString=content.getText().toString();

                if(fullnameString.isEmpty() || emailString.isEmpty() || contentString.isEmpty()){
                    ShowSnackbar.showSnack(dialog,"من فضلك قم بإدخال كل الحقول");
                }else{
                    if(Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                        send.startAnimation();
                        RetrofitApi retrofitApi = SingletonRetrofit.getRetrofitInstant();
                        Call<DefaultDataModel> conn = retrofitApi.sendComment(newsid, fullnameString, emailString, contentString, lang);
                        conn.enqueue(new Callback<DefaultDataModel>() {
                            @Override
                            public void onResponse(Call<DefaultDataModel> call, Response<DefaultDataModel> response) {
                                if (response.body().getStatus().equals("done")) {
                                    Toast.makeText(CommentsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    if (alertDialog != null) {
                                        alertDialog.dismiss();
                                        getComments();
                                    } else {
                                        fullname.setText("");
                                        email.setText("");
                                        content.setText("");
                                    }

                                } else {
                                    ShowSnackbar.showSnack(view, response.body().getMessage());
                                }
                                send.dispose();
                            }

                            @Override
                            public void onFailure(Call<DefaultDataModel> call, Throwable t) {
                                send.dispose();

                            }
                        });
                    }else{
                        ShowSnackbar.showSnack(view, "من فضلك قم بإدخال بريد إلكتروني بشكل صحيح");

                    }
                }


            }
        });

        builder.setView(dialog);
        alertDialog=builder.create();
        alertDialog.show();

    }
}

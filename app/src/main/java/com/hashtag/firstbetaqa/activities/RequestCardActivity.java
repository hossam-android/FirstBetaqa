package com.hashtag.firstbetaqa.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.CircleButton;
import com.hashtag.firstbetaqa.Model.RequestCardDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.fragments.RequestCardDialogFragment;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCardActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    WebView contentWebView;
    Button requestCard;
    String lang;
    Locale current;
    
    EditText fullName,address,phone,details;
    CircleButton sendCard;
    View dialog;
    AlertDialog alertDialog;
    boolean back=true;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_card);


        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        progressBar= (ProgressBar) findViewById(R.id.progress);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);

        title= (TextView) findViewById(R.id.title);
        contentWebView= (WebView) findViewById(R.id.requestContent);
        requestCard= (Button) findViewById(R.id.requestCardBtn);
        requestCard.setOnClickListener(this);

        if(getIntent().getExtras() != null){
            title.setText(getIntent().getExtras().getString("title",""));
        }

        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<RequestCardDataModel>> conn=retrofitApi.requestCardInfo
                ("https://www.firstcard.sa/api.php?mod=pages&pageid=25",lang);
        conn.enqueue(new Callback<List<RequestCardDataModel>>() {
            @Override
            public void onResponse(Call<List<RequestCardDataModel>> call, Response<List<RequestCardDataModel>> response) {

                Log.v("aaaaaa",response.body().get(0).getContent()+">>>");
                progressBar.setVisibility(View.GONE);
                contentWebView.loadData(response.body().get(0).getContent(),"text/html ; charset=utf-8","UTF-8");
            }

            @Override
            public void onFailure(Call<List<RequestCardDataModel>> call, Throwable t) {
                Log.v("aaaaaa",t.getMessage()+">>>");
                progressBar.setVisibility(View.GONE);


            }
        });


    }

    @Override
    public void onClick(View view) {

//        dialog=LayoutInflater.from(this).inflate(R.layout.request_card_layout,null);
//        fullName=dialog.findViewById(R.id.fullname);
//        address=dialog.findViewById(R.id.address);
//        phone=dialog.findViewById(R.id.phone);
//        details=dialog.findViewById(R.id.details);
//
//        if(lang.equals("ar")){
//
//            fullName.setGravity(Gravity.RIGHT);
//            address.setGravity(Gravity.RIGHT);
//            phone.setGravity(Gravity.RIGHT);
//            details.setGravity(Gravity.RIGHT);
//
//
//        }else if(lang.equals("en")){
//
//
//            fullName.setGravity(Gravity.LEFT);
//            address.setGravity(Gravity.LEFT);
//            phone.setGravity(Gravity.LEFT);
//            details.setGravity(Gravity.LEFT);
//
//        }
//
//
//        sendCard=dialog.findViewById(R.id.sendCard);
//        sendCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String fullNameString=fullName.getText().toString();
//                String addressString=address.getText().toString();
//                String phoneString=phone.getText().toString();
//                String detailsString=details.getText().toString();
//
//                if(fullNameString.isEmpty() || addressString.isEmpty() || phoneString.isEmpty() || detailsString.isEmpty()){
//                    ShowSnackbar.showSnack(dialog,getResources().getString(R.string.fillData));
//                }else{
//
//                    sendCard.startAnimation();
//                    back=false;
//                    RetrofitApi retrofitApi=SingletonRetrofit.getRetrofitInstant();
//                    Call<DefaultDataModel> conn=retrofitApi.sendCard(fullNameString,addressString,phoneString,detailsString);
//                    conn.enqueue(new Callback<DefaultDataModel>() {
//                        @Override
//                        public void onResponse(Call<DefaultDataModel> call, Response<DefaultDataModel> response) {
//
//                            if(response.body().getStatus().equals("done")){
//                                Toast.makeText(RequestCardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                               if(alertDialog != null)
//                                alertDialog.dismiss();
//
//                            }else{
//                                ShowSnackbar.showSnack(dialog,response.body().getMessage());
//                            }
//                            sendCard.dispose();
//                            back=true;
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<DefaultDataModel> call, Throwable t) {
//                            sendCard.dispose();
//                            back=true;
//
//                        }
//                    });
//
//
//
//
//                }
//
//
//            }
//        });
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setView(dialog);
//        builder.setCancelable(false);
//        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
//
//                if(i == keyEvent.KEYCODE_BACK){
//
//                    if(back) {
//                        if (alertDialog != null)
//                            alertDialog.dismiss();
//                    }
//
//                }
//                return true;
//            }
//        });
//        alertDialog=builder.create();
//        alertDialog.show();

        RequestCardDialogFragment cardDialogFragment=new RequestCardDialogFragment();
        cardDialogFragment.show(getSupportFragmentManager(),"card");
        
        
        

    }


}

package com.hashtag.firstbetaqa.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.hashtag.firstbetaqa.CircleButton;
import com.hashtag.firstbetaqa.Model.ContactUsDataModel;
import com.hashtag.firstbetaqa.Model.DefaultDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.ShowSnackbar;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {


    TextView phone,email,title;
    EditText content,emailForm,fullname ,phoneForm;
    CircleButton send;
    LinearLayout linearLayout;
    String lang,titleToolbar;
    Locale current;
    boolean back=true;
    LinearLayout linPhone,emailLinearDirection,phoneLinearDirection;

    RelativeLayout linEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_us);

        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        emailLinearDirection= (LinearLayout) findViewById(R.id.emailLinear);
        phoneLinearDirection= (LinearLayout) findViewById(R.id.phoneLinear);

        if(lang.equals("en")){
            emailLinearDirection.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            phoneLinearDirection.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        }else if(lang.equals("ar")){
            emailLinearDirection.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            phoneLinearDirection.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }

        linearLayout= (LinearLayout) findViewById(R.id.lin);
        title= (TextView) findViewById(R.id.title);
        phone= (TextView) findViewById(R.id.phone);
        email= (TextView) findViewById(R.id.email);
        fullname= (EditText) findViewById(R.id.fullname);
        emailForm= (EditText) findViewById(R.id.emailform);
        content= (EditText) findViewById(R.id.content);
        phoneForm= (EditText) findViewById(R.id.phoneForm);
        send= (CircleButton) findViewById(R.id.send);
        send.setOnClickListener(this);


        if(getIntent().getExtras() != null){
            titleToolbar=getIntent().getExtras().getString("title","");
            title.setText(titleToolbar);
        }

        call();

        email();

        getContact();


    }

    private void email() {
        linEmail= (RelativeLayout) findViewById(R.id.linEmail);
        linEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setType("message/rfc822");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }

    private void call() {
        linPhone= (LinearLayout) findViewById(R.id.linPhone);
        linPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ActivityCompat.checkSelfPermission(ContactUsActivity.this,android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));
                    startActivity(intent);
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE},0);
                    }
                }
            }
        });
    }

    private void getContact() {
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<ContactUsDataModel> conn=retrofitApi.getMyInformation(lang);
        conn.enqueue(new Callback<ContactUsDataModel>() {
            @Override
            public void onResponse(Call<ContactUsDataModel> call, Response<ContactUsDataModel> response) {
                phone.setText(response.body().getPhone());
                email.setText(response.body().getEmail());
            }

            @Override
            public void onFailure(Call<ContactUsDataModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 0 && grantResults != null && grantResults.length > 0 ){
            if (ActivityCompat.checkSelfPermission(ContactUsActivity.this,android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE},0);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

        String phoneString = phoneForm.getText().toString();
        String emailString=emailForm.getText().toString();
        String fullNameString=fullname.getText().toString();
        String contentString=content.getText().toString();

        if(phoneString.isEmpty() || emailString.isEmpty() | fullNameString.isEmpty() || contentString.isEmpty()){

            ShowSnackbar.showSnack(linearLayout,getResources().getString(R.string.fillData));
        }else{

            Log.v("sssss", Patterns.EMAIL_ADDRESS.matcher(emailString).matches()+">>>"+emailString);
            if(Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                send.startAnimation();
                back=false;
                RetrofitApi retrofitApi = SingletonRetrofit.getRetrofitInstant();
                Call<DefaultDataModel> conn = retrofitApi.sendContactUs(emailString, fullNameString, phoneString, contentString, lang);
                conn.enqueue(new Callback<DefaultDataModel>() {
                    @Override
                    public void onResponse(Call<DefaultDataModel> call, Response<DefaultDataModel> response) {
                        if (response.body().getStatus().equals("done")) {
                            Toast.makeText(ContactUsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            ShowSnackbar.showSnack(linearLayout, response.body().getMessage());
                        }
                        send.dispose();
                        back=true;
                    }

                    @Override
                    public void onFailure(Call<DefaultDataModel> call, Throwable t) {

                        send.dispose();
                        back=true;
                    }
                });
            }else{
                ShowSnackbar.showSnack(linearLayout, getResources().getString(R.string.emailError));

            }
        }

    }

    @Override
    public void onBackPressed() {

        if(back) {
            super.onBackPressed();
        }

    }
}

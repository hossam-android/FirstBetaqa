package com.hashtag.firstbetaqa.fragments;

import android.app.Dialog;
import android.os.Bundle;

import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;


import com.hashtag.firstbetaqa.CircleButton;
import com.hashtag.firstbetaqa.Model.DefaultDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.ShowSnackbar;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ebda3-mint on 9/24/17.
 */

public class RequestJoinDialogFragment extends DialogFragment {


    EditText fullName,buildingAddress,phone,email, details,buildingName;
    CircleButton sendCard;

    String lang;
    Locale current;
    ScrollView scrollView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.request_join_layout,container,false);


        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        scrollView=view.findViewById(R.id.sco);
        fullName=view.findViewById(R.id.fullname);
        phone=view.findViewById(R.id.phone);
        email=view.findViewById(R.id.email);
        buildingName=view.findViewById(R.id.buildingName);
        buildingAddress=view.findViewById(R.id.buildingAddress);
        details =view.findViewById(R.id.details);

        if(lang.equals("ar")){
            fullName.setGravity(Gravity.RIGHT);
            phone.setGravity(Gravity.RIGHT);
            email.setGravity(Gravity.RIGHT);
            buildingName.setGravity(Gravity.RIGHT);
            buildingAddress.setGravity(Gravity.RIGHT);
            details.setGravity(Gravity.RIGHT);
        }else if(lang.equals("en")){

            fullName.setGravity(Gravity.LEFT);
            phone.setGravity(Gravity.LEFT);
            email.setGravity(Gravity.LEFT);
            buildingName.setGravity(Gravity.LEFT);
            buildingAddress.setGravity(Gravity.LEFT);
            details.setGravity(Gravity.LEFT);
        }


        sendCard=view.findViewById(R.id.sendCard);
        sendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameString=fullName.getText().toString();
                String addressString=buildingAddress.getText().toString();
                String phoneString=phone.getText().toString();
                String emailString=email.getText().toString();
                String detailsString= details.getText().toString();
                String buildingNameString=buildingName.getText().toString();

                if(fullNameString.isEmpty() || addressString.isEmpty() || phoneString.isEmpty() || emailString.isEmpty() || detailsString.isEmpty() ||buildingNameString.isEmpty()){
                    ShowSnackbar.showSnack(scrollView,getResources().getString(R.string.fillData));
                }else{
                    if(Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                        sendCard.startAnimation();
                        RetrofitApi retrofitApi = SingletonRetrofit.getRetrofitInstant();
                        Call<DefaultDataModel> conn = retrofitApi.sendJoin(fullNameString, emailString, phoneString, detailsString, buildingNameString, addressString);
                        conn.enqueue(new Callback<DefaultDataModel>() {
                            @Override
                            public void onResponse(Call<DefaultDataModel> call, Response<DefaultDataModel> response) {

                                if (response.body().getStatus().equals("done")) {
                                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    getDialog().dismiss();

                                } else {
                                    ShowSnackbar.showSnack(scrollView, response.body().getMessage());
                                }
                                sendCard.dispose();

                            }

                            @Override
                            public void onFailure(Call<DefaultDataModel> call, Throwable t) {
                                sendCard.dispose();


                            }
                        });
                    }else {
                        ShowSnackbar.showSnack(scrollView, getResources().getString(R.string.emailError));
                    }

                }


            }
        });


        return view;
    }


}

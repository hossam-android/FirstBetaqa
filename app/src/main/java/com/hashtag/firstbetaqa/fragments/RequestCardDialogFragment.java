package com.hashtag.firstbetaqa.fragments;

import android.app.Dialog;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class RequestCardDialogFragment extends DialogFragment {

    EditText fullName,address,phone,details;
    CircleButton sendCard;

    String lang;
    Locale current;
    RelativeLayout relativeLayout;

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
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.request_card_layout, container, false);


        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();

        relativeLayout=view.findViewById(R.id.relative);
        fullName=view.findViewById(R.id.fullname);
        address=view.findViewById(R.id.address);
        phone=view.findViewById(R.id.phone);
        details=view.findViewById(R.id.details);

        if(lang.equals("ar")){

            fullName.setGravity(Gravity.RIGHT);
            address.setGravity(Gravity.RIGHT);
            phone.setGravity(Gravity.RIGHT);
            details.setGravity(Gravity.RIGHT);


        }else if(lang.equals("en")){


            fullName.setGravity(Gravity.LEFT);
            address.setGravity(Gravity.LEFT);
            phone.setGravity(Gravity.LEFT);
            details.setGravity(Gravity.LEFT);

        }


        sendCard=view.findViewById(R.id.sendCard);
        sendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameString=fullName.getText().toString();
                String addressString=address.getText().toString();
                String phoneString=phone.getText().toString();
                String detailsString=details.getText().toString();

                if(fullNameString.isEmpty() || addressString.isEmpty() || phoneString.isEmpty() || detailsString.isEmpty()){
                    ShowSnackbar.showSnack(relativeLayout,getResources().getString(R.string.fillData));
                }else{

                    sendCard.startAnimation();
                    RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
                    Call<DefaultDataModel> conn=retrofitApi.sendCard(fullNameString,addressString,phoneString,detailsString);
                    conn.enqueue(new Callback<DefaultDataModel>() {
                        @Override
                        public void onResponse(Call<DefaultDataModel> call, Response<DefaultDataModel> response) {

                            if(response.body().getStatus().equals("done")){
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                getDialog().dismiss();

                            }else{
                                ShowSnackbar.showSnack(relativeLayout,response.body().getMessage());
                            }
                            sendCard.dispose();

                        }

                        @Override
                        public void onFailure(Call<DefaultDataModel> call, Throwable t) {
                            sendCard.dispose();

                        }
                    });




                }


            }
        });


        return view;
    }
}

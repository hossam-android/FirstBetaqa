package com.hashtag.firstbetaqa.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.CircleButton;
import com.hashtag.firstbetaqa.Model.AreaDataModel;
import com.hashtag.firstbetaqa.Model.CitiesDataModel;
import com.hashtag.firstbetaqa.Model.DefaultDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.ShowSnackbar;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.adapter.AreaSpinnerAdapter;
import com.hashtag.firstbetaqa.adapter.CitiesSpinnerAdapter;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPartnerDialogFragment extends DialogFragment implements View.OnClickListener, OnAnimationEndListener {

    EditText companyName,websiteLink,managerName,jobTitle,managerPhone,managerEmail,details,employeesNumber;
    Spinner citySpinner,sectorsSpinner,areaSpinner;
    ImageView partnerImage;
    RelativeLayout relativeLayout;

    String lang="",sectorId="",cityId="",areaId="";

    ProgressBar progressBar;

    CircleButton joinPartnersBtn;

    String imagePath="";
    
    public AddPartnerDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add_partner_dialog, container, false);

        Locale locale=getActivity().getResources().getConfiguration().locale;
        lang=locale.getLanguage();
        
        initViews(view);
        
        getCities();

        getSectors();

        return view;
    }

    private void getSectors() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<AreaDataModel>> conn=retrofitApi.getSectors(lang);
        conn.enqueue(new Callback<List<AreaDataModel>>() {
            @Override
            public void onResponse(Call<List<AreaDataModel>> call, final Response<List<AreaDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                AreaSpinnerAdapter areaSpinnerAdapter=new AreaSpinnerAdapter(getActivity(),R.layout.spinner_row,response.body());
                sectorsSpinner.setAdapter(areaSpinnerAdapter);
                sectorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        sectorId=response.body().get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<AreaDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                getDialog().dismiss();
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
                CitiesSpinnerAdapter citiesSpinnerAdapter=new CitiesSpinnerAdapter(getActivity(),R.layout.spinner_row,response.body());
                citySpinner.setAdapter(citiesSpinnerAdapter);
                citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        cityId=response.body().get(i).getCityid();
                        getAreas(response.body().get(i).getUrl());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<CitiesDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                getDialog().dismiss();

            }
        });

    }

    private void getAreas(String url) {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<List<AreaDataModel>> conn=retrofitApi.getArea(url,lang);
        conn.enqueue(new Callback<List<AreaDataModel>>() {
            @Override
            public void onResponse(Call<List<AreaDataModel>> call, final Response<List<AreaDataModel>> response) {
                AreaSpinnerAdapter areaSpinnerAdapter=new AreaSpinnerAdapter(getActivity(),R.layout.spinner_row,response.body());
                areaSpinner.setAdapter(areaSpinnerAdapter);
                areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        areaId=response.body().get(i).getId();
                        progressBar.setVisibility(View.GONE);
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


    private void initViews(View view) {
        companyName=view.findViewById(R.id.companyName);
        websiteLink=view.findViewById(R.id.wedsiteLink);
        managerName=view.findViewById(R.id.managerName);
        jobTitle=view.findViewById(R.id.jobTitle);
        managerPhone=view.findViewById(R.id.managerPhone);
        managerEmail=view.findViewById(R.id.managerEmail);
        details=view.findViewById(R.id.details);
        citySpinner=view.findViewById(R.id.citySpinner);
        employeesNumber=view.findViewById(R.id.numberOfEmployees);
        sectorsSpinner=view.findViewById(R.id.sectorSpinner);
        joinPartnersBtn=view.findViewById(R.id.joinPartnersBtn);
        relativeLayout=view.findViewById(R.id.relative);
        joinPartnersBtn.setOnClickListener(this);
        partnerImage=view.findViewById(R.id.partnerImage);
        partnerImage.setOnClickListener(this);
        areaSpinner=view.findViewById(R.id.areaSpinner);
        progressBar=view.findViewById(R.id.progress);
        WanderingCubes wanderingCubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(wanderingCubes);
        

        if(lang.equals("ar")){
            companyName.setGravity(Gravity.RIGHT);
            websiteLink.setGravity(Gravity.RIGHT);
            managerName.setGravity(Gravity.RIGHT);
            jobTitle.setGravity(Gravity.RIGHT);
            employeesNumber.setGravity(Gravity.RIGHT);
            managerPhone.setGravity(Gravity.RIGHT);
            managerEmail.setGravity(Gravity.RIGHT);
            details.setGravity(Gravity.RIGHT);
                      
        }else if(lang.equals("en")){
            companyName.setGravity(Gravity.LEFT);
            websiteLink.setGravity(Gravity.LEFT);
            managerName.setGravity(Gravity.LEFT);
            jobTitle.setGravity(Gravity.LEFT);
            employeesNumber.setGravity(Gravity.LEFT);
            managerPhone.setGravity(Gravity.LEFT);
            managerEmail.setGravity(Gravity.LEFT);
            details.setGravity(Gravity.LEFT);
           
        }        
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.partnerImage:

                if(ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 0);
                }else {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                break;
            case R.id.joinPartnersBtn:

                if(!imagePath.equals("")){
                    if(Patterns.EMAIL_ADDRESS.matcher(managerEmail.getText().toString()).matches()){
                        if(!TextUtils.isEmpty(companyName.getText().toString()) && !TextUtils.isEmpty(websiteLink.getText().toString()) && !TextUtils.isEmpty(managerName.getText().toString())
                                && !TextUtils.isEmpty(jobTitle.getText().toString()) && !TextUtils.isEmpty(employeesNumber.getText().toString()) &&!TextUtils.isEmpty(managerPhone.getText().toString())
                                && !TextUtils.isEmpty(details.getText().toString()) && (!TextUtils.isEmpty(cityId) || !TextUtils.isEmpty(areaId))) {

                            joinPartnersBtn.startAnimation();
                            File file = new File(imagePath);
                            RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
                            MultipartBody.Part part = MultipartBody.Part.createFormData("photo", "image", imageRequestBody);

                            RequestBody companyNameRequestBody = RequestBody.create(MediaType.parse("text/plain"), companyName.getText().toString());
                            RequestBody websiteRequestBody = RequestBody.create(MediaType.parse("text/plain"), websiteLink.getText().toString());
                            RequestBody managerNameRequestBody = RequestBody.create(MediaType.parse("text/plain"), managerName.getText().toString());
                            RequestBody jobTitleRequestBody = RequestBody.create(MediaType.parse("text/plain"), jobTitle.getText().toString());
                            RequestBody employeesNumberRequestBody = RequestBody.create(MediaType.parse("text/plain"), employeesNumber.getText().toString());
                            RequestBody managerPhoneRequestBody = RequestBody.create(MediaType.parse("text/plain"), managerPhone.getText().toString());
                            RequestBody managerEmailRequestBody = RequestBody.create(MediaType.parse("text/plain"), managerEmail.getText().toString());
                            RequestBody detailsRequestBody = RequestBody.create(MediaType.parse("text/plain"), details.getText().toString());
                            RequestBody cityRequestBody = RequestBody.create(MediaType.parse("text/plain"), areaId);
                            RequestBody sectorRequestBody = RequestBody.create(MediaType.parse("text/plain"), sectorId);

                            RetrofitApi retrofitApi=SingletonRetrofit.getRetrofitInstant();
                            Call<DefaultDataModel> conn=retrofitApi.joinPartners(companyNameRequestBody,cityRequestBody,websiteRequestBody,employeesNumberRequestBody,sectorRequestBody,managerNameRequestBody,managerEmailRequestBody,jobTitleRequestBody,managerPhoneRequestBody,detailsRequestBody,part);
                            conn.enqueue(new Callback<DefaultDataModel>() {
                                @Override
                                public void onResponse(Call<DefaultDataModel> call, Response<DefaultDataModel> response) {

                                    if(response.body().getStatus().equals("done")){
                                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        getDialog().dismiss();
                                    }else {
                                        ShowSnackbar.showSnack(relativeLayout,response.body().getMessage());
                                    }
                                    joinPartnersBtn.revertAnimation(AddPartnerDialogFragment.this);

                                    joinPartnersBtn.stopAnimation();

                                }

                                @Override
                                public void onFailure(Call<DefaultDataModel> call, Throwable t) {
                                    joinPartnersBtn.stopAnimation();
                                    joinPartnersBtn.revertAnimation(AddPartnerDialogFragment.this);

                                }
                            });
                        }else {
                            ShowSnackbar.showSnack(relativeLayout,getResources().getString(R.string.fillData));
                        }

                    }else {
                        ShowSnackbar.showSnack(relativeLayout,getResources().getString(R.string.emailError));
                    }

                }else {
                    ShowSnackbar.showSnack(relativeLayout,getResources().getString(R.string.selectImage));
                }

                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1 && grantResults != null){

            if(ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            imagePath=getRealPathFromURI(data.getData());
            Picasso.with(getActivity()).load(new File(imagePath)).transform(new RoundedCornersTransform(10,0)).fit().into(partnerImage);
        }


    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getActivity().getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onAnimationEnd() {

    }
}

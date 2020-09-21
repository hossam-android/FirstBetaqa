package com.hashtag.firstbetaqa.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hashtag.firstbetaqa.Model.StoreDetailsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.SingletonRetrofit;
import com.hashtag.firstbetaqa.interfaces.RetrofitApi;
import com.robertsimoes.shareable.Shareable;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailsActivity extends AppCompatActivity {

    ImageView staticMap,storeImage;
    TextView title,storeName,phone,address;
    WebView webView,webView2;
String id ;
    Locale current;
    String lang,itemId,titleString;
    String lat,lng;
    String url="https://maps.googleapis.com/maps/api/staticmap?center="+lat+
            ","+lng+"&zoom=13&size=600x300&maptype=roadmap" +
            "&markers=color:blue%7C"+lat+","+lng;

    ProgressBar progressBar;
    RelativeLayout phoneRelativeLayout,addressRelativeLayout;

    ImageView shareLocation;
    StoreDetailsDataModel storeDetailsDataModel;
    TextView phoneToMove,addressToMove;
    private String String_url=
            "\"<div style=\\\"direction:rtl;text-align:center;font-size:15px" +
                    " !important;font-weight:bold;\\\"><table border=\\\"0\\\" style=\\\"" +
                    "width:270px\\\">\\r\\n\\t<tbody>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" " +
                    "style=\\\"text-align:center; width:126px\\\"><span style=\\\"color:#c0392b\\\"><span" +
                    " style=\\\"font-size:12px\\\"><strong>&nbsp;الخدمة&nbsp;</strong></span></span></td>\\r\\n\\t\\t\\t" +
                    "<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"color:#c0392b\\\">" +
                    "<span style=\\\"font-size:12px\\\"><strong>نسبة الخصم&nbsp; أو " +
                    "السعر بعد الخصم&nbsp;&nbsp;</strong></span></span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">كشف الأسنان&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">مجانا لحاملى البطاقة الأولى&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">تقويم الأسنان&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">2500 ريال</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">تركيبات بورسلين&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">400 ريال</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">تركيبات الزريكون&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">500 ريال</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">تبييض الأسنان بالليزر&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">500 ريال</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">خصم على باقى الخدمات&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">30%</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">سحب العصب&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">315 ريال بعد الخصم&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">الخلع العادى&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">105&nbsp;ريال بعد الخصم&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">الخلع الجراحى&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">315&nbsp;ريال بعد الخصم&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">حشوات عادية&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">105&nbsp;ريال بعد الخصم&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\" style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">حشوات تجميلية&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\"><span style=\\\"font-size:11px\\\">140&nbsp;ريا" +
                    "ل بعد الخصم&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t\\t<tr>\\r\\n\\t\\t\\t<td colspan=\\\"2\\\"" +
                    " style=\\\"text-align:center; width:126px\\\"><span style=\\\"font-size:12px\\\">" +
                    "تنظيف اللثة&nbsp;</span></td>\\r\\n\\t\\t\\t<td style=\\\"text-align:center; width:128px\\\">" +
                    "<span style=\\\"font-size:11px\\\">140&nbsp;ريال" +
                    " بعد الخصم&nbsp;</span></td>\\r\\n\\t\\t</tr>\\r\\n\\t</tbody>\\r\\n</table></div>\"";
    // "https://www.firstcard.sa/api.php?mod=market&itemid=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WanderingCubes cubes=new WanderingCubes();
        progressBar.setIndeterminateDrawable(cubes);


        phoneToMove= (TextView) findViewById(R.id.phoneToMove);
        addressToMove= (TextView) findViewById(R.id.addresToMove);
        title= (TextView) findViewById(R.id.title);
        storeImage= (ImageView) findViewById(R.id.detailsImage);
        storeName= (TextView) findViewById(R.id.detailsTitle);
        phone= (TextView) findViewById(R.id.detailsPhone);
        address= (TextView) findViewById(R.id.detailsAdress);
        staticMap= (ImageView) findViewById(R.id.staticmap);


        current=getResources().getConfiguration().locale;
        lang=current.getLanguage();


        if(getIntent().hasExtra("itemid"))
        {
            Log.v("dex","here");
            itemId=getIntent().getStringExtra("itemid");
            Log.v("dex",itemId);
            titleString=getIntent().getExtras().getString("title","");

        }else {
            Log.v("dex","herexx");
        }

        //else (getIntent().getExtras()!=null){
          //  id = getIntent().getExtras().getString("nid");
        //}

        call();

        addressRelativeLayout= (RelativeLayout) findViewById(R.id.addressRelative);

        if(lang.equals("en")){



            phoneRelativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            addressRelativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)address.getLayoutParams();
            params.addRule(RelativeLayout.RIGHT_OF, R.id.addresToMove);
            address.setLayoutParams(params);

            address.setGravity(Gravity.RIGHT);


        }else if(lang.equals("ar")){

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)address.getLayoutParams();
            params.addRule(RelativeLayout.LEFT_OF, R.id.addresToMove);
            address.setLayoutParams(params);

            address.setGravity(Gravity.LEFT);


            phoneRelativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            addressRelativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


        }



        staticMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lng+"?q=<"+lat+">,<"+lng+">" +
                            "("+storeName.getText().toString()+")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }

                }catch (Exception e){

                }
            }
        });

        title.setText(titleString);

        webView= (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);



        RetrofitApi retrofitApi= SingletonRetrofit.getRetrofitInstant();
        Call<StoreDetailsDataModel> conn=retrofitApi.getStoreDetails(itemId,lang);
        conn.enqueue(new Callback<StoreDetailsDataModel>()
        {
            @Override
            public void onResponse(Call<StoreDetailsDataModel> call, Response<StoreDetailsDataModel> response) {

                storeDetailsDataModel=response.body();
                webView.loadData(response.body().getContent2(),"text/html; charset=utf-8",
                        "UTF-8");
                Picasso.with(StoreDetailsActivity.this).load(response.body().getPhoto()).into(storeImage);
                storeName.setText(response.body().getTitle());
                phone.setText(response.body().getPhone());
                address.setText(response.body().getAddress());
                lat=response.body().getLat();
                lng=response.body().getLng();
                Picasso.with(StoreDetailsActivity.this).load(url).into(staticMap);
                progressBar.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(Call<StoreDetailsDataModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });

        shareLocation= (ImageView) findViewById(R.id.shareLocation);
        shareLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = "http://maps.google.com/maps?saddr=" +lat+","+lng;


                Shareable imageShare = new Shareable.Builder(StoreDetailsActivity.this)
                        .message(storeDetailsDataModel.getTitle())
                        .url(url)
                        .socialChannel(Shareable.Builder.TWITTER)
                        .socialChannel(Shareable.Builder.FACEBOOK)
                        .socialChannel(Shareable.Builder.ANY)
                        .build();
                imageShare.share();
            }
        });
    }

    private void call() {
        phoneRelativeLayout= (RelativeLayout) findViewById(R.id.phoneRelative);
        phoneRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission
                        (StoreDetailsActivity.this,android.Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if(requestCode == 0 && grantResults != null && grantResults.length > 0 ){
            if (ActivityCompat.checkSelfPermission
                    (StoreDetailsActivity.this,android.Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE},0);
                }
            }
        }
    }
}

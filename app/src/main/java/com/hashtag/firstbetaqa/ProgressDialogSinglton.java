package com.hashtag.firstbetaqa;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ebda3-mint on 8/20/17.
 */

public class ProgressDialogSinglton {

    private ProgressDialogSinglton() {
    }

    public static ProgressDialog showDiaolg(Context context, String msg){
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
}

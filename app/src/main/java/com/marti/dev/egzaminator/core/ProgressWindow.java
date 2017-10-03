package com.marti.dev.egzaminator.core;

import android.app.Dialog;
import android.content.Context;

import com.marti.dev.egzaminator.R;

/**
 * Created by marcin on 01.10.2017.
 */

public class ProgressWindow {

    private static Dialog mProgressDialog;

    public static void show(Context context){
        mProgressDialog = new Dialog(context);
        mProgressDialog.setContentView(R.layout.progress_window);
        mProgressDialog.show();
    }

    public static void dismiss(){
        if(mProgressDialog != null)
            if(mProgressDialog.isShowing())
                mProgressDialog.dismiss();
    }
}

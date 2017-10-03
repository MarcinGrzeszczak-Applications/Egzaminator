package com.marti.dev.egzaminator.CustomViews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marti.dev.egzaminator.R;

/**
 * Created by marcin on 30.09.2017.
 */

public class ErrorWindow {

    private static AlertDialog alertDialog;

    public static void show(Context context,String title,String message){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.error_window,null);

        Button positiveButton = view.findViewById(R.id.ErrorWindow_button_ok);
        TextView titleView = view.findViewById(R.id.ErrorWIndow_text_title);
        TextView messageView = view.findViewById(R.id.ErrorWindow_text_message);

        titleView.setText(title);
        messageView.setText(message);
        positiveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(alertDialog != null)
                            alertDialog.dismiss();
                    }
                });
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(view);
        alertDialog.show();


    }
}

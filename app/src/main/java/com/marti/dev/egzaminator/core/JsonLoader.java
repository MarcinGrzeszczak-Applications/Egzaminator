package com.marti.dev.egzaminator.core;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by marcin on 30.09.2017.
 */

public class JsonLoader extends AsyncTaskLoader<TestModel> {

    private Context mContext;
    private Uri mFileUri;

    public JsonLoader(Context context,Uri fileUri) {
        super(context);
        mContext = context;
        mFileUri = fileUri;
        forceLoad();
    }

    @Override
    public TestModel loadInBackground() {
       /*
        boolean looper = true;
        while(looper){
            if(!looper)
                return null;
        }
        */
            try {
                StringBuilder json = new StringBuilder();
                String line;
                AssetFileDescriptor fileDescriptor = mContext.getContentResolver().openAssetFileDescriptor(mFileUri,"r");

                assert fileDescriptor != null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileDescriptor.createInputStream()));

                while((line = reader.readLine()) != null)
                    json.append(line);

                fileDescriptor.close();
                reader.close();

                if(!json.toString().matches("")){
                    return ParseJson.parse(json.toString());
                }
                    else return null;
            }  catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }
}

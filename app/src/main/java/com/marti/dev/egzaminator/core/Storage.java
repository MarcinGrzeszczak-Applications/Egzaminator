package com.marti.dev.egzaminator.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by marcin on 30.09.2017.
 */

public class Storage {

    public static final String TEST_FILE_URI = "TESTFILEURI";

    private static final String PATH = "com.marti.dev.EGZAMINATOR";


    public static String read(Context context,String name){
        return initSharedPreferences(context).getString(name,null);
    }

    public static void write(Context context,String name,String content){
        initSharedPreferences(context).edit().putString(name,content).apply();
    }

    private static SharedPreferences initSharedPreferences(Context context){
        return context.getSharedPreferences(PATH,Context.MODE_PRIVATE);
    }
}

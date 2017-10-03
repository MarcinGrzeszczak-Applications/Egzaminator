package com.marti.dev.egzaminator.core;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;


public class ParseJson {


    public static TestModel parse(String json) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<TestModel> jsonAdapter = moshi.adapter(TestModel.class);
        try {
            return jsonAdapter.lenient().serializeNulls().fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

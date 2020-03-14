package com.example.administrator.demo22.Utils;

import com.google.gson.Gson;

public class Utility {
    public static newList parseJsonWithGson(final String requestText){
        Gson gson = new Gson();
        return gson.fromJson(requestText,newList.class);
    }
}

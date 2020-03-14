package com.example.administrator.demo22.Utils;



import com.google.gson.annotations.SerializedName;

import java.util.List;

public class newList {
    public int code;
    public String msg;
    @SerializedName("newslist")
    public List<Utils> newsList;
}

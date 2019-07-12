package com.example.pj19980729.drivingbook.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class AppVariables {
    static public Map<String, Object> map = new HashMap<>();
    static public String cookieStr;
    static public OkHttpClient clients;
    static public HashMap<HttpUrl,List<Cookie>> cookieStore=new HashMap<>();

    public OkHttpClient getClient() {
        return clients;
    }

    public void setClient(OkHttpClient client) {
        this.clients = client;
    }

}

package com.example.pj19980729.drivingbook.okhttp;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.constant.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestUtil {

    public RequestUtil() {
    }

    public RequestUtil(String url, Map map,Callback callback) {
        doPost(url,map,callback);
    }

    public  void doPost(String url, Map map, final Callback callback) {

       final String urlx = String.format("%s/%s", Constants.context, url);
       final String data = JSON.toJSONString(map);


       new Thread(new Runnable() {
           @Override
           public void run() {
               Log.i("=========================doing it==============","");

               OkHttpClient client = new OkHttpClient();
               RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), data);
               Request request = new Request.Builder()
                       .url(urlx)
                       .post(body)
                       .build();
               Call call = client.newCall(request);
               call.enqueue(callback);
           }
       }).start();




   }

    public  Response  doPostSy(String url, Map map) throws IOException {

        final String urlx = String.format("%s/%s", Constants.context, url);
        final String data = JSON.toJSONString(map);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), data);
        Request request = new Request.Builder()
                .url(urlx)
                .post(body)
                .build();
        Response response = null;
            response = client.newCall(request).execute();

        return response;

    }



}

package com.example.pj19980729.drivingbook.okhttp;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.constant.Constants;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestUtil {

   public static void doPost(String url, Map map, final Callback callback) {

       final String urlx = String.format("%s/%s", Constants.context, url);
       final String data = JSON.toJSONString(map);


       new Thread(new Runnable() {
           @Override
           public void run() {
               OkHttpClient client = new OkHttpClient();
               RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), data);
               Request request = new Request.Builder()
                       .url(urlx)
                       .post(body)
                       .build();
               Call call = client.newCall(request);
               call.enqueue(callback);
           }
       });


   }

}

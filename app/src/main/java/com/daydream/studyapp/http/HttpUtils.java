package com.daydream.studyapp.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-20
 */

public class HttpUtils {

    /**
     * 获取主线程的handler
     */
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void doGet(String url, final HttpCallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        OnStart(callBack);

        HttpClientHelper.getInstance()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        OnError(callBack, e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        onSuccess(callBack, response.body().string());
                    }
                });
    }

    public static void OnStart(HttpCallBack callBack){
        if(callBack!=null){
            callBack.onStart();
        }
    }

    public static void onSuccess(final HttpCallBack callBack,final String data){
        if(callBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {//在主线程操作
                    callBack.onSuccess(data);
                }
            });
        }
    }
    public static void OnError(final HttpCallBack callBack,final String msg){
        if(callBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onError(msg);
                }
            });
        }
    }
}

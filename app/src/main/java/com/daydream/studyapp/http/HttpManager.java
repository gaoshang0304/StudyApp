package com.daydream.studyapp.http;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-20
 */

public class HttpManager {

    /**
     * 获取主线程的handler
     */
    private static Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 异步get请求
     *
     * @param url
     * @param callBack
     */
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

    /**
     * 异步post请求
     *
     * @param url      地址
     * @param params   map 键值对
     * @param callBack 回调
     */
    public static void doPost(String url, Map<String, String> params, final HttpCallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        Request request = new Request.Builder()
                .post(builder.build())
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

    /**
     * 异步post请求
     *
     * @param url        地址
     * @param jsonParams json 字符串
     * @param callBack   回调
     */
    public static void doPost(String url, String jsonParams, final HttpCallBack callBack) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request request = new Request.Builder()
                .post(body)
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

    /**
     * 上传单个文件
     *
     * @param url
     * @param pathName
     * @param fileName
     * @param callBack
     */
    public static void upLoadFile(String url, String pathName, String fileName, final HttpCallBack callBack) {
        //判断文件类型
        MediaType MEDIA_TYPE = MediaType.parse(judgeType(pathName));
        //创建文件参数
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(MEDIA_TYPE.type(), fileName,
                        RequestBody.create(MEDIA_TYPE, new File(pathName)));

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "9199fdef135c122")
                .post(builder.build())
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

    /**
     * 根据文件路径判断MediaType
     *
     * @param path
     * @return
     */
    private static String judgeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 下载文件
     *
     * @param url
     * @param fileDir
     * @param fileName
     */
    public static void downLoadFile(String url, final String fileDir, final String fileName) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = HttpClientHelper.getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(fileDir, fileName);
                    fos = new FileOutputStream(file);
                    //计算下载进度
                    long totalSize = response.body().contentLength();
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        sum += len;
                        //progress就是进度值
                        int progress = (int) (sum * 1.0f / totalSize * 100);
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) is.close();
                    if (fos != null) fos.close();
                }
            }
        });
    }


    public static void OnStart(HttpCallBack callBack) {
        if (callBack != null) {
            callBack.onStart();
        }
    }

    public static void onSuccess(final HttpCallBack callBack, final String data) {
        if (callBack != null) {
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

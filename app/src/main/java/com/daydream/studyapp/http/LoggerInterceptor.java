package com.daydream.studyapp.http;

import android.text.TextUtils;

import com.daydream.studyapp.util.LogUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by dayDream on 2018/5/2.
 */

public class LoggerInterceptor implements Interceptor {

    private final String mTag;

    public LoggerInterceptor(String mTag) {
        this.mTag = mTag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtilsForRequest(request);
        Response response = chain.proceed(request);
        return LogUtilsForResponse(response);
    }

    private Response LogUtilsForResponse(Response response) {
        try {
            //===>response LogUtils
            LogUtils.e(mTag, "========response'LogUtils=======");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            LogUtils.e(mTag, "url : " + clone.request().url());
            LogUtils.e(mTag, "code : " + clone.code());
            LogUtils.e(mTag, "protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
                LogUtils.e(mTag, "message : " + clone.message());

            ResponseBody body = clone.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    LogUtils.e(mTag, "responseBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        String resp = body.string();
                        LogUtils.e(mTag, "responseBody's content : " + resp);

                        body = ResponseBody.create(mediaType, resp);
                        return response.newBuilder().body(body).build();
                    } else {
                        LogUtils.e(mTag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }

            }

            LogUtils.e(mTag, "========response'LogUtils=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return response;
    }

    private void LogUtilsForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            LogUtils.e(mTag, "========request'LogUtils=======");
            LogUtils.e(mTag, "method : " + request.method());
            LogUtils.e(mTag, "url : " + url);
            if (headers != null && headers.size() > 0) {
                LogUtils.e(mTag, "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    LogUtils.e(mTag, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        LogUtils.e(mTag, "requestBody's content : " + bodyToString(request));
                    } else {
                        LogUtils.e(mTag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            LogUtils.e(mTag, "========request'LogUtils=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}

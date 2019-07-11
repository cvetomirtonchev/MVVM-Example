package com.example.mvvm.networking;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import okhttp3.ResponseBody;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class BaseApiResponse<T> {
    private T mResponseData;
    private ResponseBody mErrorBody;
    private int mHttpCode;
    @ResponseStatus
    private int mResponseStatus;


    public T getResponseData() {
        return mResponseData;
    }

    public void setResponseData(T responseData) {
        this.mResponseData = responseData;
    }

    public ResponseBody getErrorBody() {
        return mErrorBody;
    }

    public void setErrorBody(ResponseBody errorBody) {
        mErrorBody = errorBody;
    }

    public int getHttpCode() {
        return mHttpCode;
    }

    public void setHttpCode(int httpCode) {
        mHttpCode = httpCode;
    }


    public static final int STATUS_OK = 200;
    public static final int STATUS_FAILED = 500;

    @ResponseStatus
    public int getResponseStatus() {
        return mResponseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        mResponseStatus = responseStatus;
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_OK, STATUS_FAILED})
    @interface ResponseStatus {
    }
}

package com.example.mvvm.networking;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kostadin.georgiev on 7/11/2019.
 */
public class ResponseCallback<T> implements Callback {
    private final MutableLiveData<BaseApiResponse<T>> mLiveData;

    public ResponseCallback(MutableLiveData<BaseApiResponse<T>> liveData) {
        mLiveData = liveData;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
        BaseApiResponse<T> baseApiResponse = new BaseApiResponse<>();
        baseApiResponse.setHttpCode(response.code());

        if (response.isSuccessful()) {
            baseApiResponse.setResponseStatus(BaseApiResponse.STATUS_OK);
            baseApiResponse.setResponseData((T) response.body());
            mLiveData.setValue(baseApiResponse);
        } else {
            baseApiResponse.setResponseStatus(BaseApiResponse.STATUS_FAILED);
            baseApiResponse.setErrorBody(response.errorBody());
            mLiveData.setValue(baseApiResponse);
        }
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
        mLiveData.setValue(null); // not needed for this example
    }
}
